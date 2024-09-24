/*     */ package org.traccar.protocol;
/*     */ 
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.handler.codec.http.FullHttpRequest;
/*     */ import io.netty.handler.codec.http.HttpResponseStatus;
/*     */ import java.io.StringReader;
/*     */ import java.net.SocketAddress;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.time.OffsetDateTime;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.TimeZone;
/*     */ import javax.json.Json;
/*     */ import javax.json.JsonArray;
/*     */ import javax.json.JsonObject;
/*     */ import org.traccar.BaseHttpProtocolDecoder;
/*     */ import org.traccar.DeviceSession;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DmtHttpProtocolDecoder
/*     */   extends BaseHttpProtocolDecoder
/*     */ {
/*     */   public DmtHttpProtocolDecoder(Protocol protocol) {
/*  47 */     super(protocol);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object decode(Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {
/*     */     Object result;
/*  54 */     FullHttpRequest request = (FullHttpRequest)msg;
/*     */     
/*  56 */     JsonObject root = Json.createReader(new StringReader(request.content().toString(StandardCharsets.US_ASCII))).readObject();
/*     */ 
/*     */     
/*  59 */     if (root.containsKey("device")) {
/*  60 */       result = (Position)decodeEdge(channel, remoteAddress, root);
/*     */     } else {
/*  62 */       result = (Position)decodeTraditional(channel, remoteAddress, root);
/*     */     } 
/*     */     
/*  65 */     sendResponse(channel, (result != null) ? HttpResponseStatus.OK : HttpResponseStatus.BAD_REQUEST);
/*  66 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Collection<Position> decodeTraditional(Channel channel, SocketAddress remoteAddress, JsonObject root) throws ParseException {
/*  72 */     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*  73 */     dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
/*     */     
/*  75 */     DeviceSession deviceSession = getDeviceSession(channel, remoteAddress, new String[] { root.getString("IMEI") });
/*  76 */     if (deviceSession == null) {
/*  77 */       return null;
/*     */     }
/*     */     
/*  80 */     List<Position> positions = new LinkedList<>();
/*     */     
/*  82 */     JsonArray records = root.getJsonArray("Records");
/*     */     
/*  84 */     for (int i = 0; i < records.size(); i++) {
/*  85 */       Position position = new Position(getProtocolName());
/*  86 */       position.setDeviceId(deviceSession.getDeviceId());
/*     */       
/*  88 */       JsonObject record = records.getJsonObject(i);
/*     */       
/*  90 */       position.set("index", Integer.valueOf(record.getInt("SeqNo")));
/*  91 */       position.set("event", Integer.valueOf(record.getInt("Reason")));
/*     */       
/*  93 */       position.setDeviceTime(dateFormat.parse(record.getString("DateUTC")));
/*     */       
/*  95 */       JsonArray fields = record.getJsonArray("Fields");
/*     */       
/*  97 */       for (int j = 0; j < fields.size(); j++) {
/*  98 */         int input, output; JsonObject adc, field = fields.getJsonObject(j);
/*  99 */         switch (field.getInt("FType")) {
/*     */           case 0:
/* 101 */             position.setFixTime(dateFormat.parse(field.getString("GpsUTC")));
/* 102 */             position.setLatitude(field.getJsonNumber("Lat").doubleValue());
/* 103 */             position.setLongitude(field.getJsonNumber("Long").doubleValue());
/* 104 */             position.setAltitude(field.getInt("Alt"));
/* 105 */             position.setSpeed(UnitsConverter.knotsFromCps(field.getInt("Spd")));
/* 106 */             position.setCourse(field.getInt("Head"));
/* 107 */             position.setAccuracy(field.getInt("PosAcc"));
/* 108 */             position.setValid((field.getInt("GpsStat") > 0));
/*     */             break;
/*     */           case 2:
/* 111 */             input = field.getInt("DIn");
/* 112 */             output = field.getInt("DOut");
/*     */             
/* 114 */             position.set("ignition", Boolean.valueOf(BitUtil.check(input, 0)));
/*     */             
/* 116 */             position.set("input", Integer.valueOf(input));
/* 117 */             position.set("output", Integer.valueOf(output));
/* 118 */             position.set("status", Integer.valueOf(field.getInt("DevStat")));
/*     */             break;
/*     */           case 6:
/* 121 */             adc = field.getJsonObject("AnalogueData");
/* 122 */             if (adc.containsKey("1")) {
/* 123 */               position.set("battery", Double.valueOf(adc.getInt("1") * 0.001D));
/*     */             }
/* 125 */             if (adc.containsKey("2")) {
/* 126 */               position.set("power", Double.valueOf(adc.getInt("2") * 0.01D));
/*     */             }
/* 128 */             if (adc.containsKey("3")) {
/* 129 */               position.set("deviceTemp", Double.valueOf(adc.getInt("3") * 0.01D));
/*     */             }
/* 131 */             if (adc.containsKey("4")) {
/* 132 */               position.set("rssi", Integer.valueOf(adc.getInt("4")));
/*     */             }
/* 134 */             if (adc.containsKey("5")) {
/* 135 */               position.set("solarPower", Double.valueOf(adc.getInt("5") * 0.001D));
/*     */             }
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/* 143 */       positions.add(position);
/*     */     } 
/*     */     
/* 146 */     return positions;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Position decodeEdge(Channel channel, SocketAddress remoteAddress, JsonObject root) {
/* 152 */     JsonObject device = root.getJsonObject("device");
/*     */     
/* 154 */     DeviceSession deviceSession = getDeviceSession(channel, remoteAddress, new String[] { device.getString("imei") });
/* 155 */     if (deviceSession == null) {
/* 156 */       return null;
/*     */     }
/*     */     
/* 159 */     Position position = new Position(getProtocolName());
/* 160 */     position.setDeviceId(deviceSession.getDeviceId());
/*     */     
/* 162 */     Date time = new Date(OffsetDateTime.parse(root.getString("date")).toInstant().toEpochMilli());
/*     */     
/* 164 */     if (root.containsKey("lat") && root.containsKey("lng")) {
/* 165 */       position.setValid(true);
/* 166 */       position.setTime(time);
/* 167 */       position.setLatitude(root.getJsonNumber("lat").doubleValue());
/* 168 */       position.setLongitude(root.getJsonNumber("lng").doubleValue());
/* 169 */       position.setAccuracy(root.getJsonNumber("posAcc").doubleValue());
/*     */     } else {
/* 171 */       getLastLocation(position, time);
/*     */     } 
/*     */     
/* 174 */     position.set("index", Integer.valueOf(root.getInt("sqn")));
/* 175 */     position.set("event", Integer.valueOf(root.getInt("reason")));
/*     */     
/* 177 */     if (root.containsKey("analogues")) {
/* 178 */       JsonArray analogues = root.getJsonArray("analogues");
/* 179 */       for (int i = 0; i < analogues.size(); i++) {
/* 180 */         JsonObject adc = analogues.getJsonObject(i);
/* 181 */         position.set("adc" + adc.getInt("id"), Integer.valueOf(adc.getInt("val")));
/*     */       } 
/*     */     } 
/*     */     
/* 185 */     if (root.containsKey("inputs")) {
/* 186 */       int input = root.getInt("inputs");
/* 187 */       position.set("ignition", Boolean.valueOf(BitUtil.check(input, 0)));
/* 188 */       position.set("input", Integer.valueOf(input));
/*     */     } 
/* 190 */     if (root.containsKey("outputs")) {
/* 191 */       position.set("output", Integer.valueOf(root.getInt("outputs")));
/*     */     }
/* 193 */     if (root.containsKey("status")) {
/* 194 */       position.set("status", Integer.valueOf(root.getInt("status")));
/*     */     }
/*     */     
/* 197 */     if (root.containsKey("counters")) {
/* 198 */       JsonArray counters = root.getJsonArray("counters");
/* 199 */       for (int i = 0; i < counters.size(); i++) {
/* 200 */         JsonObject counter = counters.getJsonObject(i);
/* 201 */         switch (counter.getInt("id")) {
/*     */           case 0:
/* 203 */             position.set("battery", Double.valueOf(counter.getInt("val") * 0.001D));
/*     */             break;
/*     */           case 1:
/* 206 */             position.set("batteryLevel", Double.valueOf(counter.getInt("val") * 0.01D));
/*     */             break;
/*     */           default:
/* 209 */             position.set("counter" + counter.getInt("id"), Integer.valueOf(counter.getInt("val")));
/*     */             break;
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/*     */     } 
/* 216 */     return position;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\protocol\DmtHttpProtocolDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */