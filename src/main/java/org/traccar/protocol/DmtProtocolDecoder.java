package org.traccar.protocol;

import com.google.common.io.ByteSource;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

import java.io.InputStream;
import java.math.BigInteger;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.*;
import org.traccar.helper.BitUtil;
import org.traccar.helper.DateBuilder;
import org.traccar.helper.UnitsConverter;
import org.traccar.model.Position;
import org.traccar.model.WiFiData;

/**
 * The DmtProtocolDecoder class is responsible for decoding and parsing messages
 * from the DMT device protocol. It extends functionality from the base protocol
 * decoder to handle specific DMT protocol message types and formats.
 * <p>
 * This decoder supports the following message types:
 * - MSG_HELLO
 * - MSG_HELLO_RESPONSE
 * - MSG_DATA_RECORD
 * - MSG_COMMIT
 * - MSG_COMMIT_RESPONSE
 * - MSG_DATA_RECORD_64
 * - MSG_CANNED_REQUEST_1
 * - MSG_CANNED_RESPONSE_1
 * - MSG_CANNED_REQUEST_2
 * - MSG_CANNED_RESPONSE_2
 * <p>
 * The decoding process includes handling fixed 64-bit data record parsing, standard
 * message parsing, processing Wi-Fi data scans, and cell tower scan data interpretation.
 * The decoder interacts with a communication channel, processes incoming data, and
 * sends appropriate responses or produces positional and event data when necessary.
 */
public class DmtProtocolDecoder extends BaseProtocolDecoder {
    private static final Logger LOGGER = LoggerFactory.getLogger(DmtProtocolDecoder.class);
    private final ExecutorService executorService = Executors.newFixedThreadPool(5); // Thread pool for async tasks
    private static final HexDataStorageHandler hexDataStorage = new HexDataStorageHandler(); // Initialize storage helper
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
//        LOGGER.info("DmtProtocolDecoder initialized");
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
        if (deviceSession == null) return null;
        List<Position> positions = new LinkedList<>();
        while (buf.readableBytes() >= 64) {
            Position position = new Position(getProtocolName());
            position.setDeviceId(deviceSession.getDeviceId());
            buf.readByte();
            position.set("index", Long.valueOf(buf.readUnsignedIntLE()));
            long time = buf.readUnsignedIntLE();
            position.setTime((new DateBuilder()).setYear((int) (2000L + (time & 0x3FL))).setMonth((int) (time >> 6L) & 0xF).setDay((int) (time >> 10L) & 0x1F).setHour((int) (time >> 15L) & 0x1F).setMinute((int) (time >> 20L) & 0x3F).setSecond((int) (time >> 26L) & 0x3F).getDate());
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
            if (event != 21) positions.add(position);
        }
        return positions.isEmpty() ? null : positions;
    }

    private List<Position> decodeStandard(Channel channel, SocketAddress remoteAddress, ByteBuf buf, String hexData) {
        DeviceSession deviceSession = getDeviceSession(channel, remoteAddress, new String[0]);
//        LOGGER.info("Inside decodeStandard method :: DeviceSession :: {}", deviceSession);
        if (deviceSession == null) return null;

        List<Position> positions = new LinkedList<>();
//        LOGGER.info("Positions :: {}", positions);
        Long sequenceNo = null;
        String uniqueId = null;
        //LOGGER.info("The HEX data is :: toString :: {}, hexDump :: {}", hexData, testHex);
        while (buf.isReadable()) {
            int recordEnd = buf.readerIndex() + buf.readUnsignedShortLE();
//            LOGGER.info("RecordEnd :: {}", recordEnd);

            Position position = new Position(getProtocolName());
//            LOGGER.info("Position Before Population :: {}", position);
            position.setDeviceId(deviceSession.getDeviceId());
            uniqueId = Context.getIdentityManager().getById(position.getDeviceId()).getUniqueId();
            position.set("index", Long.valueOf(buf.readUnsignedIntLE()));
            sequenceNo = (Long) position.getAttributes().get("index");
            position.setDeviceTime(new Date(1356998400000L + buf.readUnsignedIntLE() * 1000L));

            int event = buf.readUnsignedByte();
            if (event == 11) position.setDeviceTime(new Date());
            position.set("event", Integer.valueOf(event));
            while (buf.readerIndex() < recordEnd) {
                int fieldId = buf.readUnsignedByte();
//                LOGGER.info("Field ID: {}", fieldId);
                int fieldLength = buf.readUnsignedByte();
//                LOGGER.info("Field Length: {}", fieldLength);
                int fieldEnd = buf.readerIndex() + ((fieldLength == 255) ? buf.readUnsignedShortLE() : fieldLength);
                if (fieldId == 0) {
                    position.setFixTime(new Date(1356998400000L + buf.readUnsignedIntLE() * 1000L));
                    if (event == 11) position.setFixTime(position.getDeviceTime());
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
//                    position.set("tripStatus", BitUtil.check(status, 0));               // b0
//                    position.set("internalBatteryGood", BitUtil.check(status, 1));      // b1
//                    position.set("externalPowerGood", BitUtil.check(status, 2));        // b2
//                    position.set("gsmConnected", BitUtil.check(status, 3));             // b3
//                    position.set("shuntingFromBattery", BitUtil.check(status, 4));      // b4
//                    position.set("externalPowerEnabled", BitUtil.check(status, 5));     // b5
//                    position.set("magnetAbsent", BitUtil.check(status, 6));             // b6
//                    position.set("recoveryMode", BitUtil.check(status, 7));             // b7
//                    for (int i = 8; i < 16; i++) {
//                        position.set("deviceStatusBit" + i, BitUtil.check(status, i));
//                    }
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
                } else if (fieldId == 25) {
                    //In JavaScript Decoder WiFi Data is parsed when fieldID == 25
                    List<WiFiData> wifiDatas = parseWiFiDataScan(buf, fieldLength);
                    LOGGER.info("Inside WiFi Case 25");
                    if (!wifiDatas.isEmpty())
                        position.set("wifiData", wifiDatas.toString());
                } else if (fieldId == 26) {
                    position.set("tripOdometer", Long.valueOf(buf.readUnsignedIntLE()));
                    position.set("tripHours", Long.valueOf(buf.readUnsignedIntLE() * 1000L));
                } else if (fieldId == 27) {
                    position.set("odometer", Long.valueOf(buf.readUnsignedIntLE()));
                    position.set("hours", Long.valueOf(buf.readUnsignedIntLE() * 1000L));
                } else if (fieldId == 28) {
                    LOGGER.info("Inside CellTower Case 28");
                    List<Map<String, Object>> cellTowers = parseCellTowerScan(buf, fieldLength);
                    LOGGER.info("Decoded CellTower Case 28: {}", cellTowers.toString());
                    position.set("cellTowers", cellTowers.toString());
                } else if (fieldId == 33) {
                    LOGGER.info("Inside CellTower Case 33");
                    Map<String, Object> detailedTower = parseDetailedCellTowerScan(buf, fieldLength);
                    LOGGER.info("Decoded CellTower Case 33: {}", detailedTower.toString());
                    position.set("detailedTower", detailedTower.toString());
                } else if (fieldId == 36) {
                    LOGGER.info("Inside CellTower Case 36");
                    LOGGER.info("Field ID: {}, Field Length: {}", fieldId, fieldLength);
                    Map<String, Object> multiTower = parseMultiCellTowerScan(buf.readSlice(fieldLength));
                    LOGGER.info("Decoded CellTower Case 36: {}", multiTower.toString());
                    position.set("multiTower", multiTower.toString());
                }

                buf.readerIndex(fieldEnd);
            }
            if (position.getFixTime() == null) getLastLocation(position, position.getDeviceTime());
            if (event != 21) positions.add(position);
        }
        if (!positions.isEmpty()) {
            for (Position position : positions) {
                LOGGER.info("Position Object decoded: {}", position);
            }
        }
        String decodedData = positions.toString();
        // Asynchronously store the data
        Long finalIndex = sequenceNo;
        String finalUniqueId = uniqueId;
        executorService.submit(() -> {
            try {
                hexDataStorage.storeHexAndDecodedData(hexData, decodedData, finalIndex, finalUniqueId);
            } catch (Exception e) {
                // Log any errors during the database operation
                LOGGER.error("Error storing data", e);
            }
        });
        return positions.isEmpty() ? null : positions;
    }

    protected Object decode(Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {
//        LOGGER.info("Inside decode DmtProtocolDecoder Channel :: {} :: SocketAddress :: {} :: Message :: {}", channel.getClass().getSimpleName(), remoteAddress, msg);

        ByteBuf buf = (ByteBuf) msg;
        String hexData = ByteBufUtil.hexDump(buf);
        buf.skipBytes(2);
        int type = buf.readUnsignedByte();
//        LOGGER.info("Type :: {}", type);

        int length = buf.readUnsignedShortLE();
//        LOGGER.info("Length :: {}", length);
        if (type == 0) {
            buf.readUnsignedIntLE();
            DeviceSession deviceSession = getDeviceSession(channel, remoteAddress, new String[]{buf.readSlice(15).toString(StandardCharsets.US_ASCII)});
//            LOGGER.info("DeviceSession :: {}", deviceSession);

            ByteBuf response = Unpooled.buffer();
//            LOGGER.info("Response Before Checks :: {}", response.toString(StandardCharsets.UTF_8));

            if (length == 51) {
                response.writeByte(0);
                response.writeIntLE(0);
            } else {
                response.writeIntLE((int) ((System.currentTimeMillis() - 1356998400000L) / 1000L));
                response.writeIntLE((deviceSession != null) ? 0 : 1);
            }
//            LOGGER.info("Response After Checks :: {}", response.toString(StandardCharsets.UTF_8));

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
            sendResponse(channel, 35, (ByteBuf) null);
        } else {
            if (type == 16) return decodeFixed64(channel, remoteAddress, buf);
            if (type == 4) return decodeStandard(channel, remoteAddress, buf, hexData);
        }
        return null;
    }

    private List<WiFiData> parseWiFiDataScan(ByteBuf buf, int length) {
        List<WiFiData> wiFiDatas = new LinkedList<>();

        int dataFieldLength = 8; // Fixed length for each Wi-Fi data entry
        int maxEntries = 30; // Maximum number of entries

        for (int i = 0; i < length / dataFieldLength; i++) {
            if (i >= maxEntries) break;

            // Parse MAC Address (6 bytes)
            StringBuilder macAddress = new StringBuilder();
            for (int j = 0; j < 6; j++) {
                macAddress.append(String.format("%02X", buf.readUnsignedByte()));
                if (j < 5) macAddress.append(":");
            }
            // Parse Signal Strength (1 byte)
            int unsignedSignalStrength = buf.readUnsignedByte();
            int signalStrength = unsignedToSigned(unsignedSignalStrength);

            // Parse Channel Number (extract the first 4 bits)
            int channelNum = buf.readUnsignedByte() & 0x0F;

            // Create Wi-Fi location object and add to the list
            WiFiData wiFiData = new WiFiData(macAddress.toString(), signalStrength, channelNum);
            LOGGER.info("WiFi data parsed as :: {}", wiFiData);
            wiFiDatas.add(wiFiData);
        }

        return wiFiDatas;
    }

    /**
     * Converts an unsigned byte value to its signed equivalent.
     * Values > 127 are adjusted to the range -128 to -1.
     */
    private int unsignedToSigned(int unsignedValue) {
        return unsignedValue > 127 ? unsignedValue - 256 : unsignedValue;
    }

    private List<Map<String, Object>> parseCellTowerScan(ByteBuf buf, int length) {
        List<Map<String, Object>> cellTowers = new LinkedList<>();
        int dataFieldLength = 10; // Fixed length for each tower entry
        int maxEntries = 20; // Limit to 20 entries

        for (int i = 0; i < length / dataFieldLength; i++) {
            if (i >= maxEntries) break;

            Map<String, Object> tower = new HashMap<>();
            tower.put("cellId", buf.readUnsignedIntLE());
            tower.put("locationAreaCode", buf.readUnsignedShortLE());
            tower.put("mobileCountryCode", buf.readUnsignedShortLE());
            tower.put("mobileNetworkCode", buf.readUnsignedShortLE());

            cellTowers.add(tower);
        }
        return cellTowers;
    }

    private Map<String, Object> parseDetailedCellTowerScan(ByteBuf buf, int length) {
        Map<String, Object> detailedTower = new HashMap<>();

        int towerLength = buf.readByte() & 0x1F; // Extract 5 bits for tower length
        detailedTower.put("towerRAT", (buf.readByte() >> 2) & 0x01); // Extract RAT (Radio Access Technology)
        buf.skipBytes(1); // Skip reserved bits

        detailedTower.put("cellId", buf.readUnsignedIntLE());
        LOGGER.info("CellID: {}", detailedTower.get("cellId"));
        detailedTower.put("locationAreaCode", buf.readUnsignedShortLE());
        detailedTower.put("mobileCountryCode", buf.readUnsignedShortLE());
        detailedTower.put("mobileNetworkCode", buf.readUnsignedShortLE());
        detailedTower.put("timingAdvance", buf.readUnsignedShortLE());

        List<Map<String, Object>> towerData = new LinkedList<>();
        while (buf.readableBytes() >= towerLength) {
            Map<String, Object> tower = new HashMap<>();
            tower.put("downlinkEARFCN", buf.readUnsignedShortLE());
            tower.put("physicalCellId", buf.readUnsignedShortLE());
            tower.put("rsrp", buf.readShortLE());
            tower.put("rsrq", (int) buf.readByte());
            tower.put("timingDifference", buf.readUnsignedIntLE());

            towerData.add(tower);
        }
        detailedTower.put("towerData", towerData);

        return detailedTower;
    }

    private Map<String, Object> parseMultiCellTowerScan(ByteBuf buf) {
        Map<String, Object> multiTower = new HashMap<>();


        List<Map<String, Object>> towerDataList = new LinkedList<>();

        int towerFieldLength = buf.readUnsignedByte(); // First byte
        int startIndex = buf.readerIndex();
        int endIndex = buf.writerIndex(); // Use writerIndex since it's a slice

        while (buf.readerIndex() + towerFieldLength <= endIndex) {
            int towerStart = buf.readerIndex();
            LOGGER.info("Parsing tower at byte index {}", buf.readerIndex());
            Map<String, Object> tower = new HashMap<>();

            int validFlags1 = buf.readUnsignedByte(); // byte 0
            int validFlags2 = buf.readUnsignedByte(); // byte 1
            int ratType = (validFlags2 >> 4) & 0x0F;

            tower.put("validFlagCellId", (validFlags1 & 0x01) != 0);
            tower.put("validFlagLac", (validFlags1 & 0x02) != 0);
            tower.put("validFlagMccMnc", (validFlags1 & 0x04) != 0);
            tower.put("validFlagTimingAdvance", (validFlags1 & 0x08) != 0);
            tower.put("validFlagRsrp", (validFlags1 & 0x10) != 0);
            tower.put("validFlagRsrq", (validFlags1 & 0x20) != 0);
            tower.put("validFlagEarfcn", (validFlags1 & 0x40) != 0);
            tower.put("validFlagPhysicalCellId", (validFlags1 & 0x80) != 0);
            tower.put("validFlagTimingDiff", (validFlags2 & 0x01) != 0);
            tower.put("isServingCell", (validFlags2 & 0x02) != 0);

            switch (ratType) {
                case 0:
                    tower.put("ratType", "0: CAT-M1");
                    break;
                case 1:
                    tower.put("ratType", "1: NB-IoT");
                    break;
                case 2:
                    tower.put("ratType", "2: GSM");
                    break;
                case 3:
                    tower.put("ratType", "3: UMTS");
                    break;
                case 4:
                    tower.put("ratType", "4: LTE");
                    break;
                default:
                    tower.put("ratType", ratType);
                    break;
            }

            if ((validFlags1 & 0x01) != 0) {
                tower.put("cellId", buf.readUnsignedIntLE());
            } else {
                buf.skipBytes(4);
            }

            if ((validFlags1 & 0x02) != 0) {
                tower.put("lac", buf.readUnsignedShortLE());
            } else {
                buf.skipBytes(2);
            }

            if ((validFlags1 & 0x04) != 0) {
                tower.put("mcc", buf.readUnsignedShortLE());
                tower.put("mnc", buf.readUnsignedShortLE());
            } else {
                buf.skipBytes(4);
            }

            if ((validFlags1 & 0x08) != 0) {
                tower.put("timingAdvance", buf.readUnsignedShortLE());
            } else {
                buf.skipBytes(2);
            }

            if ((validFlags1 & 0x10) != 0) {
                tower.put("rsrp", buf.readShortLE());
            } else {
                buf.skipBytes(2);
            }

            if ((validFlags1 & 0x20) != 0) {
                tower.put("rsrq", (int) buf.readByte());
            } else {
                buf.skipBytes(1);
            }

            if ((validFlags1 & 0x40) != 0) {
                int earfcn = (buf.readUnsignedByte() & 0xFF)
                        | ((buf.readUnsignedByte() & 0xFF) << 8)
                        | ((buf.readUnsignedByte() & 0xFF) << 16);
                tower.put("downlinkEARFCN", earfcn);
            } else {
                buf.skipBytes(3);
            }

            if ((validFlags1 & 0x80) != 0) {
                tower.put("physicalCellId", buf.readUnsignedShortLE());
            } else {
                buf.skipBytes(2);
            }

            if ((validFlags2 & 0x01) != 0) {
                tower.put("timingDifference", buf.readIntLE());
            } else {
                buf.skipBytes(4);
            }

            // Ensure proper alignment: skip over unprocessed bytes if field logic missed anything not required after debugging
//            int bytesRead = buf.readerIndex() - towerStart;
//            if (bytesRead < towerFieldLength) {
//                buf.skipBytes(towerFieldLength - bytesRead);
//            } else if (bytesRead > towerFieldLength) {
//                // In case we accidentally over-read (should not happen), reset to expected position
//                buf.readerIndex(towerStart + towerFieldLength);
//            }

            towerDataList.add(tower);
            LOGGER.info("Finished parsing towers, total parsed: {}", towerDataList.size());

        }

        multiTower.put("towerFieldLength", towerFieldLength);
        multiTower.put("towerData", towerDataList);
        return multiTower;
    }

}
