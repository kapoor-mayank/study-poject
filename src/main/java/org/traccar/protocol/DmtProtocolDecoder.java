package org.traccar.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.*;
import org.traccar.helper.BitUtil;
import org.traccar.helper.DateBuilder;
import org.traccar.helper.UnitsConverter;
import org.traccar.model.Position;

public class DmtProtocolDecoder extends BaseProtocolDecoder {
    private static final Logger LOGGER = LoggerFactory.getLogger(DmtProtocolDecoder.class);
    public static final int MSG_HELLO = 0;

    public static final int MSG_HELLO_RESPONSE = 1;

    public static final int MSG_DATA_RECORD = 4;

    public static final int MSG_COMMIT = 5;

    public static final int MSG_COMMIT_RESPONSE = 6;

    public static final int MSG_DATA_RECORD_64 = 16;

    public static final int MSG_CANNED_REQUEST_1 = 20;

    public static final int MSG_CANNED_RESPONSE_1 = 21;

    public static final int MSG_CANNED_REQUEST_2 = 34;

    public static final int MSG_CANNED_RESPONSE_2 = 35;

    public DmtProtocolDecoder(Protocol protocol) {
        super(protocol);
        LOGGER.info("DmtProtocolDecoder initialized");
    }

    private void sendResponse(Channel channel, int type, ByteBuf content) {
        if (channel != null) {
            ByteBuf response = Unpooled.buffer();
            response.writeByte(2);
            response.writeByte(85);
            response.writeByte(type);
            response.writeShortLE((content != null) ? content.readableBytes() : 0);
            if (content != null) {
                response.writeBytes(content);
                content.release();
            }
            channel.writeAndFlush(new NetworkMessage(response, channel.remoteAddress()));
        }
    }

    private List<Position> decodeFixed64(Channel channel, SocketAddress remoteAddress, ByteBuf buf) {
        DeviceSession deviceSession = getDeviceSession(channel, remoteAddress, new String[0]);
        if (deviceSession == null)
            return null;
        List<Position> positions = new LinkedList<>();
        while (buf.readableBytes() >= 64) {
            Position position = new Position(getProtocolName());
            position.setDeviceId(deviceSession.getDeviceId());
            buf.readByte();
            position.set("index", Long.valueOf(buf.readUnsignedIntLE()));
            long time = buf.readUnsignedIntLE();
            position.setTime((new DateBuilder())
                    .setYear((int)(2000L + (time & 0x3FL)))
                    .setMonth((int)(time >> 6L) & 0xF)
                    .setDay((int)(time >> 10L) & 0x1F)
                    .setHour((int)(time >> 15L) & 0x1F)
                    .setMinute((int)(time >> 20L) & 0x3F)
                    .setSecond((int)(time >> 26L) & 0x3F)
                    .getDate());
            position.setLongitude(buf.readIntLE() * 1.0E-7D);
            position.setLatitude(buf.readIntLE() * 1.0E-7D);
            position.setSpeed(UnitsConverter.knotsFromCps(buf.readUnsignedShortLE()));
            position.setCourse((buf.readUnsignedByte() * 2));
            position.setAltitude(buf.readShortLE());
            buf.readUnsignedShortLE();
            buf.readUnsignedByte();
            int event = buf.readUnsignedByte();
            position.set("event", Integer.valueOf(event));
            position.setValid(BitUtil.check(buf.readByte(), 0));
            position.set("input", Long.valueOf(buf.readUnsignedIntLE()));
            position.set("output", Integer.valueOf(buf.readUnsignedShortLE()));
            for (int i = 1; i <= 5; i++)
                position.set("adc" + i, Short.valueOf(buf.readShortLE()));
            position.set("deviceTemp", Byte.valueOf(buf.readByte()));
            buf.readShortLE();
            buf.readShortLE();
            buf.readShortLE();
            buf.skipBytes(8);
            position.set("pdop", Double.valueOf(buf.readUnsignedShortLE() * 0.01D));
            buf.skipBytes(2);
            buf.readUnsignedShortLE();
            if (event != 21)
                positions.add(position);
        }
        return positions.isEmpty() ? null : positions;
    }

    private List<Position> decodeStandard(Channel channel, SocketAddress remoteAddress, ByteBuf buf) {
        DeviceSession deviceSession = getDeviceSession(channel, remoteAddress, new String[0]);
        LOGGER.info("Inside decodeStandard method :: DeviceSession :: {}", deviceSession);
        if (deviceSession == null) return null;

        List<Position> positions = new LinkedList<>();
        LOGGER.info("Positions :: {}", positions);

        while (buf.isReadable()) {
            int recordEnd = buf.readerIndex() + buf.readUnsignedShortLE();
            LOGGER.info("RecordEnd :: {}", recordEnd);

            Position position = new Position(getProtocolName());
            LOGGER.info("Position Before Population :: {}", position);

            position.setDeviceId(deviceSession.getDeviceId());
            position.set("index", Long.valueOf(buf.readUnsignedIntLE()));
            position.setDeviceTime(new Date(1356998400000L + buf.readUnsignedIntLE() * 1000L));

            LOGGER.info("Position After Population :: {}", position);

            int event = buf.readUnsignedByte();
            if (event == 11)
                position.setDeviceTime(new Date());
            position.set("event", Integer.valueOf(event));
            LOGGER.info("DmtProtocolDecoder - The value of event is: {}, multipying it by 2: {}", Integer.valueOf(event), 2 * Integer.valueOf(event));
            while (buf.readerIndex() < recordEnd) {
                int fieldId = buf.readUnsignedByte();
                int fieldLength = buf.readUnsignedByte();
                int fieldEnd = buf.readerIndex() + ((fieldLength == 255) ? buf.readUnsignedShortLE() : fieldLength);
                if (fieldId == 0) {
                    position.setFixTime(new Date(1356998400000L + buf.readUnsignedIntLE() * 1000L));
                    if (event == 11)
                        position.setFixTime(position.getDeviceTime());
                    position.setLatitude(buf.readIntLE() * 1.0E-7D);
                    position.setLongitude(buf.readIntLE() * 1.0E-7D);
                    position.setAltitude(buf.readShortLE());
                    position.setSpeed(UnitsConverter.knotsFromCps(buf.readUnsignedShortLE()));
                    buf.readUnsignedByte();
                    position.setCourse((buf.readUnsignedByte() * 2));
                    position.set("pdop", Double.valueOf(buf.readUnsignedByte() * 0.1D));
                    position.setAccuracy(buf.readUnsignedByte());
                    int gpsStatus = buf.readUnsignedByte();
                    position.setValid((gpsStatus != 0));
                    position.set("gpsStatusB0", Boolean.valueOf(BitUtil.check(gpsStatus, 0)));
                    position.set("gpsStatusB1", Boolean.valueOf(BitUtil.check(gpsStatus, 1)));
                    position.set("gpsStatusB2", Boolean.valueOf(BitUtil.check(gpsStatus, 2)));
                } else if (fieldId == 2) {
                    int input = buf.readIntLE();
                    int output = buf.readUnsignedShortLE();
                    int status = buf.readUnsignedShortLE();
                    position.set("ignition", Boolean.valueOf(BitUtil.check(input, 0)));
                    for (int i = 1; i < 32; i++)
                        position.set("in" + i, Boolean.valueOf(BitUtil.check(input, i)));
                    position.set("output", Integer.valueOf(output));
                    position.set("status", Integer.valueOf(status));
                } else if (fieldId == 6) {
                    while (buf.readerIndex() < fieldEnd) {
                        int number = buf.readUnsignedByte();
                        switch (number) {
                            case 1:
                                position.set("battery", Double.valueOf(buf.readUnsignedShortLE() * 0.001D));
                                continue;
                            case 2:
                                position.set("power", Double.valueOf(buf.readUnsignedShortLE() * 0.01D));
                                continue;
                            case 3:
                                position.set("deviceTemp", Double.valueOf(buf.readShortLE() * 0.01D));
                                continue;
                            case 4:
                                position.set("rssi", Integer.valueOf(buf.readUnsignedShortLE()));
                                continue;
                            case 5:
                                position.set("solarPower", Double.valueOf(buf.readUnsignedShortLE() * 0.001D));
                                continue;
                        }
                        position.set("io" + number, Integer.valueOf(buf.readUnsignedShortLE()));
                    }
                } else if (fieldId == 26) {
                    position.set("tripOdometer", Long.valueOf(buf.readUnsignedIntLE()));
                    position.set("tripHours", Long.valueOf(buf.readUnsignedIntLE() * 1000L));
                } else if (fieldId == 27) {
                    position.set("odometer", Long.valueOf(buf.readUnsignedIntLE()));
                    position.set("hours", Long.valueOf(buf.readUnsignedIntLE() * 1000L));
                }
                buf.readerIndex(fieldEnd);
            }
            if (position.getFixTime() == null)
                getLastLocation(position, position.getDeviceTime());
            if (event != 21)
                positions.add(position);
        }
        if (!positions.isEmpty()) {
            for(Position position: positions) {
                int count = 0;
                LOGGER.info("Inside DmtProtocolDecoder, Position{} Object decoded: {}", count++, position);
            }
        }
        return positions.isEmpty() ? null : positions;
    }

    protected Object decode(Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {
        LOGGER.info("Inside decode DmtProtocolDecoder Channel :: {} :: SocketAddress :: {} :: Message :: {}", channel.getClass().getSimpleName(), remoteAddress, msg);

        ByteBuf buf = (ByteBuf)msg;
        assert buf == null : "Byte Buffer ids null";

        LOGGER.info("Byte Buf In String :: {}", buf.toString(StandardCharsets.UTF_8));

        buf.skipBytes(2);
        int type = buf.readUnsignedByte();
        LOGGER.info("Type :: {}", type);

        int length = buf.readUnsignedShortLE();
        LOGGER.info("Length :: {}", length);

        if (type == 0) {
            buf.readUnsignedIntLE();
            DeviceSession deviceSession = getDeviceSession(channel, remoteAddress, new String[] { buf.readSlice(15).toString(StandardCharsets.US_ASCII) });
            LOGGER.info("DeviceSession :: {}", deviceSession);

            ByteBuf response = Unpooled.buffer();
            LOGGER.info("Response Before Checks :: {}", response.toString(StandardCharsets.UTF_8));

            if (length == 51) {
                response.writeByte(0);
                response.writeIntLE(0);
            } else {
                response.writeIntLE((int)((System.currentTimeMillis() - 1356998400000L) / 1000L));
                response.writeIntLE((deviceSession != null) ? 0 : 1);
            }
            LOGGER.info("Response After Checks :: {}", response.toString(StandardCharsets.UTF_8));

            sendResponse(channel, 1, response);
        } else if (type == 5) {
            ByteBuf response = Unpooled.buffer(0);
            response.writeByte(1);
            sendResponse(channel, 6, response);
        } else if (type == 20) {
            ByteBuf response = Unpooled.buffer(0);
            response.writeBytes(new byte[12]);
            sendResponse(channel, 21, response);
        } else if (type == 34) {
            sendResponse(channel, 35, (ByteBuf)null);
        } else {
            if (type == 16)
                return decodeFixed64(channel, remoteAddress, buf);
            if (type == 4)
                return decodeStandard(channel, remoteAddress, buf);
        }
        return null;
    }
}
