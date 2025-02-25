package org.traccar.protocol;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.io.StringReader;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.BaseHttpProtocolDecoder;
import org.traccar.DeviceSession;
import org.traccar.Protocol;
import org.traccar.helper.BitUtil;
import org.traccar.helper.UnitsConverter;
import org.traccar.model.Position;


public class DmtHttpProtocolDecoder
        extends BaseHttpProtocolDecoder {
    private static final Logger log = LoggerFactory.getLogger(DmtHttpProtocolDecoder.class);

    public DmtHttpProtocolDecoder(Protocol protocol) {
        super(protocol);
    }


    protected Object decode(Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {
        Object result;
        FullHttpRequest request = (FullHttpRequest) msg;

        JsonObject root = Json.createReader(new StringReader(request.content().toString(StandardCharsets.US_ASCII))).readObject();
        log.info("DmtHTTPProtocolDecoder JSON Received :: {}", root.toString());

        if (root.containsKey("device")) {
            result = (Position) decodeEdge(channel, remoteAddress, root);
        } else {
            result = (Position) decodeTraditional(channel, remoteAddress, root);
        }

        sendResponse(channel, (result != null) ? HttpResponseStatus.OK : HttpResponseStatus.BAD_REQUEST);
        return result;
    }


    private Collection<Position> decodeTraditional(Channel channel, SocketAddress remoteAddress, JsonObject root) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        DeviceSession deviceSession = getDeviceSession(channel, remoteAddress, new String[]{root.getString("IMEI")});
        if (deviceSession == null) {
            return null;
        }

        List<Position> positions = new LinkedList<>();

        JsonArray records = root.getJsonArray("Records");

        for (int i = 0; i < records.size(); i++) {
            Position position = new Position(getProtocolName());
            position.setDeviceId(deviceSession.getDeviceId());

            JsonObject record = records.getJsonObject(i);

            position.set("index", Integer.valueOf(record.getInt("SeqNo")));
            position.set("event", Integer.valueOf(record.getInt("Reason")));

            position.setDeviceTime(dateFormat.parse(record.getString("DateUTC")));

            JsonArray fields = record.getJsonArray("Fields");

            for (int j = 0; j < fields.size(); j++) {
                int input, output;
                JsonObject adc, field = fields.getJsonObject(j);
                switch (field.getInt("FType")) {
                    case 0:
                        position.setFixTime(dateFormat.parse(field.getString("GpsUTC")));
                        position.setLatitude(field.getJsonNumber("Lat").doubleValue());
                        position.setLongitude(field.getJsonNumber("Long").doubleValue());
                        position.setAltitude(field.getInt("Alt"));
                        position.setSpeed(UnitsConverter.knotsFromCps(field.getInt("Spd")));
                        position.setCourse(field.getInt("Head"));
                        position.setAccuracy(field.getInt("PosAcc"));
                        position.setValid((field.getInt("GpsStat") > 0));
                        break;
                    case 2:
                        input = field.getInt("DIn");
                        output = field.getInt("DOut");

                        position.set("ignition", Boolean.valueOf(BitUtil.check(input, 0)));

                        position.set("input", Integer.valueOf(input));
                        position.set("output", Integer.valueOf(output));
                        position.set("status", Integer.valueOf(field.getInt("DevStat")));
                        break;
                    case 6:
                        adc = field.getJsonObject("AnalogueData");
                        if (adc.containsKey("1")) {
                            position.set("battery", Double.valueOf(adc.getInt("1") * 0.001D));
                        }
                        if (adc.containsKey("2")) {
                            position.set("power", Double.valueOf(adc.getInt("2") * 0.01D));
                        }
                        if (adc.containsKey("3")) {
                            position.set("deviceTemp", Double.valueOf(adc.getInt("3") * 0.01D));
                        }
                        if (adc.containsKey("4")) {
                            position.set("rssi", Integer.valueOf(adc.getInt("4")));
                        }
                        if (adc.containsKey("5")) {
                            position.set("solarPower", Double.valueOf(adc.getInt("5") * 0.001D));
                        }
                        break;
                    case 25:    //Handle WiFi realted data
                        JsonArray aps = field.getJsonArray("APs");
                        for (int k = 0; k < aps.size(); k++) {
                            JsonObject ap = aps.getJsonObject(k);
                            position.set("ap" + k + "_mac", ap.getString("MAC"));
                            position.set("ap" + k + "_sig", ap.getInt("Sig"));
                            position.set("ap" + k + "_ch", ap.getInt("Ch"));
                        }
                        break;
                    case 36: // Handle Towers information
                        JsonArray towers = field.getJsonArray("Towers");
                        for (int k = 0; k < towers.size(); k++) {
                            JsonObject tower = towers.getJsonObject(k);
                            position.set("tower" + k + "_srv", tower.getBoolean("SRV", false));
                            position.set("tower" + k + "_nw", tower.getInt("NW", -1));
                            position.set("tower" + k + "_cid", tower.getInt("CID", -1));
                            position.set("tower" + k + "_lac", tower.getInt("LAC", -1));
                            position.set("tower" + k + "_mcc", tower.getInt("MCC", -1));
                            position.set("tower" + k + "_mnc", tower.getInt("MNC", -1));
                            position.set("tower" + k + "_earfcn", tower.getInt("EARFCN", -1));
                            position.set("tower" + k + "_pcid", tower.getInt("PCID", -1));
                            position.set("tower" + k + "_rsrp", tower.getInt("RSRP", -1));
                            position.set("tower" + k + "_rsrq", tower.getInt("RSRQ", -1));
                            position.set("tower" + k + "_ta", tower.getInt("TA", -1));
                            if (tower.containsKey("DeltaT")) {
                                position.set("tower" + k + "_deltat", tower.getInt("DeltaT"));
                            }
                        }
                        break;
                }
            }
            positions.add(position);
            log.info("HTTP DECODER: {}", positions.toString());
        }

        return positions;
    }



    private Position decodeEdge(Channel channel, SocketAddress remoteAddress, JsonObject root) {
        JsonObject device = root.getJsonObject("device");

        DeviceSession deviceSession = getDeviceSession(channel, remoteAddress, new String[]{device.getString("imei")});
        if (deviceSession == null) {
            return null;
        }

        Position position = new Position(getProtocolName());
        position.setDeviceId(deviceSession.getDeviceId());

        Date time = new Date(OffsetDateTime.parse(root.getString("date")).toInstant().toEpochMilli());

        if (root.containsKey("lat") && root.containsKey("lng")) {
            position.setValid(true);
            position.setTime(time);
            position.setLatitude(root.getJsonNumber("lat").doubleValue());
            position.setLongitude(root.getJsonNumber("lng").doubleValue());
            position.setAccuracy(root.getJsonNumber("posAcc").doubleValue());
        } else {
            getLastLocation(position, time);
        }

        position.set("index", Integer.valueOf(root.getInt("sqn")));
        position.set("event", Integer.valueOf(root.getInt("reason")));

        if (root.containsKey("analogues")) {
            JsonArray analogues = root.getJsonArray("analogues");
            for (int i = 0; i < analogues.size(); i++) {
                JsonObject adc = analogues.getJsonObject(i);
                position.set("adc" + adc.getInt("id"), Integer.valueOf(adc.getInt("val")));
            }
        }

        if (root.containsKey("inputs")) {
            int input = root.getInt("inputs");
            position.set("ignition", Boolean.valueOf(BitUtil.check(input, 0)));
            position.set("input", Integer.valueOf(input));
        }
        if (root.containsKey("outputs")) {
            position.set("output", Integer.valueOf(root.getInt("outputs")));
        }
        if (root.containsKey("status")) {
            position.set("status", Integer.valueOf(root.getInt("status")));
        }

        if (root.containsKey("counters")) {
            JsonArray counters = root.getJsonArray("counters");
            for (int i = 0; i < counters.size(); i++) {
                JsonObject counter = counters.getJsonObject(i);
                switch (counter.getInt("id")) {
                    case 0:
                        position.set("battery", Double.valueOf(counter.getInt("val") * 0.001D));
                        break;
                    case 1:
                        position.set("batteryLevel", Double.valueOf(counter.getInt("val") * 0.01D));
                        break;
                    default:
                        position.set("counter" + counter.getInt("id"), Integer.valueOf(counter.getInt("val")));
                        break;
                }


            }
        }
        return position;
    }
}