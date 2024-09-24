/*     */ package org.traccar.protocol;
/*     */ 
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.handler.codec.http.FullHttpRequest;
/*     */ import io.netty.handler.codec.http.HttpResponseStatus;
/*     */ import io.netty.handler.codec.http.QueryStringDecoder;
/*     */ import java.net.SocketAddress;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.traccar.BaseHttpProtocolDecoder;
/*     */ import org.traccar.DeviceSession;
/*     */ import org.traccar.Protocol;
/*     */ import org.traccar.helper.DateUtil;
/*     */ import org.traccar.model.CellTower;
/*     */ import org.traccar.model.Network;
/*     */ import org.traccar.model.Position;
/*     */ import org.traccar.model.WifiAccessPoint;
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
/*     */ public class OsmAndProtocolDecoder
/*     */   extends BaseHttpProtocolDecoder
/*     */ {
/*     */   public OsmAndProtocolDecoder(Protocol protocol) {
/*  42 */     super(protocol);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object decode(Channel channel, SocketAddress remoteAddress, Object msg) throws Exception {
/*  48 */     FullHttpRequest request = (FullHttpRequest)msg;
/*  49 */     QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
/*  50 */     Map<String, List<String>> params = decoder.parameters();
/*  51 */     if (params.isEmpty()) {
/*  52 */       decoder = new QueryStringDecoder(request.content().toString(StandardCharsets.US_ASCII), false);
/*  53 */       params = decoder.parameters();
/*     */     } 
/*     */     
/*  56 */     Position position = new Position(getProtocolName());
/*  57 */     position.setValid(true);
/*     */     
/*  59 */     Network network = new Network();
/*     */     
/*  61 */     for (Map.Entry<String, List<String>> entry : params.entrySet()) {
/*  62 */       for (String value : entry.getValue()) {
/*  63 */         DeviceSession deviceSession; String[] location; String[] cell; String[] wifi; switch ((String)entry.getKey()) {
/*     */           case "protocol":
/*  65 */             if (getProtocolName().equals("insert")) {
/*  66 */               position.setProtocol(value);
/*     */             }
/*     */             continue;
/*     */           case "id":
/*     */           case "deviceid":
/*  71 */             deviceSession = getDeviceSession(channel, remoteAddress, new String[] { value });
/*  72 */             if (deviceSession == null) {
/*  73 */               sendResponse(channel, HttpResponseStatus.BAD_REQUEST);
/*  74 */               return null;
/*     */             } 
/*  76 */             position.setDeviceId(deviceSession.getDeviceId());
/*     */             continue;
/*     */           case "valid":
/*  79 */             position.setValid((Boolean.parseBoolean(value) || "1".equals(value)));
/*     */             continue;
/*     */           case "timestamp":
/*     */             try {
/*  83 */               long timestamp = Long.parseLong(value);
/*  84 */               if (timestamp < 2147483647L) {
/*  85 */                 timestamp *= 1000L;
/*     */               }
/*  87 */               position.setTime(new Date(timestamp));
/*  88 */             } catch (NumberFormatException error) {
/*  89 */               if (value.contains("T")) {
/*  90 */                 position.setTime(DateUtil.parseDate(value)); continue;
/*     */               } 
/*  92 */               DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*  93 */               position.setTime(dateFormat.parse(value));
/*     */             } 
/*     */             continue;
/*     */           
/*     */           case "lat":
/*  98 */             position.setLatitude(Double.parseDouble(value));
/*     */             continue;
/*     */           case "lon":
/* 101 */             position.setLongitude(Double.parseDouble(value));
/*     */             continue;
/*     */           case "location":
/* 104 */             location = value.split(",");
/* 105 */             position.setLatitude(Double.parseDouble(location[0]));
/* 106 */             position.setLongitude(Double.parseDouble(location[1]));
/*     */             continue;
/*     */           case "cell":
/* 109 */             cell = value.split(",");
/* 110 */             if (cell.length > 4) {
/* 111 */               network.addCellTower(CellTower.from(
/* 112 */                     Integer.parseInt(cell[0]), Integer.parseInt(cell[1]), 
/* 113 */                     Integer.parseInt(cell[2]), Integer.parseInt(cell[3]), Integer.parseInt(cell[4]))); continue;
/*     */             } 
/* 115 */             network.addCellTower(CellTower.from(
/* 116 */                   Integer.parseInt(cell[0]), Integer.parseInt(cell[1]), 
/* 117 */                   Integer.parseInt(cell[2]), Integer.parseInt(cell[3])));
/*     */             continue;
/*     */           
/*     */           case "wifi":
/* 121 */             wifi = value.split(",");
/* 122 */             network.addWifiAccessPoint(WifiAccessPoint.from(wifi[0]
/* 123 */                   .replace('-', ':'), Integer.parseInt(wifi[1])));
/*     */             continue;
/*     */           case "speed":
/* 126 */             position.setSpeed(convertSpeed(Double.parseDouble(value), "kn"));
/*     */             continue;
/*     */           case "bearing":
/*     */           case "heading":
/* 130 */             position.setCourse(Double.parseDouble(value));
/*     */             continue;
/*     */           case "altitude":
/* 133 */             position.setAltitude(Double.parseDouble(value));
/*     */             continue;
/*     */           case "accuracy":
/* 136 */             position.setAccuracy(Double.parseDouble(value));
/*     */             continue;
/*     */           case "hdop":
/* 139 */             position.set("hdop", Double.valueOf(Double.parseDouble(value)));
/*     */             continue;
/*     */           case "batt":
/* 142 */             position.set("batteryLevel", Double.valueOf(Double.parseDouble(value)));
/*     */             continue;
/*     */           case "driverUniqueId":
/* 145 */             position.set("driverUniqueId", value);
/*     */             continue;
/*     */         } 
/*     */         try {
/* 149 */           position.set(entry.getKey(), Double.valueOf(Double.parseDouble(value)));
/* 150 */         } catch (NumberFormatException e) {
/* 151 */           switch (value) {
/*     */             case "true":
/* 153 */               position.set(entry.getKey(), Boolean.valueOf(true));
/*     */               continue;
/*     */             case "false":
/* 156 */               position.set(entry.getKey(), Boolean.valueOf(false));
/*     */               continue;
/*     */           } 
/* 159 */           position.set(entry.getKey(), value);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 168 */     if (position.getFixTime() == null) {
/* 169 */       position.setTime(new Date());
/*     */     }
/*     */     
/* 172 */     if (network.getCellTowers() != null || network.getWifiAccessPoints() != null) {
/* 173 */       position.setNetwork(network);
/*     */     }
/*     */     
/* 176 */     if (position.getLatitude() == 0.0D && position.getLongitude() == 0.0D) {
/* 177 */       getLastLocation(position, position.getDeviceTime());
/*     */     }
/*     */     
/* 180 */     if (position.getDeviceId() != 0L) {
/* 181 */       sendResponse(channel, HttpResponseStatus.OK);
/* 182 */       return position;
/*     */     } 
/* 184 */     sendResponse(channel, HttpResponseStatus.BAD_REQUEST);
/* 185 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\protocol\OsmAndProtocolDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */