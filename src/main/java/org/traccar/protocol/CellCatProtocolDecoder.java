package org.traccar.protocol;
import org.traccar.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.BaseProtocolDecoder;
import org.traccar.NetworkMessage;
import org.traccar.Protocol;
import org.traccar.model.Position;

import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CellCatProtocolDecoder extends BaseProtocolDecoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(CellCatProtocolDecoder.class);

    public static final int MSG_ID_PACKET = 0x01;
    public static final int MSG_GPS = 0x10;
    public static final int MSG_ALARM = 0x11;
    public static final int MSG_STATUS = 0x12;

    public CellCatProtocolDecoder(Protocol protocol) {
        super(protocol);
        LOGGER.info("CellCatProtocolDecoder Initialized");
    }

    private boolean validateChecksum(ByteBuf buf) {
        int sum = 0;
        for (int i = 1; i <= 28; i++) {
            sum += buf.getUnsignedByte(i);
        }
        int checksum = ((sum ^ 0xFF) + 1) & 0xFF;
        LOGGER.info("Checksum (calculated): {}, Expected: {}", checksum, buf.getUnsignedByte(29));
        return checksum == buf.getUnsignedByte(29);
    }

    @Override
    protected Object decode(Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        LOGGER.info("Inside CellCatProtocolDecode");

        if (buf.readableBytes() == 30 && buf.getUnsignedByte(0) == 0x55 && buf.getUnsignedByte(1) == MSG_ID_PACKET) {
            return decodeRegistration(channel, remoteAddress, buf);
        }

        if (buf.readableBytes() != 30 || buf.getUnsignedByte(0) != 0x55 || !validateChecksum(buf)) {
            LOGGER.info("Failing check");
            return null;
        }

        int command = buf.getUnsignedByte(1);
        LOGGER.info("Command: {}", command);

        switch (command) {
            case MSG_ID_PACKET:
                return decodeRegistration(channel, remoteAddress, buf);
            case MSG_GPS:
                return decodeGps(channel, remoteAddress, buf);
            case MSG_ALARM:
                return decodeAlarm(channel, remoteAddress, buf);
            case MSG_STATUS:
                return decodeStatus(channel, remoteAddress, buf);
            default:
                LOGGER.warn("Unknown command: {}", command);
                return null;
        }
    }

    private Object decodeRegistration(Channel channel, SocketAddress remoteAddress, ByteBuf buf) {
        buf.readerIndex(2);

        // Read and store the actual 4-byte serial number
        ByteBuf serialBytes = buf.readSlice(4);
        byte[] serial = new byte[4];
        serialBytes.getBytes(0, serial);

        // Read IMEI from bytes [6..20]
        ByteBuf rawDeviceIdBytes = buf.readSlice(15);
        LOGGER.info("Raw Device ID Bytes: {}", ByteBufUtil.hexDump(rawDeviceIdBytes));
        String deviceId = rawDeviceIdBytes.toString(StandardCharsets.US_ASCII);
        LOGGER.info("Received registration ID in 0x01 command: {}", deviceId);

        getDeviceSession(channel, remoteAddress, deviceId);

        if (channel != null) {
            ByteBuf response = Unpooled.buffer(30);
            response.writeByte(0x55);         // [0] Start byte
            response.writeByte(0x02);         // [1] Command code (ACK)
            response.writeBytes(serial);      // [2–5] Echo back actual 4-byte serial number
            response.writeByte(0x01);         // [6] Result code (success)
            response.writeZero(20);           // [7–26] Reserved
            response.writeByte(0x03);         // [27] Echo: message count
            response.writeByte(0x00);         // [28] Echo: session type

            // Calculate checksum for bytes [1..28]
            int sum = 0;
            for (int i = 1; i <= 28; i++) {
                sum += response.getUnsignedByte(i);
            }
            int checksum = ((sum ^ 0xFF) + 1) & 0xFF;
            response.writeByte(checksum);     // [29] Checksum

            LOGGER.info("Sending ACK (0x02) HEX: {}", ByteBufUtil.hexDump(response));
            channel.writeAndFlush(new NetworkMessage(response, remoteAddress));
        }

        return null;
    }


    private List<Position> decodeGps(Channel channel, SocketAddress remoteAddress, ByteBuf buf) {
        DeviceSession deviceSession = getDeviceSession(channel, remoteAddress);
        if (deviceSession == null) return null;

        Position position = new Position(getProtocolName());
        position.setDeviceId(deviceSession.getDeviceId());

        buf.readerIndex(2);
        position.setTime(new Date(buf.readUnsignedInt() * 1000L));
        position.setLongitude(buf.readInt() * 1e-7);
        position.setLatitude(buf.readInt() * 1e-7);
        position.setAltitude((double) buf.readShort());
        position.setSpeed(buf.readUnsignedShort());
        position.setCourse(buf.readUnsignedShort() * 0.1);
        int fix = buf.readUnsignedByte();
        position.setValid(fix >= 2);
        position.set("satellites", buf.readUnsignedByte());
        buf.skipBytes(1);
        position.set("hdop", buf.readUnsignedByte() * 0.1);
        position.set("gpsTryCount", buf.readUnsignedByte());

        return Collections.singletonList(position);
    }

    private List<Position> decodeAlarm(Channel channel, SocketAddress remoteAddress, ByteBuf buf) {
        DeviceSession deviceSession = getDeviceSession(channel, remoteAddress);
        if (deviceSession == null) return null;

        Position position = new Position(getProtocolName());
        position.setDeviceId(deviceSession.getDeviceId());

        buf.readerIndex(2);
        position.setTime(new Date(buf.readUnsignedInt() * 1000L));
        buf.skipBytes(6);
        position.set("relayStatus", buf.readUnsignedByte());
        position.set("bw1", buf.readUnsignedByte());
        position.set("bw2", buf.readUnsignedByte());
        position.set("light", buf.readUnsignedByte());
        buf.skipBytes(1);
        position.set("vehicleStopped", buf.readUnsignedByte());
        position.set("geofence1", buf.readUnsignedByte());
        position.set("geofence2", buf.readUnsignedByte());
        position.set("agnss", buf.readUnsignedByte());
        position.set("rtc", buf.readUnsignedInt() * 1000L);

        return Collections.singletonList(position);
    }

    private List<Position> decodeStatus(Channel channel, SocketAddress remoteAddress, ByteBuf buf) {
        DeviceSession deviceSession = getDeviceSession(channel, remoteAddress);
        if (deviceSession == null) return null;

        Position position = new Position(getProtocolName());
        position.setDeviceId(deviceSession.getDeviceId());
        // Use current system time if GPS time is not available
        position.setFixTime(new Date(System.currentTimeMillis()));


        buf.readerIndex(6);  // Start after 0x55, command, and 4-byte serial (0-5)

        try {
            position.set("packetNumber", buf.readUnsignedShort());       // [6–7]
            position.set("batteryVoltage", buf.readUnsignedShort());     // [8–9]
            position.set("batteryLevel", buf.readUnsignedByte());        // [10]
            position.set("externalVoltage", buf.readUnsignedShort());    // [11–12]
            position.set("temperature", buf.readShort() * 0.1);          // [13–14]
            buf.skipBytes(2);                                            // [15–16] not used
            position.set("rssi", (double) buf.readShort());              // [17–18]
            position.set("aband", buf.readUnsignedByte());               // [19]
            position.set("earfcn", buf.readUnsignedShort());             // [20–21]
            position.set("retryCount", buf.readUnsignedByte());          // [22]
            buf.skipBytes(6);                                            // [23–28] not used
        } catch (Exception e) {
            LOGGER.error("Error decoding status message: {}", ByteBufUtil.hexDump(buf));
            LOGGER.error("Exception: ", e);
            return null;
        }

        return Collections.singletonList(position);
    }

}
