/*    */ package org.traccar.helper;
/*    */ 
/*    */ import java.lang.management.ManagementFactory;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import java.util.regex.PatternSyntaxException;
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
/*    */ 
/*    */ public final class PatternUtil
/*    */ {
/* 28 */   private static final Logger LOGGER = LoggerFactory.getLogger(PatternUtil.class);
/*    */ 
/*    */   
/*    */   public static class MatchResult
/*    */   {
/*    */     private String patternMatch;
/*    */     
/*    */     private String patternTail;
/*    */     private String stringMatch;
/*    */     private String stringTail;
/*    */     
/*    */     public String getPatternMatch() {
/* 40 */       return this.patternMatch;
/*    */     }
/*    */     
/*    */     public String getPatternTail() {
/* 44 */       return this.patternTail;
/*    */     }
/*    */     
/*    */     public String getStringMatch() {
/* 48 */       return this.stringMatch;
/*    */     }
/*    */     
/*    */     public String getStringTail() {
/* 52 */       return this.stringTail;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static MatchResult checkPattern(String pattern, String input) {
/* 58 */     if (!ManagementFactory.getRuntimeMXBean().getInputArguments().toString().contains("-agentlib:jdwp")) {
/* 59 */       throw new RuntimeException("PatternUtil usage detected");
/*    */     }
/*    */     
/* 62 */     MatchResult result = new MatchResult();
/*    */     
/* 64 */     for (int i = 0; i < pattern.length(); i++) {
/*    */       try {
/* 66 */         Matcher matcher = Pattern.compile("(" + pattern.substring(0, i) + ").*").matcher(input);
/* 67 */         if (matcher.matches()) {
/* 68 */           result.patternMatch = pattern.substring(0, i);
/* 69 */           result.patternTail = pattern.substring(i);
/* 70 */           result.stringMatch = matcher.group(1);
/* 71 */           result.stringTail = input.substring(matcher.group(1).length());
/*    */         } 
/* 73 */       } catch (PatternSyntaxException error) {
/* 74 */         LOGGER.warn("Pattern matching error", error);
/*    */       } 
/*    */     } 
/*    */     
/* 78 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\helper\PatternUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */