/*    */ package org.traccar.helper;
/*    */ 
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.time.Instant;
/*    */ import java.time.ZoneId;
/*    */ import java.time.format.DateTimeFormatter;
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class DateUtil
/*    */ {
/*    */   public static Date correctDay(Date guess) {
/* 31 */     return correctDate(new Date(), guess, 5);
/*    */   }
/*    */   
/*    */   public static Date correctYear(Date guess) {
/* 35 */     return correctDate(new Date(), guess, 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public static Date correctDate(Date now, Date guess, int field) {
/* 40 */     if (guess.getTime() > now.getTime()) {
/* 41 */       Date previous = dateAdd(guess, field, -1);
/* 42 */       if (now.getTime() - previous.getTime() < guess.getTime() - now.getTime()) {
/* 43 */         return previous;
/*    */       }
/* 45 */     } else if (guess.getTime() < now.getTime()) {
/* 46 */       Date next = dateAdd(guess, field, 1);
/* 47 */       if (next.getTime() - now.getTime() < now.getTime() - guess.getTime()) {
/* 48 */         return next;
/*    */       }
/*    */     } 
/*    */     
/* 52 */     return guess;
/*    */   }
/*    */   
/*    */   private static Date dateAdd(Date guess, int field, int amount) {
/* 56 */     Calendar calendar = Calendar.getInstance();
/* 57 */     calendar.setTime(guess);
/* 58 */     calendar.add(field, amount);
/* 59 */     return calendar.getTime();
/*    */   }
/*    */   
/*    */   public static Date parseDate(String value) {
/* 63 */     return Date.from(Instant.from(DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(value)));
/*    */   }
/*    */   
/*    */   public static String formatDate(Date date) {
/* 67 */     return formatDate(date, true);
/*    */   }
/*    */   
/*    */   public static String formatDate(Date date, boolean zoned) {
/* 71 */     if (zoned) {
/* 72 */       return DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.systemDefault()).format(date.toInstant());
/*    */     }
/* 74 */     return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\helper\DateUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */