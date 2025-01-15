/*    */ package org.traccar.helper;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.regex.Pattern;
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
/*    */ public class PatternBuilder
/*    */ {
/* 23 */   private final ArrayList<String> fragments = new ArrayList<>();
/*    */   
/*    */   public PatternBuilder optional() {
/* 26 */     return optional(1);
/*    */   }
/*    */   
/*    */   public PatternBuilder optional(int count) {
/* 30 */     this.fragments.add(this.fragments.size() - count, "(?:");
/* 31 */     this.fragments.add(")?");
/* 32 */     return this;
/*    */   }
/*    */   
/*    */   public PatternBuilder expression(String s) {
/* 36 */     s = s.replaceAll("\\|$", "\\\\|");
/*    */     
/* 38 */     this.fragments.add(s);
/* 39 */     return this;
/*    */   }
/*    */   
/*    */   public PatternBuilder text(String s) {
/* 43 */     this.fragments.add(s.replaceAll("([\\\\\\.\\[\\{\\(\\)\\*\\+\\?\\^\\$\\|])", "\\\\$1"));
/* 44 */     return this;
/*    */   }
/*    */   
/*    */   public PatternBuilder number(String s) {
/* 48 */     s = s.replace("dddd", "d{4}").replace("ddd", "d{3}").replace("dd", "d{2}");
/* 49 */     s = s.replace("xxxx", "x{4}").replace("xxx", "x{3}").replace("xx", "x{2}");
/*    */     
/* 51 */     s = s.replace("d", "\\d").replace("x", "[0-9a-fA-F]").replaceAll("([\\.])", "\\\\$1");
/* 52 */     s = s.replaceAll("\\|$", "\\\\|").replaceAll("^\\|", "\\\\|");
/*    */     
/* 54 */     this.fragments.add(s);
/* 55 */     return this;
/*    */   }
/*    */   
/*    */   public PatternBuilder any() {
/* 59 */     this.fragments.add(".*");
/* 60 */     return this;
/*    */   }
/*    */   
/*    */   public PatternBuilder binary(String s) {
/* 64 */     this.fragments.add(s.replaceAll("(\\p{XDigit}{2})", "\\\\$1"));
/* 65 */     return this;
/*    */   }
/*    */   
/*    */   public PatternBuilder or() {
/* 69 */     this.fragments.add("|");
/* 70 */     return this;
/*    */   }
/*    */   
/*    */   public PatternBuilder groupBegin() {
/* 74 */     return expression("(?:");
/*    */   }
/*    */   
/*    */   public PatternBuilder groupEnd() {
/* 78 */     return expression(")");
/*    */   }
/*    */   
/*    */   public PatternBuilder groupEnd(String s) {
/* 82 */     return expression(")" + s);
/*    */   }
/*    */   
/*    */   public Pattern compile() {
/* 86 */     return Pattern.compile(toString(), 32);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 91 */     StringBuilder builder = new StringBuilder();
/* 92 */     for (String fragment : this.fragments) {
/* 93 */       builder.append(fragment);
/*    */     }
/* 95 */     return builder.toString();
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\helper\PatternBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */