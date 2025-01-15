/*     */ package org.traccar.database;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import javax.naming.NamingEnumeration;
/*     */ import javax.naming.NamingException;
/*     */ import javax.naming.directory.Attribute;
/*     */ import javax.naming.directory.InitialDirContext;
/*     */ import javax.naming.directory.SearchControls;
/*     */ import javax.naming.directory.SearchResult;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.config.Config;
/*     */ import org.traccar.model.User;
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
/*     */ public class LdapProvider
/*     */ {
/*  35 */   private static final Logger LOGGER = LoggerFactory.getLogger(LdapProvider.class);
/*     */   
/*     */   private String url;
/*     */   private String searchBase;
/*     */   private String idAttribute;
/*     */   private String nameAttribute;
/*     */   private String mailAttribute;
/*     */   private String searchFilter;
/*     */   private String adminFilter;
/*     */   private String serviceUser;
/*     */   private String servicePassword;
/*     */   
/*     */   public LdapProvider(Config config) {
/*  48 */     String url = config.getString("ldap.url");
/*  49 */     if (url != null) {
/*  50 */       this.url = url;
/*     */     } else {
/*  52 */       this.url = "ldap://" + config.getString("ldap.server") + ":" + config.getInteger("ldap.port", 389);
/*     */     } 
/*  54 */     this.searchBase = config.getString("ldap.base");
/*  55 */     this.idAttribute = config.getString("ldap.idAttribute", "uid");
/*  56 */     this.nameAttribute = config.getString("ldap.nameAttribute", "cn");
/*  57 */     this.mailAttribute = config.getString("ldap.mailAttribute", "mail");
/*  58 */     this.searchFilter = config.getString("ldap.searchFilter", "(" + this.idAttribute + "=:login)");
/*  59 */     String adminGroup = config.getString("ldap.adminGroup");
/*  60 */     this.adminFilter = config.getString("ldap.adminFilter");
/*  61 */     if (this.adminFilter == null && adminGroup != null) {
/*  62 */       this.adminFilter = "(&(" + this.idAttribute + "=:login)(memberOf=" + adminGroup + "))";
/*     */     }
/*  64 */     this.serviceUser = config.getString("ldap.user");
/*  65 */     this.servicePassword = config.getString("ldap.password");
/*     */   }
/*     */   
/*     */   private InitialDirContext auth(String accountName, String password) throws NamingException {
/*  69 */     Hashtable<String, String> env = new Hashtable<>();
/*  70 */     env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
/*  71 */     env.put("java.naming.provider.url", this.url);
/*     */     
/*  73 */     env.put("java.naming.security.authentication", "simple");
/*  74 */     env.put("java.naming.security.principal", accountName);
/*  75 */     env.put("java.naming.security.credentials", password);
/*     */     
/*  77 */     return new InitialDirContext(env);
/*     */   }
/*     */   
/*     */   private boolean isAdmin(String accountName) {
/*  81 */     if (this.adminFilter != null) {
/*     */       try {
/*  83 */         InitialDirContext context = initContext();
/*  84 */         String searchString = this.adminFilter.replace(":login", accountName);
/*  85 */         SearchControls searchControls = new SearchControls();
/*  86 */         searchControls.setSearchScope(2);
/*  87 */         NamingEnumeration<SearchResult> results = context.search(this.searchBase, searchString, searchControls);
/*  88 */         if (results.hasMoreElements()) {
/*  89 */           results.nextElement();
/*  90 */           if (results.hasMoreElements()) {
/*  91 */             LOGGER.warn("Matched multiple users for the accountName: " + accountName);
/*  92 */             return false;
/*     */           } 
/*  94 */           return true;
/*     */         } 
/*  96 */       } catch (NamingException e) {
/*  97 */         return false;
/*     */       } 
/*     */     }
/* 100 */     return false;
/*     */   }
/*     */   
/*     */   public InitialDirContext initContext() throws NamingException {
/* 104 */     return auth(this.serviceUser, this.servicePassword);
/*     */   }
/*     */   
/*     */   private SearchResult lookupUser(String accountName) throws NamingException {
/* 108 */     InitialDirContext context = initContext();
/*     */     
/* 110 */     String searchString = this.searchFilter.replace(":login", accountName);
/*     */     
/* 112 */     SearchControls searchControls = new SearchControls();
/* 113 */     String[] attributeFilter = { this.idAttribute, this.nameAttribute, this.mailAttribute };
/* 114 */     searchControls.setReturningAttributes(attributeFilter);
/* 115 */     searchControls.setSearchScope(2);
/*     */     
/* 117 */     NamingEnumeration<SearchResult> results = context.search(this.searchBase, searchString, searchControls);
/*     */     
/* 119 */     SearchResult searchResult = null;
/* 120 */     if (results.hasMoreElements()) {
/* 121 */       searchResult = results.nextElement();
/* 122 */       if (results.hasMoreElements()) {
/* 123 */         LOGGER.warn("Matched multiple users for the accountName: " + accountName);
/* 124 */         return null;
/*     */       } 
/*     */     } 
/*     */     
/* 128 */     return searchResult;
/*     */   }
/*     */ 
/*     */   
/*     */   public User getUser(String accountName) {
/* 133 */     User user = new User();
/*     */     try {
/* 135 */       SearchResult ldapUser = lookupUser(accountName);
/* 136 */       if (ldapUser != null) {
/* 137 */         Attribute attribute = ldapUser.getAttributes().get(this.idAttribute);
/* 138 */         if (attribute != null) {
/* 139 */           user.setLogin((String)attribute.get());
/*     */         } else {
/* 141 */           user.setLogin(accountName);
/*     */         } 
/* 143 */         attribute = ldapUser.getAttributes().get(this.nameAttribute);
/* 144 */         if (attribute != null) {
/* 145 */           user.setName((String)attribute.get());
/*     */         } else {
/* 147 */           user.setName(accountName);
/*     */         } 
/* 149 */         attribute = ldapUser.getAttributes().get(this.mailAttribute);
/* 150 */         if (attribute != null) {
/* 151 */           user.setEmail((String)attribute.get());
/*     */         } else {
/* 153 */           user.setEmail(accountName);
/*     */         } 
/*     */       } 
/* 156 */       user.setAdministrator(isAdmin(accountName));
/* 157 */     } catch (NamingException e) {
/* 158 */       user.setLogin(accountName);
/* 159 */       user.setName(accountName);
/* 160 */       user.setEmail(accountName);
/* 161 */       LOGGER.warn("User lookup error", e);
/*     */     } 
/* 163 */     return user;
/*     */   }
/*     */   
/*     */   public boolean login(String username, String password) {
/*     */     try {
/* 168 */       SearchResult ldapUser = lookupUser(username);
/* 169 */       if (ldapUser != null) {
/* 170 */         auth(ldapUser.getNameInNamespace(), password).close();
/* 171 */         return true;
/*     */       } 
/* 173 */     } catch (NamingException e) {
/* 174 */       return false;
/*     */     } 
/* 176 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\LdapProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */