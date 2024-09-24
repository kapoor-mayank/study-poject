/*    */ package org.traccar.database;
/*    */ 
/*    */ import org.traccar.model.Attribute;
/*    */ import org.traccar.model.BaseModel;
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
/*    */ public class AttributesManager
/*    */   extends ExtendedObjectManager<Attribute>
/*    */ {
/*    */   public AttributesManager(DataManager dataManager) {
/* 24 */     super(dataManager, Attribute.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateCachedItem(Attribute attribute) {
/* 29 */     Attribute cachedAttribute = getById(attribute.getId());
/* 30 */     cachedAttribute.setDescription(attribute.getDescription());
/* 31 */     cachedAttribute.setAttribute(attribute.getAttribute());
/* 32 */     cachedAttribute.setExpression(attribute.getExpression());
/* 33 */     cachedAttribute.setType(attribute.getType());
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\AttributesManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */