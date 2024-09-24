/*    */ package org.traccar;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.net.DatagramPacket;
/*    */ import java.net.DatagramSocket;
/*    */ import java.net.InetAddress;
/*    */ import java.net.Socket;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ public class DataForwarder
/*    */ {
/* 19 */   private static final Logger LOGGER = LoggerFactory.getLogger(DataForwarder.class);
/*    */   
/*    */   private static class Info
/*    */   {
/*    */     private InetAddress address;
/*    */     private int port;
/*    */     private boolean datagram;
/*    */     private DatagramSocket udpSocket;
/*    */     private Socket tcpSocket;
/*    */     private OutputStream tcpSteam;
/*    */     
/*    */     private Info() {}
/*    */   }
/* 32 */   private Map<String, List<Info>> infoMap = new HashMap<>();
/* 33 */   private Map<String, Integer> fails = new HashMap<>();
/*    */   
/*    */   public DataForwarder() {
/* 36 */     String config = Context.getConfig().getString("forwarder.config", "");
/* 37 */     for (String line : config.split("\r?\n")) {
/*    */       try {
/* 39 */         String[] params = line.trim().split(" +");
/* 40 */         if (params.length >= 4) {
/* 41 */           Info info = new Info();
/* 42 */           info.address = InetAddress.getByName(params[1]);
/* 43 */           info.port = Integer.parseInt(params[2]);
/* 44 */           info.datagram = params[3].equalsIgnoreCase("UDP");
/*    */           
/* 46 */           List<Info> infoList = this.infoMap.getOrDefault(params[0], new ArrayList<>());
/* 47 */           infoList.add(info);
/* 48 */           this.infoMap.put(params[0], infoList);
/*    */         } 
/* 50 */       } catch (IOException e) {
/* 51 */         LOGGER.warn("DataForwarder init error", e);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void forward(String uniqueId, byte[] data) {
/* 57 */     if (((Integer)this.fails.getOrDefault(uniqueId, (V)Integer.valueOf(0))).intValue() >= 5)
/*    */       return;  try {
/* 59 */       List<Info> infoList = this.infoMap.get(uniqueId);
/* 60 */       if (infoList != null) {
/* 61 */         for (Info info : infoList) {
/* 62 */           if (info.datagram) {
/* 63 */             if (info.udpSocket == null) {
/* 64 */               info.udpSocket = new DatagramSocket();
/*    */             }
/* 66 */             info.udpSocket.send(new DatagramPacket(data, data.length, info.address, info.port)); continue;
/*    */           } 
/* 68 */           if (info.tcpSocket == null || info.tcpSocket.isClosed()) {
/* 69 */             info.tcpSocket = new Socket(info.address, info.port);
/* 70 */             info.tcpSteam = info.tcpSocket.getOutputStream();
/*    */           } 
/* 72 */           info.tcpSteam.write(data);
/*    */         }
/*    */       
/*    */       }
/* 76 */     } catch (IOException e) {
/* 77 */       this.fails.put(uniqueId, Integer.valueOf(((Integer)this.fails.getOrDefault(uniqueId, Integer.valueOf(0))).intValue() + 1));
/* 78 */       LOGGER.warn("DataForwarder forward error", e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\DataForwarder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */