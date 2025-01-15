/*    */ package org.traccar.database;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.channels.FileChannel;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.Paths;
/*    */ import java.nio.file.attribute.FileAttribute;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MediaManager
/*    */ {
/* 35 */   private static final Logger LOGGER = LoggerFactory.getLogger(MediaManager.class);
/*    */   
/*    */   private String path;
/*    */   
/*    */   public MediaManager(String path) {
/* 40 */     this.path = path;
/*    */   }
/*    */   
/*    */   private File createFile(String uniqueId, String name) throws IOException {
/* 44 */     Path filePath = Paths.get(this.path, new String[] { uniqueId, name });
/* 45 */     Path directoryPath = filePath.getParent();
/* 46 */     if (directoryPath != null) {
/* 47 */       Files.createDirectories(directoryPath, (FileAttribute<?>[])new FileAttribute[0]);
/*    */     }
/* 49 */     return filePath.toFile();
/*    */   }
/*    */   
/*    */   public String writeFile(String uniqueId, ByteBuf buf, String extension) {
/* 53 */     return writeFile(uniqueId, buf, null, extension);
/*    */   }
/*    */   
/*    */   public String writeFile(String uniqueId, ByteBuf buf, String prefix, String extension) {
/* 57 */     if (this.path != null) {
/* 58 */       int size = buf.readableBytes();
/* 59 */       String name = (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date()) + "." + extension;
/* 60 */       if (prefix != null) {
/* 61 */         name = prefix + "-" + name;
/*    */       }
/* 63 */       try(FileOutputStream output = new FileOutputStream(createFile(uniqueId, name)); 
/* 64 */           FileChannel fileChannel = output.getChannel()) {
/* 65 */         ByteBuffer byteBuffer = buf.nioBuffer();
/* 66 */         int written = 0;
/* 67 */         while (written < size) {
/* 68 */           written += fileChannel.write(byteBuffer);
/*    */         }
/* 70 */         fileChannel.force(false);
/* 71 */         return name;
/* 72 */       } catch (IOException e) {
/* 73 */         LOGGER.warn("Save media file error", e);
/*    */       } 
/*    */     } 
/* 76 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\MediaManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */