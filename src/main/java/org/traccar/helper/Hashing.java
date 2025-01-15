/*    */ package org.traccar.helper;
/*    */ 
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import java.security.SecureRandom;
/*    */ import java.security.spec.InvalidKeySpecException;
/*    */ import javax.crypto.SecretKeyFactory;
/*    */ import javax.crypto.spec.PBEKeySpec;
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
/*    */ public final class Hashing
/*    */ {
/*    */   public static final int ITERATIONS = 1000;
/*    */   public static final int SALT_SIZE = 24;
/*    */   public static final int HASH_SIZE = 24;
/*    */   private static SecretKeyFactory factory;
/*    */   
/*    */   static {
/*    */     try {
/* 33 */       factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
/* 34 */     } catch (NoSuchAlgorithmException e) {
/* 35 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class HashingResult
/*    */   {
/*    */     private final String hash;
/*    */     private final String salt;
/*    */     
/*    */     public HashingResult(String hash, String salt) {
/* 45 */       this.hash = hash;
/* 46 */       this.salt = salt;
/*    */     }
/*    */     
/*    */     public String getHash() {
/* 50 */       return this.hash;
/*    */     }
/*    */     
/*    */     public String getSalt() {
/* 54 */       return this.salt;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static byte[] function(char[] password, byte[] salt) {
/*    */     try {
/* 63 */       PBEKeySpec spec = new PBEKeySpec(password, salt, 1000, 192);
/* 64 */       return factory.generateSecret(spec).getEncoded();
/* 65 */     } catch (InvalidKeySpecException e) {
/* 66 */       throw new SecurityException(e);
/*    */     } 
/*    */   }
/*    */   
/* 70 */   private static final SecureRandom RANDOM = new SecureRandom();
/*    */   
/*    */   public static HashingResult createHash(String password) {
/* 73 */     byte[] salt = new byte[24];
/* 74 */     RANDOM.nextBytes(salt);
/* 75 */     byte[] hash = function(password.toCharArray(), salt);
/* 76 */     return new HashingResult(
/* 77 */         DataConverter.printHex(hash), 
/* 78 */         DataConverter.printHex(salt));
/*    */   }
/*    */   
/*    */   public static boolean validatePassword(String password, String hashHex, String saltHex) {
/* 82 */     byte[] hash = DataConverter.parseHex(hashHex);
/* 83 */     byte[] salt = DataConverter.parseHex(saltHex);
/* 84 */     return slowEquals(hash, function(password.toCharArray(), salt));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static boolean slowEquals(byte[] a, byte[] b) {
/* 93 */     int diff = a.length ^ b.length;
/* 94 */     for (int i = 0; i < a.length && i < b.length; i++) {
/* 95 */       diff |= a[i] ^ b[i];
/*    */     }
/* 97 */     return (diff == 0);
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\helper\Hashing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */