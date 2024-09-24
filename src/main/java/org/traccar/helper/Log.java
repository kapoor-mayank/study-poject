/*     */ package org.traccar.helper;
/*     */ 
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.logging.ConsoleHandler;
/*     */ import java.util.logging.Formatter;
/*     */ import java.util.logging.Handler;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import org.traccar.config.Config;
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
/*     */ 
/*     */ 
/*     */ public final class Log
/*     */ {
/*     */   private static final String STACK_PACKAGE = "org.traccar";
/*     */   private static final int STACK_LIMIT = 3;
/*     */   
/*     */   private static class RollingFileHandler
/*     */     extends Handler
/*     */   {
/*     */     private String name;
/*     */     private String suffix;
/*     */     private Writer writer;
/*     */     private boolean rotate;
/*     */     
/*     */     RollingFileHandler(String name, boolean rotate) {
/*  55 */       this.name = name;
/*  56 */       this.rotate = rotate;
/*     */     }
/*     */ 
/*     */     
/*     */     public synchronized void publish(LogRecord record) {
/*  61 */       if (isLoggable(record)) {
/*     */         try {
/*  63 */           String suffix = "";
/*  64 */           if (this.rotate) {
/*  65 */             suffix = (new SimpleDateFormat("yyyyMMdd")).format(new Date(record.getMillis()));
/*     */             
/*  67 */             this.writer.close();
/*  68 */             this.writer = null;
/*  69 */             if (this.writer != null && !suffix.equals(this.suffix) && !(new File(this.name)).renameTo(new File(this.name + "." + this.suffix))) {
/*  70 */               throw new RuntimeException("Log file renaming failed");
/*     */             }
/*     */           } 
/*     */           
/*  74 */           if (this.writer == null) {
/*  75 */             this.suffix = suffix;
/*  76 */             this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.name, true), StandardCharsets.UTF_8));
/*     */           } 
/*     */           
/*  79 */           this.writer.write(getFormatter().format(record));
/*  80 */           this.writer.flush();
/*  81 */         } catch (IOException e) {
/*  82 */           throw new RuntimeException(e);
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public synchronized void flush() {
/*  89 */       if (this.writer != null) {
/*     */         try {
/*  91 */           this.writer.flush();
/*  92 */         } catch (IOException e) {
/*  93 */           throw new RuntimeException(e);
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public synchronized void close() throws SecurityException {
/* 100 */       if (this.writer != null) {
/*     */         try {
/* 102 */           this.writer.close();
/* 103 */         } catch (IOException e) {
/* 104 */           throw new RuntimeException(e);
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class LogFormatter
/*     */     extends Formatter
/*     */   {
/*     */     private boolean fullStackTraces;
/*     */     
/*     */     LogFormatter(boolean fullStackTraces) {
/* 116 */       this.fullStackTraces = fullStackTraces;
/*     */     }
/*     */     
/*     */     private static String formatLevel(Level level) {
/* 120 */       switch (level.getName()) {
/*     */         case "FINEST":
/* 122 */           return "TRACE";
/*     */         case "FINER":
/*     */         case "FINE":
/*     */         case "CONFIG":
/* 126 */           return "DEBUG";
/*     */         case "INFO":
/* 128 */           return "INFO";
/*     */         case "WARNING":
/* 130 */           return "WARN";
/*     */       } 
/*     */       
/* 133 */       return "ERROR";
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String format(LogRecord record) {
/* 139 */       StringBuilder message = new StringBuilder();
/*     */       
/* 141 */       if (record.getMessage() != null) {
/* 142 */         message.append(record.getMessage());
/*     */       }
/*     */       
/* 145 */       if (record.getThrown() != null) {
/* 146 */         if (message.length() > 0) {
/* 147 */           message.append(" - ");
/*     */         }
/* 149 */         if (this.fullStackTraces) {
/* 150 */           StringWriter stringWriter = new StringWriter();
/* 151 */           PrintWriter printWriter = new PrintWriter(stringWriter);
/* 152 */           record.getThrown().printStackTrace(printWriter);
/* 153 */           message.append(System.lineSeparator()).append(stringWriter.toString());
/*     */         } else {
/* 155 */           message.append(Log.exceptionStack(record.getThrown()));
/*     */         } 
/*     */       } 
/*     */       
/* 159 */       return String.format("%1$tF %1$tT %2$5s: %3$s%n", new Object[] { new Date(record
/* 160 */               .getMillis()), formatLevel(record.getLevel()), message.toString() });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setupDefaultLogger() {
/* 166 */     String path = null;
/* 167 */     URL url = ClassLoader.getSystemClassLoader().getResource(".");
/* 168 */     if (url != null) {
/* 169 */       File jarPath = new File(url.getPath());
/* 170 */       File logsPath = new File(jarPath, "logs");
/* 171 */       if (!logsPath.exists() || !logsPath.isDirectory()) {
/* 172 */         logsPath = jarPath;
/*     */       }
/* 174 */       path = (new File(logsPath, "tracker-server.log")).getPath();
/*     */     } 
/* 176 */     setupLogger((path == null), path, Level.WARNING.getName(), false, true);
/*     */   }
/*     */   
/*     */   public static void setupLogger(Config config) {
/* 180 */     setupLogger(config
/* 181 */         .getBoolean("logger.console"), config
/* 182 */         .getString("logger.file"), config
/* 183 */         .getString("logger.level"), config
/* 184 */         .getBoolean("logger.fullStackTraces"), config
/* 185 */         .getBoolean("logger.rotate"));
/*     */   }
/*     */ 
/*     */   
/*     */   private static void setupLogger(boolean console, String file, String levelString, boolean fullStackTraces, boolean rotate) {
/*     */     Handler handler;
/* 191 */     Logger rootLogger = Logger.getLogger("");
/* 192 */     for (Handler handler1 : rootLogger.getHandlers()) {
/* 193 */       rootLogger.removeHandler(handler1);
/*     */     }
/*     */ 
/*     */     
/* 197 */     if (console) {
/* 198 */       handler = new ConsoleHandler();
/*     */     } else {
/* 200 */       handler = new RollingFileHandler(file, rotate);
/*     */     } 
/*     */     
/* 203 */     handler.setFormatter(new LogFormatter(fullStackTraces));
/*     */     
/* 205 */     Level level = Level.parse(levelString.toUpperCase());
/* 206 */     rootLogger.setLevel(level);
/* 207 */     handler.setLevel(level);
/* 208 */     handler.setFilter(record -> (record != null && !record.getLoggerName().startsWith("sun")));
/*     */     
/* 210 */     rootLogger.addHandler(handler);
/*     */   }
/*     */   
/*     */   public static String exceptionStack(Throwable exception) {
/* 214 */     StringBuilder s = new StringBuilder();
/* 215 */     String exceptionMsg = exception.getMessage();
/* 216 */     if (exceptionMsg != null) {
/* 217 */       s.append(exceptionMsg);
/* 218 */       s.append(" - ");
/*     */     } 
/* 220 */     s.append(exception.getClass().getSimpleName());
/* 221 */     StackTraceElement[] stack = exception.getStackTrace();
/*     */     
/* 223 */     if (stack.length > 0) {
/* 224 */       int count = 3;
/* 225 */       boolean first = true;
/* 226 */       boolean skip = false;
/* 227 */       String file = "";
/* 228 */       s.append(" (");
/* 229 */       for (StackTraceElement element : stack) {
/* 230 */         if (count > 0 && element.getClassName().startsWith("org.traccar")) {
/* 231 */           if (!first) {
/* 232 */             s.append(" < ");
/*     */           } else {
/* 234 */             first = false;
/*     */           } 
/*     */           
/* 237 */           if (skip) {
/* 238 */             s.append("... < ");
/* 239 */             skip = false;
/*     */           } 
/*     */           
/* 242 */           if (file.equals(element.getFileName())) {
/* 243 */             s.append("*");
/*     */           } else {
/* 245 */             file = element.getFileName();
/* 246 */             s.append(file, 0, file.length() - 5);
/* 247 */             count--;
/*     */           } 
/* 249 */           s.append(":").append(element.getLineNumber());
/*     */         } else {
/* 251 */           skip = true;
/*     */         } 
/*     */       } 
/* 254 */       if (skip) {
/* 255 */         if (!first) {
/* 256 */           s.append(" < ");
/*     */         }
/* 258 */         s.append("...");
/*     */       } 
/* 260 */       s.append(")");
/*     */     } 
/* 262 */     return s.toString();
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\helper\Log.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */