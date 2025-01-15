/*    */ package org.traccar.database;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.traccar.Context;
/*    */ import org.traccar.model.Device;
/*    */ import org.traccar.model.Geofence;
/*    */ import org.traccar.model.Position;
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
/*    */ public class GeofenceManager
/*    */   extends ExtendedObjectManager<Geofence>
/*    */ {
/*    */   public GeofenceManager(DataManager dataManager) {
/* 29 */     super(dataManager, Geofence.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public final void refreshExtendedPermissions() {
/* 34 */     super.refreshExtendedPermissions();
/* 35 */     recalculateDevicesGeofences();
/*    */   }
/*    */   
/*    */   public List<Long> getCurrentDeviceGeofences(Position position) {
/* 39 */     List<Long> result = new ArrayList<>();
/* 40 */     for (Iterator<Long> iterator = getAllDeviceItems(position.getDeviceId()).iterator(); iterator.hasNext(); ) { long geofenceId = ((Long)iterator.next()).longValue();
/* 41 */       Geofence geofence = getById(geofenceId);
/* 42 */       if (geofence != null && geofence.getGeometry()
/* 43 */         .containsPoint(position.getLatitude(), position.getLongitude())) {
/* 44 */         result.add(Long.valueOf(geofenceId));
/*    */       } }
/*    */     
/* 47 */     return result;
/*    */   }
/*    */   
/*    */   public void recalculateDevicesGeofences() {
/* 51 */     for (Device device : Context.getDeviceManager().getAllDevices()) {
/* 52 */       List<Long> deviceGeofenceIds = device.getGeofenceIds();
/* 53 */       if (deviceGeofenceIds == null) {
/* 54 */         deviceGeofenceIds = new ArrayList<>();
/*    */       } else {
/* 56 */         deviceGeofenceIds.clear();
/*    */       } 
/* 58 */       Position lastPosition = Context.getIdentityManager().getLastPosition(device.getId());
/* 59 */       if (lastPosition != null && getAllDeviceItems(device.getId()) != null) {
/* 60 */         deviceGeofenceIds.addAll(getCurrentDeviceGeofences(lastPosition));
/*    */       }
/* 62 */       device.setGeofenceIds(deviceGeofenceIds);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\GeofenceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */