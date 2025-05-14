package org.traccar.protocol;
import org.traccar.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
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

    private String readId(ByteBuf buf) {
        return buf.readSlice(15).toString(StandardCharsets.US_ASCII);
    }

    @Override
    protected Object decode(Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        LOGGER.info("Inside CellCatProtocolDecode");

        if (buf.readableBytes() == 17 && buf.getUnsignedByte(0) == 0x55 && buf.getUnsignedByte(1) == MSG_ID_PACKET) {
            buf.skipBytes(2); // Skip header and type
            String deviceId = readId(buf);
            LOGGER.info("Received registration ID: {}", deviceId);
            getDeviceSession(channel, remoteAddress, deviceId);

            if (channel != null) {
                ByteBuf response = Unpooled.buffer();
                response.writeByte(MSG_ID_PACKET);
                response.writeByte(0x01); // Acknowledge OK
                channel.writeAndFlush(new NetworkMessage(response, remoteAddress));
                LOGGER.info("Sent registration ACK for ID: {}", deviceId);
            }
            return null;
        }

        if (buf.readableBytes() != 30 || buf.getUnsignedByte(0) != 0x55 || !validateChecksum(buf)) {
            LOGGER.info("Failing check");
            return null;
        }

        int command = buf.getUnsignedByte(1);
        LOGGER.info("Command: {}", command);

        switch (command) {
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

        buf.readerIndex(2);
        buf.skipBytes(4); // serial
        position.set("packetNumber", buf.readUnsignedShort());
        position.set("batteryVoltage", buf.readUnsignedShort());
        position.set("batteryLevel", buf.readUnsignedByte());
        position.set("externalVoltage", buf.readUnsignedShort());
        position.set("temperature", buf.readShort() * 0.1);
        buf.skipBytes(2);
        position.set("rssi", buf.readShort());
        position.set("aband", buf.readUnsignedByte());
        position.set("earfcn", buf.readUnsignedShort());
        position.set("retryCount", buf.readUnsignedByte());

        return Collections.singletonList(position);
    }
}
