/*     */ package org.traccar;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.BindException;
/*     */ import java.net.ConnectException;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.net.URLDecoder;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.Enumeration;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarFile;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
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
/*     */ 
/*     */ public class ServerManager
/*     */ {
/*  38 */   private static final Logger LOGGER = LoggerFactory.getLogger(ServerManager.class);
/*     */   
/*  40 */   private final List<TrackerConnector> connectorList = new LinkedList<>();
/*  41 */   private final Map<String, BaseProtocol> protocolList = new ConcurrentHashMap<>();
/*     */ 
/*     */   
/*     */   public ServerManager() throws Exception {
/*  45 */     List<String> names = new LinkedList<>();
/*  46 */     String packageName = "org.traccar.protocol";
/*  47 */     String packagePath = packageName.replace('.', '/');
/*  48 */     URL packageUrl = getClass().getClassLoader().getResource(packagePath);
/*     */     
/*  50 */     if (packageUrl.getProtocol().equals("jar")) {
/*  51 */       String jarFileName = URLDecoder.decode(packageUrl.getFile(), StandardCharsets.UTF_8.name());
/*  52 */       try (JarFile jf = new JarFile(jarFileName.substring(5, jarFileName.indexOf("!")))) {
/*  53 */         Enumeration<JarEntry> jarEntries = jf.entries();
/*  54 */         while (jarEntries.hasMoreElements()) {
/*  55 */           String entryName = ((JarEntry)jarEntries.nextElement()).getName();
/*  56 */           if (entryName.startsWith(packagePath) && entryName.length() > packagePath.length() + 5) {
/*  57 */             names.add(entryName.substring(packagePath.length() + 1, entryName.lastIndexOf('.')));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } else {
/*  62 */       File folder = new File(new URI(packageUrl.toString()));
/*  63 */       File[] files = folder.listFiles();
/*  64 */       if (files != null) {
/*  65 */         for (File actual : files) {
/*  66 */           String entryName = actual.getName();
/*  67 */           names.add(entryName.substring(0, entryName.lastIndexOf('.')));
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  72 */     for (String name : names) {
/*  73 */       Class<?> protocolClass = Class.forName(packageName + '.' + name);
/*  74 */       if (BaseProtocol.class.isAssignableFrom(protocolClass) && 
/*  75 */         Context.getConfig().hasKey(BaseProtocol.nameFromClass(protocolClass) + ".port")) {
/*  76 */         BaseProtocol protocol = (BaseProtocol)protocolClass.newInstance();
/*  77 */         this.connectorList.addAll(protocol.getConnectorList());
/*  78 */         this.protocolList.put(protocol.getName(), protocol);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public BaseProtocol getProtocol(String name) {
/*  84 */     return this.protocolList.get(name);
/*     */   }
/*     */   
/*     */   public void start() throws Exception {
/*  88 */     for (TrackerConnector connector : this.connectorList) {
/*     */       try {
/*  90 */         connector.start();
/*  91 */       } catch (BindException e) {
/*  92 */         LOGGER.warn("Port disabled due to conflict", e);
/*  93 */       } catch (ConnectException e) {
/*  94 */         LOGGER.warn("Connection failed", e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void stop() {
/* 100 */     for (TrackerConnector connector : this.connectorList) {
/* 101 */       connector.stop();
/*     */     }
/* 103 */     GlobalTimer.release();
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\ServerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */