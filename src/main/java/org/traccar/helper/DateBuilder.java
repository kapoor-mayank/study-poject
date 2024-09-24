/*     */ package org.traccar.helper;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
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
/*     */ public class DateBuilder
/*     */ {
/*     */   private Calendar calendar;
/*     */   
/*     */   public DateBuilder() {
/*  27 */     this(TimeZone.getTimeZone("UTC"));
/*     */   }
/*     */   
/*     */   public DateBuilder(Date time) {
/*  31 */     this(time, TimeZone.getTimeZone("UTC"));
/*     */   }
/*     */   
/*     */   public DateBuilder(TimeZone timeZone) {
/*  35 */     this(new Date(0L), timeZone);
/*     */   }
/*     */   
/*     */   public DateBuilder(Date time, TimeZone timeZone) {
/*  39 */     this.calendar = Calendar.getInstance(timeZone);
/*  40 */     this.calendar.clear();
/*  41 */     this.calendar.setTimeInMillis(time.getTime());
/*     */   }
/*     */   
/*     */   public DateBuilder setYear(int year) {
/*  45 */     if (year < 100) {
/*  46 */       year += 2000;
/*     */     }
/*  48 */     this.calendar.set(1, year);
/*  49 */     return this;
/*     */   }
/*     */   
/*     */   public DateBuilder setMonth(int month) {
/*  53 */     this.calendar.set(2, month - 1);
/*  54 */     return this;
/*     */   }
/*     */   
/*     */   public DateBuilder setDay(int day) {
/*  58 */     this.calendar.set(5, day);
/*  59 */     return this;
/*     */   }
/*     */   
/*     */   public DateBuilder setDate(int year, int month, int day) {
/*  63 */     return setYear(year).setMonth(month).setDay(day);
/*     */   }
/*     */   
/*     */   public DateBuilder setDateReverse(int day, int month, int year) {
/*  67 */     return setDate(year, month, day);
/*     */   }
/*     */   
/*     */   public DateBuilder setCurrentDate() {
/*  71 */     Calendar now = Calendar.getInstance(this.calendar.getTimeZone());
/*  72 */     return setYear(now.get(1))
/*  73 */       .setMonth(now.get(2) + 1)
/*  74 */       .setDay(now.get(5));
/*     */   }
/*     */   
/*     */   public DateBuilder setHour(int hour) {
/*  78 */     this.calendar.set(11, hour);
/*  79 */     return this;
/*     */   }
/*     */   
/*     */   public DateBuilder setMinute(int minute) {
/*  83 */     this.calendar.set(12, minute);
/*  84 */     return this;
/*     */   }
/*     */   
/*     */   public DateBuilder addMinute(int minute) {
/*  88 */     this.calendar.add(12, minute);
/*  89 */     return this;
/*     */   }
/*     */   
/*     */   public DateBuilder setSecond(int second) {
/*  93 */     this.calendar.set(13, second);
/*  94 */     return this;
/*     */   }
/*     */   
/*     */   public DateBuilder addSeconds(long seconds) {
/*  98 */     this.calendar.setTimeInMillis(this.calendar.getTimeInMillis() + seconds * 1000L);
/*  99 */     return this;
/*     */   }
/*     */   
/*     */   public DateBuilder setMillis(int millis) {
/* 103 */     this.calendar.set(14, millis);
/* 104 */     return this;
/*     */   }
/*     */   
/*     */   public DateBuilder addMillis(long millis) {
/* 108 */     this.calendar.setTimeInMillis(this.calendar.getTimeInMillis() + millis);
/* 109 */     return this;
/*     */   }
/*     */   
/*     */   public DateBuilder setTime(int hour, int minute, int second) {
/* 113 */     return setHour(hour).setMinute(minute).setSecond(second);
/*     */   }
/*     */   
/*     */   public DateBuilder setTimeReverse(int second, int minute, int hour) {
/* 117 */     return setHour(hour).setMinute(minute).setSecond(second);
/*     */   }
/*     */   
/*     */   public DateBuilder setTime(int hour, int minute, int second, int millis) {
/* 121 */     return setHour(hour).setMinute(minute).setSecond(second).setMillis(millis);
/*     */   }
/*     */   
/*     */   public Date getDate() {
/* 125 */     return this.calendar.getTime();
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\helper\DateBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */