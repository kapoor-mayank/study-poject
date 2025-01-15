/*     */ package org.traccar.protocol;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import io.netty.channel.Channel;
/*     */ import java.net.SocketAddress;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.Date;
/*     */ import org.traccar.BaseProtocolDecoder;
/*     */ import org.traccar.DeviceSession;
/*     */ import org.traccar.NetworkMessage;
/*     */ import org.traccar.Protocol;
/*     */ import org.traccar.helper.BitUtil;
/*     */ import org.traccar.helper.UnitsConverter;
/*     */ import org.traccar.model.Position;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObdDongleProtocolDecoder
/*     */   extends BaseProtocolDecoder
/*     */ {
/*     */   public static final int MSG_TYPE_CONNECT = 1;
/*     */   public static final int MSG_TYPE_CONNACK = 2;
/*     */   public static final int MSG_TYPE_PUBLISH = 3;
/*     */   public static final int MSG_TYPE_PUBACK = 4;
/*     */   public static final int MSG_TYPE_PINGREQ = 12;
/*     */   public static final int MSG_TYPE_PINGRESP = 13;
/*     */   public static final int MSG_TYPE_DISCONNECT = 14;
/*     */   
/*     */   public ObdDongleProtocolDecoder(Protocol protocol) {
/*  36 */     super(protocol);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void sendResponse(Channel channel, int type, int index, String imei, ByteBuf content) {
/*  48 */     if (channel != null) {
/*  49 */       ByteBuf response = Unpooled.buffer();
/*  50 */       response.writeShort(21845);
/*  51 */       response.writeShort(index);
/*  52 */       response.writeBytes(imei.getBytes(StandardCharsets.US_ASCII));
/*  53 */       response.writeByte(type);
/*  54 */       response.writeShort(content.readableBytes());
/*  55 */       response.writeBytes(content);
/*  56 */       content.release();
/*  57 */       response.writeByte(0);
/*  58 */       response.writeShort(43690);
/*  59 */       channel.writeAndFlush(new NetworkMessage(response, channel.remoteAddress()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object decode(Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {
/*  67 */     ByteBuf buf = (ByteBuf)msg;
/*     */     
/*  69 */     buf.skipBytes(2);
/*  70 */     int index = buf.readUnsignedShort();
/*     */     
/*  72 */     String imei = buf.readSlice(15).toString(StandardCharsets.US_ASCII);
/*  73 */     DeviceSession deviceSession = getDeviceSession(channel, remoteAddress, new String[] { imei });
/*  74 */     if (deviceSession == null) {
/*  75 */       return null;
/*     */     }
/*     */     
/*  78 */     int type = buf.readUnsignedByte();
/*     */     
/*  80 */     buf.readUnsignedShort();
/*     */     
/*  82 */     if (type == 1) {
/*     */       
/*  84 */       ByteBuf response = Unpooled.buffer();
/*  85 */       response.writeByte(1);
/*  86 */       response.writeShort(0);
/*  87 */       response.writeInt(0);
/*  88 */       sendResponse(channel, 2, index, imei, response);
/*     */     }
/*  90 */     else if (type == 3) {
/*     */       
/*  92 */       int typeMajor = buf.readUnsignedByte();
/*  93 */       int typeMinor = buf.readUnsignedByte();
/*     */       
/*  95 */       buf.readUnsignedByte();
/*     */       
/*  97 */       Position position = new Position(getProtocolName());
/*  98 */       position.setDeviceId(deviceSession.getDeviceId());
/*     */       
/* 100 */       position.setTime(new Date(buf.readUnsignedInt() * 1000L));
/*     */       
/* 102 */       int flags = buf.readUnsignedByte();
/*     */       
/* 104 */       position.setValid(!BitUtil.check(flags, 6));
/*     */       
/* 106 */       position.set("sat", Integer.valueOf(BitUtil.to(flags, 4)));
/*     */       
/* 108 */       double longitude = ((BitUtil.to(buf.readUnsignedShort(), 1) << 24) + buf.readUnsignedMedium()) * 1.0E-5D;
/* 109 */       position.setLongitude(BitUtil.check(flags, 5) ? longitude : -longitude);
/*     */       
/* 111 */       double latitude = buf.readUnsignedMedium() * 1.0E-5D;
/* 112 */       position.setLatitude(BitUtil.check(flags, 4) ? latitude : -latitude);
/*     */       
/* 114 */       int speedCourse = buf.readUnsignedMedium();
/* 115 */       position.setSpeed(UnitsConverter.knotsFromMph(BitUtil.from(speedCourse, 10) * 0.1D));
/* 116 */       position.setCourse(BitUtil.to(speedCourse, 10));
/*     */       
/* 118 */       ByteBuf response = Unpooled.buffer();
/* 119 */       response.writeByte(typeMajor);
/* 120 */       response.writeByte(typeMinor);
/* 121 */       sendResponse(channel, 4, index, imei, response);
/*     */       
/* 123 */       return position;
/*     */     } 
/*     */ 
/*     */     
/* 127 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\protocol\ObdDongleProtocolDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */