/*     */ package org.traccar.handler;
/*     */ 
/*     */ import io.netty.channel.ChannelHandler.Sharable;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.jexl2.JexlContext;
/*     */ import org.apache.commons.jexl2.JexlEngine;
/*     */ import org.apache.commons.jexl2.JexlException;
/*     */ import org.apache.commons.jexl2.MapContext;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.traccar.BaseDataHandler;
/*     */ import org.traccar.config.Config;
/*     */ import org.traccar.config.Keys;
/*     */ import org.traccar.database.AttributesManager;
/*     */ import org.traccar.database.IdentityManager;
/*     */ import org.traccar.model.Attribute;
/*     */ import org.traccar.model.Device;
/*     */ import org.traccar.model.Position;
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
/*     */ @Sharable
/*     */ public class ComputedAttributesHandler
/*     */   extends BaseDataHandler
/*     */ {
/*  46 */   private static final Logger LOGGER = LoggerFactory.getLogger(ComputedAttributesHandler.class);
/*     */   
/*     */   private final IdentityManager identityManager;
/*     */   
/*     */   private final AttributesManager attributesManager;
/*     */   
/*     */   private final JexlEngine engine;
/*     */   
/*     */   private final boolean includeDeviceAttributes;
/*     */   
/*     */   public ComputedAttributesHandler(Config config, IdentityManager identityManager, AttributesManager attributesManager) {
/*  57 */     this.identityManager = identityManager;
/*  58 */     this.attributesManager = attributesManager;
/*  59 */     this.engine = new JexlEngine();
/*  60 */     this.engine.setStrict(true);
/*  61 */     this.engine.setFunctions(Collections.singletonMap("math", Math.class));
/*  62 */     this.includeDeviceAttributes = config.getBoolean(Keys.PROCESSING_COMPUTED_ATTRIBUTES_DEVICE_ATTRIBUTES);
/*     */   }
/*     */   
/*     */   private MapContext prepareContext(Position position) {
/*  66 */     MapContext result = new MapContext();
/*  67 */     if (this.includeDeviceAttributes) {
/*  68 */       Device device = this.identityManager.getById(position.getDeviceId());
/*  69 */       if (device != null) {
/*  70 */         for (Object key : device.getAttributes().keySet()) {
/*  71 */           result.set((String)key, device.getAttributes().get(key));
/*     */         }
/*     */       }
/*     */     } 
/*  75 */     Set<Method> methods = new HashSet<>(Arrays.asList(position.getClass().getMethods()));
/*  76 */     methods.removeAll(Arrays.asList((Object[])Object.class.getMethods()));
/*  77 */     for (Method method : methods) {
/*  78 */       if (method.getName().startsWith("get") && (method.getParameterTypes()).length == 0) {
/*  79 */         String name = Character.toLowerCase(method.getName().charAt(3)) + method.getName().substring(4);
/*     */         
/*     */         try {
/*  82 */           if (!method.getReturnType().equals(Map.class)) {
/*  83 */             result.set(name, method.invoke(position, new Object[0])); continue;
/*     */           } 
/*  85 */           for (Object key : ((Map)method.invoke(position, new Object[0])).keySet()) {
/*  86 */             result.set((String)key, ((Map)method.invoke(position, new Object[0])).get(key));
/*     */           }
/*     */         }
/*  89 */         catch (IllegalAccessException|java.lang.reflect.InvocationTargetException error) {
/*  90 */           LOGGER.warn("Attribute reflection error", error);
/*     */         } 
/*     */       } 
/*     */     } 
/*  94 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Object computeAttribute(Attribute attribute, Position position) throws JexlException {
/* 102 */     return this.engine.createExpression(attribute.getExpression()).evaluate((JexlContext)prepareContext(position));
/*     */   }
/*     */ 
/*     */   
/*     */   protected Position handlePosition(Position position) {
/* 107 */     Collection<Attribute> attributes = this.attributesManager.getItems(this.attributesManager
/* 108 */         .getAllDeviceItems(position.getDeviceId()));
/* 109 */     for (Attribute attribute : attributes) {
/* 110 */       if (attribute.getAttribute() != null) {
/* 111 */         Object result = null;
/*     */         try {
/* 113 */           result = computeAttribute(attribute, position);
/* 114 */         } catch (JexlException error) {
/* 115 */           LOGGER.warn("Attribute computation error", (Throwable)error);
/*     */         } 
/* 117 */         if (result != null) {
/*     */           try {
/* 119 */             Number numberValue; Boolean booleanValue; switch (attribute.getType()) {
/*     */               case "number":
/* 121 */                 numberValue = (Number)result;
/* 122 */                 position.getAttributes().put(attribute.getAttribute(), numberValue);
/*     */                 continue;
/*     */               case "boolean":
/* 125 */                 booleanValue = (Boolean)result;
/* 126 */                 position.getAttributes().put(attribute.getAttribute(), booleanValue);
/*     */                 continue;
/*     */             } 
/* 129 */             position.getAttributes().put(attribute.getAttribute(), result.toString());
/*     */           }
/* 131 */           catch (ClassCastException error) {
/* 132 */             LOGGER.warn("Attribute cast error", error);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/* 137 */     return position;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\handler\ComputedAttributesHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */