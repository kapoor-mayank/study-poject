/*     */ package org.traccar.api;
/*     */ 
/*     */ import java.sql.SQLException;
/*     */ import java.util.Set;
/*     */ import javax.ws.rs.DELETE;
/*     */ import javax.ws.rs.POST;
/*     */ import javax.ws.rs.PUT;
/*     */ import javax.ws.rs.Path;
/*     */ import javax.ws.rs.PathParam;
/*     */ import javax.ws.rs.core.Response;
/*     */ import org.traccar.Context;
/*     */ import org.traccar.database.BaseObjectManager;
/*     */ import org.traccar.database.ExtendedObjectManager;
/*     */ import org.traccar.database.ManagableObjects;
/*     */ import org.traccar.database.SimpleObjectManager;
/*     */ import org.traccar.helper.LogAction;
/*     */ import org.traccar.model.BaseModel;
/*     */ import org.traccar.model.Calendar;
/*     */ import org.traccar.model.Command;
/*     */ import org.traccar.model.Device;
/*     */ import org.traccar.model.Group;
/*     */ import org.traccar.model.GroupedModel;
/*     */ import org.traccar.model.ScheduledModel;
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
/*     */ public abstract class BaseObjectResource<T extends BaseModel>
/*     */   extends BaseResource
/*     */ {
/*     */   private Class<T> baseClass;
/*     */   
/*     */   public BaseObjectResource(Class<T> baseClass) {
/*  49 */     this.baseClass = baseClass;
/*     */   }
/*     */   
/*     */   protected final Class<T> getBaseClass() {
/*  53 */     return this.baseClass;
/*     */   }
/*     */   
/*     */   protected final Set<Long> getSimpleManagerItems(BaseObjectManager<T> manager, boolean all, long userId) {
/*  57 */     Set<Long> result = null;
/*  58 */     if (all) {
/*  59 */       if (Context.getPermissionsManager().getUserAdmin(getUserId())) {
/*  60 */         result = manager.getAllItems();
/*     */       } else {
/*  62 */         Context.getPermissionsManager().checkManager(getUserId());
/*  63 */         result = ((ManagableObjects)manager).getManagedItems(getUserId());
/*     */       } 
/*     */     } else {
/*  66 */       if (userId == 0L) {
/*  67 */         userId = getUserId();
/*     */       }
/*  69 */       Context.getPermissionsManager().checkUser(getUserId(), userId);
/*  70 */       result = ((ManagableObjects)manager).getUserItems(userId);
/*     */     } 
/*  72 */     return result;
/*     */   }
/*     */   
/*     */   @POST
/*     */   public Response add(T entity) throws SQLException {
/*  77 */     Context.getPermissionsManager().checkReadonly(getUserId());
/*  78 */     if (this.baseClass.equals(Device.class)) {
/*  79 */       Context.getPermissionsManager().checkDeviceReadonly(getUserId());
/*  80 */       Context.getPermissionsManager().checkDeviceLimit(getUserId());
/*  81 */     } else if (this.baseClass.equals(Command.class)) {
/*  82 */       Context.getPermissionsManager().checkLimitCommands(getUserId());
/*  83 */     } else if (entity instanceof GroupedModel && ((GroupedModel)entity).getGroupId() != 0L) {
/*  84 */       Context.getPermissionsManager().checkPermission(Group.class, 
/*  85 */           getUserId(), ((GroupedModel)entity).getGroupId());
/*  86 */     } else if (entity instanceof ScheduledModel && ((ScheduledModel)entity).getCalendarId() != 0L) {
/*  87 */       Context.getPermissionsManager().checkPermission(Calendar.class, 
/*  88 */           getUserId(), ((ScheduledModel)entity).getCalendarId());
/*     */     } 
/*     */     
/*  91 */     BaseObjectManager<T> manager = Context.getManager(this.baseClass);
/*  92 */     manager.addItem((T) entity);
/*  93 */     LogAction.create(getUserId(), (BaseModel)entity);
/*     */     
/*  95 */     Context.getDataManager().linkObject(User.class, getUserId(), this.baseClass, entity.getId(), true);
/*  96 */     LogAction.link(getUserId(), User.class, getUserId(), this.baseClass, entity.getId());
/*     */     
/*  98 */     if (manager instanceof SimpleObjectManager) {
/*  99 */       ((SimpleObjectManager)manager).refreshUserItems();
/* 100 */     } else if (this.baseClass.equals(Group.class) || this.baseClass.equals(Device.class)) {
/* 101 */       Context.getPermissionsManager().refreshDeviceAndGroupPermissions();
/* 102 */       Context.getPermissionsManager().refreshAllExtendedPermissions();
/*     */     } 
/* 104 */     return Response.ok(entity).build();
/*     */   }
/*     */   
/*     */   @Path("{id}")
/*     */   @PUT
/*     */   public Response update(T entity) throws SQLException {
/* 110 */     Context.getPermissionsManager().checkReadonly(getUserId());
/* 111 */     if (this.baseClass.equals(Device.class)) {
/* 112 */       Context.getPermissionsManager().checkDeviceReadonly(getUserId());
/* 113 */     } else if (this.baseClass.equals(User.class)) {
/* 114 */       User before = Context.getPermissionsManager().getUser(entity.getId());
/* 115 */       Context.getPermissionsManager().checkUserUpdate(getUserId(), before, (User)entity);
/* 116 */     } else if (this.baseClass.equals(Command.class)) {
/* 117 */       Context.getPermissionsManager().checkLimitCommands(getUserId());
/* 118 */     } else if (entity instanceof GroupedModel && ((GroupedModel)entity).getGroupId() != 0L) {
/* 119 */       Context.getPermissionsManager().checkPermission(Group.class, 
/* 120 */           getUserId(), ((GroupedModel)entity).getGroupId());
/* 121 */     } else if (entity instanceof ScheduledModel && ((ScheduledModel)entity).getCalendarId() != 0L) {
/* 122 */       Context.getPermissionsManager().checkPermission(Calendar.class, 
/* 123 */           getUserId(), ((ScheduledModel)entity).getCalendarId());
/*     */     } 
/* 125 */     Context.getPermissionsManager().checkPermission(this.baseClass, getUserId(), entity.getId());
/*     */     
/* 127 */     Context.getManager(this.baseClass).updateItem((T) entity);
/* 128 */     LogAction.edit(getUserId(), (BaseModel)entity);
/*     */     
/* 130 */     if (this.baseClass.equals(Group.class) || this.baseClass.equals(Device.class)) {
/* 131 */       Context.getPermissionsManager().refreshDeviceAndGroupPermissions();
/* 132 */       Context.getPermissionsManager().refreshAllExtendedPermissions();
/*     */     } 
/* 134 */     return Response.ok(entity).build();
/*     */   }
/*     */   
/*     */   @Path("{id}")
/*     */   @DELETE
/*     */   public Response remove(@PathParam("id") long id) throws SQLException {
/* 140 */     Context.getPermissionsManager().checkReadonly(getUserId());
/* 141 */     if (this.baseClass.equals(Device.class)) {
/* 142 */       Context.getPermissionsManager().checkDeviceReadonly(getUserId());
/* 143 */     } else if (this.baseClass.equals(Command.class)) {
/* 144 */       Context.getPermissionsManager().checkLimitCommands(getUserId());
/*     */     } 
/* 146 */     Context.getPermissionsManager().checkPermission(this.baseClass, getUserId(), id);
/*     */     
/* 148 */     BaseObjectManager<T> manager = Context.getManager(this.baseClass);
/* 149 */     manager.removeItem(id);
/* 150 */     LogAction.remove(getUserId(), this.baseClass, id);
/*     */     
/* 152 */     if (manager instanceof SimpleObjectManager) {
/* 153 */       ((SimpleObjectManager)manager).refreshUserItems();
/* 154 */       if (manager instanceof ExtendedObjectManager) {
/* 155 */         ((ExtendedObjectManager)manager).refreshExtendedPermissions();
/*     */       }
/*     */     } 
/* 158 */     if (this.baseClass.equals(Group.class) || this.baseClass.equals(Device.class) || this.baseClass.equals(User.class)) {
/* 159 */       if (this.baseClass.equals(Group.class)) {
/* 160 */         Context.getGroupsManager().updateGroupCache(true);
/* 161 */         Context.getDeviceManager().updateDeviceCache(true);
/*     */       } 
/* 163 */       Context.getPermissionsManager().refreshDeviceAndGroupPermissions();
/* 164 */       if (this.baseClass.equals(User.class)) {
/* 165 */         Context.getPermissionsManager().refreshAllUsersPermissions();
/*     */       } else {
/* 167 */         Context.getPermissionsManager().refreshAllExtendedPermissions();
/*     */       } 
/* 169 */     } else if (this.baseClass.equals(Calendar.class)) {
/* 170 */       Context.getGeofenceManager().refreshItems();
/* 171 */       Context.getNotificationManager().refreshItems();
/*     */     } 
/* 173 */     return Response.noContent().build();
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\api\BaseObjectResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */