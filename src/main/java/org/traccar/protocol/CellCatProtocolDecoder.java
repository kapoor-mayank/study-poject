package org.traccar.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.*;
import org.traccar.helper.UnitsConverter;
import org.traccar.model.Position;

import java.net.SocketAddress;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CellCatProtocolDecoder extends BaseProtocolDecoder {
    private static final Logger LOGGER = LoggerFactory.getLogger(CellCatProtocolDecoder.class);

    public CellCatProtocolDecoder(Protocol protocol) {
        super(protocol);
        LOGGER.info("CellCatProtocolDecoder Initialized");
    }

    private boolean validateChecksum(ByteBuf buf) {
        int sum = 0;
        for (int i = 1; i <= 28; i++) sum += buf.getUnsignedByte(i);
        int checksum = (sum ^ 0xFF) + 1;
        LOGGER.info("Checksum: {}", checksum);
        return checksum == buf.getUnsignedByte(29);
    }

    @Override
    protected Object decode(Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {
        LOGGER.info("Inside CellCatProtocolDecode");
        ByteBuf buf = (ByteBuf) msg;
        if (buf.readableBytes() != 30 || buf.getUnsignedByte(0) != 0x55 || !validateChecksum(buf)) {
            LOGGER.info("Failing check");
            return null;
        }


        int command = buf.getUnsignedByte(1);
        LOGGER.info("Command: {}", command);
        switch (command) {
            case 0x10:
                LOGGER.info("Calling decodeGps()");
                return decodeGps(channel, remoteAddress, buf);
            case 0x11: return decodeAlarm(channel, remoteAddress, buf);
            case 0x12: return decodeStatus(channel, remoteAddress, buf);
            // Future implementation for additional message types.
            default: return null;
        }
    }

    private List<Position> decodeGps(Channel channel, SocketAddress remoteAddress, ByteBuf buf) {
        LOGGER.info("Inside CellCatProtocolDecode decodeGPS before device Session");
        DeviceSession deviceSession = getDeviceSession(channel, remoteAddress);
        LOGGER.info("Inside CellCatProtocolDecode decodeGPS");
//        if (deviceSession == null) {
//            LOGGER.warn("Created test device session for deviceId: {}", 35893803);
//            deviceSession = new DeviceSession(35893803);  // your own fallback
//        }
        List<Position> positions = new LinkedList<>();
        if (deviceSession == null) return null;

        Position position = new Position(getProtocolName());
        position.setDeviceId(deviceSession.getDeviceId());

        buf.readerIndex(2);
        long epoch = buf.readUnsignedInt();
        position.setTime(new Date(epoch * 1000));
        position.setLongitude(buf.readInt() * 1e-7);
        position.setLatitude(buf.readInt() * 1e-7);
        position.setAltitude(buf.readShort());
        position.setSpeed(UnitsConverter.knotsFromCps(buf.readUnsignedShort()));
        position.setCourse(buf.readUnsignedShort() * 0.1);
        int fix = buf.readUnsignedByte();
        position.setValid(fix >= 2);
        position.set("satellites", buf.readUnsignedByte());
        buf.skipBytes(1);
        position.set("hdop", buf.readUnsignedByte() * 0.1);
        position.set("gpsTryCount", buf.readUnsignedByte());

        LOGGER.info("Positions CellCAT: {}", position);
        positions.add(position);
        return positions;
    }

    private List<Position> decodeAlarm(Channel channel, SocketAddress remoteAddress, ByteBuf buf) {
        LOGGER.info("Inside CellCatProtocolDecode Alarm");
        DeviceSession deviceSession = getDeviceSession(channel, remoteAddress);
        List<Position> positions = new LinkedList<>();
        if (deviceSession == null) return null;

        Position position = new Position(getProtocolName());
        position.setDeviceId(deviceSession.getDeviceId());

        buf.readerIndex(2);
        long eventTime = buf.readUnsignedInt();
        position.setTime(new Date(eventTime * 1000));
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
        long rtcTime = buf.readUnsignedInt();
        position.set("rtc", new Date(rtcTime * 1000L).getTime()); // Store as long (timestamp)


        positions.add(position);
        return positions;
    }

    private List<Position> decodeStatus(Channel channel, SocketAddress remoteAddress, ByteBuf buf) {
        LOGGER.info("Inside CellCatProtocolDecode status");
        DeviceSession deviceSession = getDeviceSession(channel, remoteAddress);
        List<Position> positions = new LinkedList<>();
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

        positions.add(position);
        return positions;
    }
}
