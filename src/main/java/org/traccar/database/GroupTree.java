/*     */ package org.traccar.database;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.traccar.model.Device;
/*     */ import org.traccar.model.Group;
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
/*     */ public class GroupTree
/*     */ {
/*     */   private static class TreeNode
/*     */   {
/*     */     private Group group;
/*     */     private Device device;
/*  34 */     private Collection<TreeNode> children = new HashSet<>();
/*     */     
/*     */     TreeNode(Group group) {
/*  37 */       this.group = group;
/*     */     }
/*     */     
/*     */     TreeNode(Device device) {
/*  41 */       this.device = device;
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/*  46 */       if (this.group != null) {
/*  47 */         return (int)this.group.getId();
/*     */       }
/*  49 */       return (int)this.device.getId();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean equals(Object obj) {
/*  55 */       if (!(obj instanceof TreeNode)) {
/*  56 */         return false;
/*     */       }
/*  58 */       TreeNode other = (TreeNode)obj;
/*  59 */       if (other == this) {
/*  60 */         return true;
/*     */       }
/*  62 */       if (this.group != null && other.group != null)
/*  63 */         return (this.group.getId() == other.group.getId()); 
/*  64 */       if (this.device != null && other.device != null) {
/*  65 */         return (this.device.getId() == other.device.getId());
/*     */       }
/*  67 */       return false;
/*     */     }
/*     */     
/*     */     public Group getGroup() {
/*  71 */       return this.group;
/*     */     }
/*     */     
/*     */     public Device getDevice() {
/*  75 */       return this.device;
/*     */     }
/*     */     
/*     */     public void setParent(TreeNode parent) {
/*  79 */       if (parent != null) {
/*  80 */         parent.children.add(this);
/*     */       }
/*     */     }
/*     */     
/*     */     public Collection<TreeNode> getChildren() {
/*  85 */       return this.children;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*  90 */   private final Map<Long, TreeNode> groupMap = new HashMap<>();
/*     */ 
/*     */   
/*     */   public GroupTree(Collection<Group> groups, Collection<Device> devices) {
/*  94 */     for (Group group : groups) {
/*  95 */       this.groupMap.put(Long.valueOf(group.getId()), new TreeNode(group));
/*     */     }
/*     */     
/*  98 */     for (TreeNode node : this.groupMap.values()) {
/*  99 */       if (node.getGroup().getGroupId() != 0L) {
/* 100 */         node.setParent(this.groupMap.get(Long.valueOf(node.getGroup().getGroupId())));
/*     */       }
/*     */     } 
/*     */     
/* 104 */     Map<Long, TreeNode> deviceMap = new HashMap<>();
/*     */     
/* 106 */     for (Device device : devices) {
/* 107 */       deviceMap.put(Long.valueOf(device.getId()), new TreeNode(device));
/*     */     }
/*     */     
/* 110 */     for (TreeNode node : deviceMap.values()) {
/* 111 */       if (node.getDevice().getGroupId() != 0L) {
/* 112 */         node.setParent(this.groupMap.get(Long.valueOf(node.getDevice().getGroupId())));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<Group> getGroups(long groupId) {
/* 119 */     Set<TreeNode> results = new HashSet<>();
/* 120 */     getNodes(results, this.groupMap.get(Long.valueOf(groupId)));
/* 121 */     Collection<Group> groups = new ArrayList<>();
/* 122 */     for (TreeNode node : results) {
/* 123 */       if (node.getGroup() != null) {
/* 124 */         groups.add(node.getGroup());
/*     */       }
/*     */     } 
/* 127 */     return groups;
/*     */   }
/*     */   
/*     */   public Collection<Device> getDevices(long groupId) {
/* 131 */     Set<TreeNode> results = new HashSet<>();
/* 132 */     getNodes(results, this.groupMap.get(Long.valueOf(groupId)));
/* 133 */     Collection<Device> devices = new ArrayList<>();
/* 134 */     for (TreeNode node : results) {
/* 135 */       if (node.getDevice() != null) {
/* 136 */         devices.add(node.getDevice());
/*     */       }
/*     */     } 
/* 139 */     return devices;
/*     */   }
/*     */   
/*     */   private void getNodes(Set<TreeNode> results, TreeNode node) {
/* 143 */     if (node != null)
/* 144 */       for (TreeNode child : node.getChildren()) {
/* 145 */         results.add(child);
/* 146 */         getNodes(results, child);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\database\GroupTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */