/*     */ package org.traccar.protocol;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import io.netty.channel.Channel;
/*     */ import java.net.SocketAddress;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.Date;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.*;
/*     */
/*     */
/*     */
/*     */ import org.traccar.helper.BitUtil;
/*     */ import org.traccar.helper.DateBuilder;
/*     */ import org.traccar.helper.UnitsConverter;
/*     */ import org.traccar.model.Position;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DmtProtocolDecoder
/*     */   extends BaseProtocolDecoder
/*     */ {
/*     */   public static final int MSG_HELLO = 0;
/*     */   public static final int MSG_HELLO_RESPONSE = 1;
/*     */   public static final int MSG_DATA_RECORD = 4;
/*     */   public static final int MSG_COMMIT = 5;
/*     */   public static final int MSG_COMMIT_RESPONSE = 6;
/*     */   public static final int MSG_DATA_RECORD_64 = 16;
/*     */   public static final int MSG_CANNED_REQUEST_1 = 20;
/*     */   public static final int MSG_CANNED_RESPONSE_1 = 21;
/*     */   public static final int MSG_CANNED_REQUEST_2 = 34;
/*     */   public static final int MSG_CANNED_RESPONSE_2 = 35;
/*     */   
/*     */   public DmtProtocolDecoder(Protocol protocol) {
/*  39 */     super(protocol);
/*     */   }
    private static final Logger LOGGER = LoggerFactory.getLogger(DmtProtocolDecoder.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendResponse(Channel channel, int type, ByteBuf content) {
/*  55 */     if (channel != null) {
/*  56 */       ByteBuf response = Unpooled.buffer();
/*  57 */       response.writeByte(2); response.writeByte(85);
/*  58 */       response.writeByte(type);
/*  59 */       response.writeShortLE((content != null) ? content.readableBytes() : 0);
/*  60 */       if (content != null) {
/*  61 */         response.writeBytes(content);
/*  62 */         content.release();
/*     */       } 
/*  64 */       channel.writeAndFlush(new NetworkMessage(response, channel.remoteAddress()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Position> decodeFixed64(Channel channel, SocketAddress remoteAddress, ByteBuf buf) {
/*  70 */     DeviceSession deviceSession = getDeviceSession(channel, remoteAddress, new String[0]);
/*  71 */     if (deviceSession == null) {
/*  72 */       return null;
/*     */     }
/*     */     
/*  75 */     List<Position> positions = new LinkedList<>();
/*     */     
/*  77 */     while (buf.readableBytes() >= 64) {
/*  78 */       Position position = new Position(getProtocolName());
/*  79 */       position.setDeviceId(deviceSession.getDeviceId());
/*     */       
/*  81 */       buf.readByte();
/*     */       
/*  83 */       position.set("index", Long.valueOf(buf.readUnsignedIntLE()));
/*     */       
/*  85 */       long time = buf.readUnsignedIntLE();
/*  86 */       position.setTime((new DateBuilder())
/*  87 */           .setYear((int)(2000L + (time & 0x3FL)))
/*  88 */           .setMonth((int)(time >> 6L) & 0xF)
/*  89 */           .setDay((int)(time >> 10L) & 0x1F)
/*  90 */           .setHour((int)(time >> 15L) & 0x1F)
/*  91 */           .setMinute((int)(time >> 20L) & 0x3F)
/*  92 */           .setSecond((int)(time >> 26L) & 0x3F)
/*  93 */           .getDate());
/*     */       
/*  95 */       position.setLongitude(buf.readIntLE() * 1.0E-7D);
/*  96 */       position.setLatitude(buf.readIntLE() * 1.0E-7D);
/*  97 */       position.setSpeed(UnitsConverter.knotsFromCps(buf.readUnsignedShortLE()));
/*  98 */       position.setCourse((buf.readUnsignedByte() * 2));
/*  99 */       position.setAltitude(buf.readShortLE());
/*     */       
/* 101 */       buf.readUnsignedShortLE();
/* 102 */       buf.readUnsignedByte();
/*     */       
/* 104 */       int event = buf.readUnsignedByte();
/* 105 */       position.set("event", Integer.valueOf(event));
/*     */       
/* 107 */       position.setValid(BitUtil.check(buf.readByte(), 0));
/*     */       
/* 109 */       position.set("input", Long.valueOf(buf.readUnsignedIntLE()));
/* 110 */       position.set("output", Integer.valueOf(buf.readUnsignedShortLE()));
/*     */       
/* 112 */       for (int i = 1; i <= 5; i++) {
/* 113 */         position.set("adc" + i, Short.valueOf(buf.readShortLE()));
/*     */       }
/*     */       
/* 116 */       position.set("deviceTemp", Byte.valueOf(buf.readByte()));
/*     */       
/* 118 */       buf.readShortLE();
/* 119 */       buf.readShortLE();
/* 120 */       buf.readShortLE();
/*     */       
/* 122 */       buf.skipBytes(8);
/*     */       
/* 124 */       position.set("pdop", Double.valueOf(buf.readUnsignedShortLE() * 0.01D));
/*     */       
/* 126 */       buf.skipBytes(2);
/*     */       
/* 128 */       buf.readUnsignedShortLE();
/*     */       
/* 130 */       if (event != 21) {
/* 131 */         positions.add(position);
/*     */       }
/*     */     } 
/*     */     
/* 135 */     return positions.isEmpty() ? null : positions;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Position> decodeStandard(Channel channel, SocketAddress remoteAddress, ByteBuf buf) {
/* 140 */     DeviceSession deviceSession = getDeviceSession(channel, remoteAddress, new String[0]);
/* 141 */     if (deviceSession == null) {
/* 142 */       return null;
/*     */     }
/*     */     
/* 145 */     List<Position> positions = new LinkedList<>();
/*     */     
/* 147 */     while (buf.isReadable()) {
/* 148 */       int recordEnd = buf.readerIndex() + buf.readUnsignedShortLE();
/*     */       
/* 150 */       Position position = new Position(getProtocolName());
/* 151 */       position.setDeviceId(deviceSession.getDeviceId());
/*     */       
/* 153 */       position.set("index", Long.valueOf(buf.readUnsignedIntLE()));
/*     */       
/* 155 */       position.setDeviceTime(new Date(1356998400000L + buf.readUnsignedIntLE() * 1000L));
/*     */       
/* 157 */       int event = buf.readUnsignedByte();
/* 158 */       if (event == 11) {
/* 159 */         position.setDeviceTime(new Date());
/*     */       }
/* 161 */       position.set("event", Integer.valueOf(event));
/*     */       
/* 163 */       while (buf.readerIndex() < recordEnd) {
/*     */         
/* 165 */         int fieldId = buf.readUnsignedByte();
/* 166 */         int fieldLength = buf.readUnsignedByte();
/* 167 */         int fieldEnd = buf.readerIndex() + ((fieldLength == 255) ? buf.readUnsignedShortLE() : fieldLength);
/*     */         
/* 169 */         if (fieldId == 0) {
/*     */           
/* 171 */           position.setFixTime(new Date(1356998400000L + buf.readUnsignedIntLE() * 1000L));
/* 172 */           if (event == 11) {
/* 173 */             position.setFixTime(position.getDeviceTime());
/*     */           }
/*     */           
/* 176 */           position.setLatitude(buf.readIntLE() * 1.0E-7D);
/* 177 */           position.setLongitude(buf.readIntLE() * 1.0E-7D);
/* 178 */           position.setAltitude(buf.readShortLE());
/* 179 */           position.setSpeed(UnitsConverter.knotsFromCps(buf.readUnsignedShortLE()));
/*     */           
/* 181 */           buf.readUnsignedByte();
/*     */           
/* 183 */           position.setCourse((buf.readUnsignedByte() * 2));
/*     */           
/* 185 */           position.set("pdop", Double.valueOf(buf.readUnsignedByte() * 0.1D));
/*     */           
/* 187 */           position.setAccuracy(buf.readUnsignedByte());
/* 188 */           int gpsStatus = buf.readUnsignedByte();
/* 189 */           position.setValid((gpsStatus != 0));
/* 190 */           position.set("gpsStatusB0", Boolean.valueOf(BitUtil.check(gpsStatus, 0)));
/* 191 */           position.set("gpsStatusB1", Boolean.valueOf(BitUtil.check(gpsStatus, 1)));
/* 192 */           position.set("gpsStatusB2", Boolean.valueOf(BitUtil.check(gpsStatus, 2)));
/*     */         }
/* 194 */         else if (fieldId == 2) {
/*     */           
/* 196 */           int input = buf.readIntLE();
/* 197 */           int output = buf.readUnsignedShortLE();
/* 198 */           int status = buf.readUnsignedShortLE();
/*     */           
/* 200 */           position.set("ignition", Boolean.valueOf(BitUtil.check(input, 0)));
/*     */           
/* 202 */           for (int i = 1; i < 32; i++) {
/* 203 */             position.set("in" + i, Boolean.valueOf(BitUtil.check(input, i)));
/*     */           }
/*     */           
/* 206 */           position.set("output", Integer.valueOf(output));
/* 207 */           position.set("status", Integer.valueOf(status));
/*     */         }
/* 209 */         else if (fieldId == 6) {
/*     */           
/* 211 */           while (buf.readerIndex() < fieldEnd) {
/* 212 */             int number = buf.readUnsignedByte();
/* 213 */             switch (number) {
/*     */               case 1:
/* 215 */                 position.set("battery", Double.valueOf(buf.readUnsignedShortLE() * 0.001D));
/*     */                 continue;
/*     */               case 2:
/* 218 */                 position.set("power", Double.valueOf(buf.readUnsignedShortLE() * 0.01D));
/*     */                 continue;
/*     */               case 3:
/* 221 */                 position.set("deviceTemp", Double.valueOf(buf.readShortLE() * 0.01D));
/*     */                 continue;
/*     */               case 4:
/* 224 */                 position.set("rssi", Integer.valueOf(buf.readUnsignedShortLE()));
/*     */                 continue;
/*     */               case 5:
/* 227 */                 position.set("solarPower", Double.valueOf(buf.readUnsignedShortLE() * 0.001D));
/*     */                 continue;
/*     */             } 
/* 230 */             position.set("io" + number, Integer.valueOf(buf.readUnsignedShortLE()));
/*     */           
/*     */           }
/*     */         
/*     */         }
/* 235 */         else if (fieldId == 26) {
/*     */           
/* 237 */           position.set("tripOdometer", Long.valueOf(buf.readUnsignedIntLE()));
/* 238 */           position.set("tripHours", Long.valueOf(buf.readUnsignedIntLE() * 1000L));
/*     */         }
/* 240 */         else if (fieldId == 27) {
/*     */           
/* 242 */           position.set("odometer", Long.valueOf(buf.readUnsignedIntLE()));
/* 243 */           position.set("hours", Long.valueOf(buf.readUnsignedIntLE() * 1000L));
/*     */         } 
/*     */ 
/*     */         
/* 247 */         buf.readerIndex(fieldEnd);
/*     */       } 
/*     */ 
/*     */       
/* 251 */       if (position.getFixTime() == null) {
/* 252 */         getLastLocation(position, position.getDeviceTime());
/*     */       }
/*     */       
/* 255 */       if (event != 21) {
/* 256 */         positions.add(position);
/*     */       }
/*     */     } 
/*     */     DmtProtocolDecoder.LOGGER.info(positions.toString());
/* 260 */     return positions.isEmpty() ? null : positions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object decode(Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {
/* 267 */     ByteBuf buf = (ByteBuf)msg;
/*     */     
/* 269 */     buf.skipBytes(2);
/*     */     
/* 271 */     int type = buf.readUnsignedByte();
/* 272 */     int length = buf.readUnsignedShortLE();
/*     */     
/* 274 */     if (type == 0) {
/*     */       
/* 276 */       buf.readUnsignedIntLE();
/*     */       
/* 278 */       DeviceSession deviceSession = getDeviceSession(channel, remoteAddress, new String[] { buf
/* 279 */             .readSlice(15).toString(StandardCharsets.US_ASCII) });
/*     */       
/* 281 */       ByteBuf response = Unpooled.buffer();
/* 282 */       if (length == 51) {
/* 283 */         response.writeByte(0);
/* 284 */         response.writeIntLE(0);
/*     */       } else {
/* 286 */         response.writeIntLE((int)((System.currentTimeMillis() - 1356998400000L) / 1000L));
/* 287 */         response.writeIntLE((deviceSession != null) ? 0 : 1);
/*     */       } 
/*     */       
/* 290 */       sendResponse(channel, 1, response);
/*     */     }
/* 292 */     else if (type == 5) {
/*     */       
/* 294 */       ByteBuf response = Unpooled.buffer(0);
/* 295 */       response.writeByte(1);
/* 296 */       sendResponse(channel, 6, response);
/*     */     }
/* 298 */     else if (type == 20) {
/*     */       
/* 300 */       ByteBuf response = Unpooled.buffer(0);
/* 301 */       response.writeBytes(new byte[12]);
/* 302 */       sendResponse(channel, 21, response);
/*     */     }
/* 304 */     else if (type == 34) {
/*     */       
/* 306 */       sendResponse(channel, 35, (ByteBuf)null);
/*     */     } else {
/* 308 */       if (type == 16)
/*     */       {
/* 310 */         return decodeFixed64(channel, remoteAddress, buf);
/*     */       }
/* 312 */       if (type == 4)
/*     */       {
/* 314 */         return decodeStandard(channel, remoteAddress, buf);
/*     */       }
/*     */     } 
/*     */     
/* 318 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\protocol\DmtProtocolDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */