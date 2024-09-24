/*     */ package org.traccar.helper;
/*     */ 
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class Parser
/*     */ {
/*     */   private int position;
/*     */   private final Matcher matcher;
/*     */   
/*     */   public Parser(Pattern pattern, String input) {
/*  29 */     this.matcher = pattern.matcher(input);
/*     */   }
/*     */   
/*     */   public boolean matches() {
/*  33 */     this.position = 1;
/*  34 */     return this.matcher.matches();
/*     */   }
/*     */   
/*     */   public boolean find() {
/*  38 */     this.position = 1;
/*  39 */     return this.matcher.find();
/*     */   }
/*     */   
/*     */   public void skip(int number) {
/*  43 */     this.position += number;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  47 */     return hasNext(1);
/*     */   }
/*     */   
/*     */   public boolean hasNext(int number) {
/*  51 */     String value = this.matcher.group(this.position);
/*  52 */     if (value != null && !value.isEmpty()) {
/*  53 */       return true;
/*     */     }
/*  55 */     this.position += number;
/*  56 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String next() {
/*  61 */     return this.matcher.group(this.position++);
/*     */   }
/*     */   
/*     */   public Integer nextInt() {
/*  65 */     if (hasNext()) {
/*  66 */       return Integer.valueOf(Integer.parseInt(next()));
/*     */     }
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int nextInt(int defaultValue) {
/*  73 */     if (hasNext()) {
/*  74 */       return Integer.parseInt(next());
/*     */     }
/*  76 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer nextHexInt() {
/*  81 */     if (hasNext()) {
/*  82 */       return Integer.valueOf(Integer.parseInt(next(), 16));
/*     */     }
/*  84 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int nextHexInt(int defaultValue) {
/*  89 */     if (hasNext()) {
/*  90 */       return Integer.parseInt(next(), 16);
/*     */     }
/*  92 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer nextBinInt() {
/*  97 */     if (hasNext()) {
/*  98 */       return Integer.valueOf(Integer.parseInt(next(), 2));
/*     */     }
/* 100 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int nextBinInt(int defaultValue) {
/* 105 */     if (hasNext()) {
/* 106 */       return Integer.parseInt(next(), 2);
/*     */     }
/* 108 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long nextLong() {
/* 113 */     if (hasNext()) {
/* 114 */       return Long.valueOf(Long.parseLong(next()));
/*     */     }
/* 116 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Long nextHexLong() {
/* 121 */     if (hasNext()) {
/* 122 */       return Long.valueOf(Long.parseLong(next(), 16));
/*     */     }
/* 124 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public long nextLong(long defaultValue) {
/* 129 */     return nextLong(10, defaultValue);
/*     */   }
/*     */   
/*     */   public long nextLong(int radix, long defaultValue) {
/* 133 */     if (hasNext()) {
/* 134 */       return Long.parseLong(next(), radix);
/*     */     }
/* 136 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public Double nextDouble() {
/* 141 */     if (hasNext()) {
/* 142 */       return Double.valueOf(Double.parseDouble(next()));
/*     */     }
/* 144 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public double nextDouble(double defaultValue) {
/* 149 */     if (hasNext()) {
/* 150 */       return Double.parseDouble(next());
/*     */     }
/* 152 */     return defaultValue;
/*     */   }
/*     */   
/*     */   public enum CoordinateFormat
/*     */   {
/* 157 */     DEG_DEG,
/* 158 */     DEG_DEG_HEM,
/* 159 */     DEG_HEM,
/* 160 */     DEG_MIN_MIN,
/* 161 */     DEG_MIN_HEM,
/* 162 */     DEG_MIN_MIN_HEM,
/* 163 */     HEM_DEG_MIN_MIN,
/* 164 */     HEM_DEG,
/* 165 */     HEM_DEG_MIN,
/* 166 */     HEM_DEG_MIN_HEM;
/*     */   }
/*     */   
/*     */   public double nextCoordinate(CoordinateFormat format) {
/*     */     double coordinate;
/* 171 */     String hemisphere = null;
/*     */     
/* 173 */     switch (format) {
/*     */       case HMS:
/* 175 */         coordinate = Double.parseDouble(next() + '.' + next());
/*     */         break;
/*     */       case SMH:
/* 178 */         coordinate = Double.parseDouble(next() + '.' + next());
/* 179 */         hemisphere = next();
/*     */         break;
/*     */       case HMS_YMD:
/* 182 */         coordinate = nextDouble(0.0D);
/* 183 */         hemisphere = next();
/*     */         break;
/*     */       case HMS_DMY:
/* 186 */         coordinate = nextInt(0);
/* 187 */         coordinate += Double.parseDouble(next() + '.' + next()) / 60.0D;
/*     */         break;
/*     */       case SMH_YMD:
/* 190 */         coordinate = nextInt(0);
/* 191 */         coordinate += Double.parseDouble(next() + '.' + next()) / 60.0D;
/* 192 */         hemisphere = next();
/*     */         break;
/*     */       case SMH_DMY:
/* 195 */         hemisphere = next();
/* 196 */         coordinate = nextDouble(0.0D);
/*     */         break;
/*     */       case DMY_HMS:
/* 199 */         hemisphere = next();
/* 200 */         coordinate = nextInt(0);
/* 201 */         coordinate += nextDouble(0.0D) / 60.0D;
/*     */         break;
/*     */       case DMY_HMSS:
/* 204 */         hemisphere = next();
/* 205 */         coordinate = nextInt(0);
/* 206 */         coordinate += nextDouble(0.0D) / 60.0D;
/* 207 */         if (hasNext()) {
/* 208 */           hemisphere = next();
/*     */         }
/*     */         break;
/*     */       case YMD_HMS:
/* 212 */         hemisphere = next();
/* 213 */         coordinate = nextInt(0);
/* 214 */         coordinate += Double.parseDouble(next() + '.' + next()) / 60.0D;
/*     */         break;
/*     */       
/*     */       default:
/* 218 */         coordinate = nextInt(0);
/* 219 */         coordinate += nextDouble(0.0D) / 60.0D;
/* 220 */         hemisphere = next();
/*     */         break;
/*     */     } 
/*     */     
/* 224 */     if (hemisphere != null && (hemisphere.equals("S") || hemisphere.equals("W") || hemisphere.equals("-"))) {
/* 225 */       coordinate = -Math.abs(coordinate);
/*     */     }
/*     */     
/* 228 */     return coordinate;
/*     */   }
/*     */   
/*     */   public double nextCoordinate() {
/* 232 */     return nextCoordinate(CoordinateFormat.DEG_MIN_HEM);
/*     */   }
/*     */   
/*     */   public enum DateTimeFormat {
/* 236 */     HMS,
/* 237 */     SMH,
/* 238 */     HMS_YMD,
/* 239 */     HMS_DMY,
/* 240 */     SMH_YMD,
/* 241 */     SMH_DMY,
/* 242 */     DMY_HMS,
/* 243 */     DMY_HMSS,
/* 244 */     YMD_HMS,
/* 245 */     YMD_HMSS; }
/*     */   
/*     */   public Date nextDateTime(DateTimeFormat format, String timeZone) {
/*     */     DateBuilder dateBuilder;
/* 249 */     int year = 0, month = 0, day = 0;
/* 250 */     int hour = 0, minute = 0, second = 0, millisecond = 0;
/*     */     
/* 252 */     switch (format) {
/*     */       case HMS:
/* 254 */         hour = nextInt(0);
/* 255 */         minute = nextInt(0);
/* 256 */         second = nextInt(0);
/*     */         break;
/*     */       case SMH:
/* 259 */         second = nextInt(0);
/* 260 */         minute = nextInt(0);
/* 261 */         hour = nextInt(0);
/*     */         break;
/*     */       case HMS_YMD:
/* 264 */         hour = nextInt(0);
/* 265 */         minute = nextInt(0);
/* 266 */         second = nextInt(0);
/* 267 */         year = nextInt(0);
/* 268 */         month = nextInt(0);
/* 269 */         day = nextInt(0);
/*     */         break;
/*     */       case HMS_DMY:
/* 272 */         hour = nextInt(0);
/* 273 */         minute = nextInt(0);
/* 274 */         second = nextInt(0);
/* 275 */         day = nextInt(0);
/* 276 */         month = nextInt(0);
/* 277 */         year = nextInt(0);
/*     */         break;
/*     */       case SMH_YMD:
/* 280 */         second = nextInt(0);
/* 281 */         minute = nextInt(0);
/* 282 */         hour = nextInt(0);
/* 283 */         year = nextInt(0);
/* 284 */         month = nextInt(0);
/* 285 */         day = nextInt(0);
/*     */         break;
/*     */       case SMH_DMY:
/* 288 */         second = nextInt(0);
/* 289 */         minute = nextInt(0);
/* 290 */         hour = nextInt(0);
/* 291 */         day = nextInt(0);
/* 292 */         month = nextInt(0);
/* 293 */         year = nextInt(0);
/*     */         break;
/*     */       case DMY_HMS:
/*     */       case DMY_HMSS:
/* 297 */         day = nextInt(0);
/* 298 */         month = nextInt(0);
/* 299 */         year = nextInt(0);
/* 300 */         hour = nextInt(0);
/* 301 */         minute = nextInt(0);
/* 302 */         second = nextInt(0);
/*     */         break;
/*     */ 
/*     */       
/*     */       default:
/* 307 */         year = nextInt(0);
/* 308 */         month = nextInt(0);
/* 309 */         day = nextInt(0);
/* 310 */         hour = nextInt(0);
/* 311 */         minute = nextInt(0);
/* 312 */         second = nextInt(0);
/*     */         break;
/*     */     } 
/*     */     
/* 316 */     if (format == DateTimeFormat.YMD_HMSS || format == DateTimeFormat.DMY_HMSS) {
/* 317 */       millisecond = nextInt(0);
/*     */     }
/*     */     
/* 320 */     if (year >= 0 && year < 100) {
/* 321 */       year += 2000;
/*     */     }
/*     */ 
/*     */     
/* 325 */     if (format != DateTimeFormat.HMS && format != DateTimeFormat.SMH) {
/* 326 */       if (timeZone != null) {
/* 327 */         dateBuilder = new DateBuilder(TimeZone.getTimeZone(timeZone));
/*     */       } else {
/* 329 */         dateBuilder = new DateBuilder();
/*     */       } 
/* 331 */       dateBuilder.setDate(year, month, day);
/*     */     }
/* 333 */     else if (timeZone != null) {
/* 334 */       dateBuilder = new DateBuilder(new Date(), TimeZone.getTimeZone(timeZone));
/*     */     } else {
/* 336 */       dateBuilder = new DateBuilder(new Date());
/*     */     } 
/*     */ 
/*     */     
/* 340 */     dateBuilder.setTime(hour, minute, second, millisecond);
/*     */     
/* 342 */     return dateBuilder.getDate();
/*     */   }
/*     */   
/*     */   public Date nextDateTime(DateTimeFormat format) {
/* 346 */     return nextDateTime(format, null);
/*     */   }
/*     */   
/*     */   public Date nextDateTime() {
/* 350 */     return nextDateTime(DateTimeFormat.YMD_HMS, null);
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\helper\Parser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */