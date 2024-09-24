/*     */ package org.traccar.helper;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
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
/*     */ public class LocationTree
/*     */ {
/*     */   private Item root;
/*     */   
/*     */   public static class Item
/*     */   {
/*     */     private Item left;
/*     */     private Item right;
/*     */     private float x;
/*     */     private float y;
/*     */     private String data;
/*     */     
/*     */     public Item(float x, float y) {
/*  32 */       this(x, y, null);
/*     */     }
/*     */     
/*     */     public Item(float x, float y, String data) {
/*  36 */       this.x = x;
/*  37 */       this.y = y;
/*  38 */       this.data = data;
/*     */     }
/*     */     
/*     */     public String getData() {
/*  42 */       return this.data;
/*     */     }
/*     */     
/*     */     private float squaredDistance(Item item) {
/*  46 */       return (this.x - item.x) * (this.x - item.x) + (this.y - item.y) * (this.y - item.y);
/*     */     }
/*     */     
/*     */     private float axisSquaredDistance(Item item, int axis) {
/*  50 */       if (axis == 0) {
/*  51 */         return (this.x - item.x) * (this.x - item.x);
/*     */       }
/*  53 */       return (this.y - item.y) * (this.y - item.y);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   private ArrayList<Comparator<Item>> comparators = new ArrayList<>();
/*     */   
/*     */   public LocationTree(List<Item> items) {
/*  64 */     this.comparators.add(new Comparator<Item>()
/*     */         {
/*     */           public int compare(LocationTree.Item o1, LocationTree.Item o2) {
/*  67 */             return Float.compare(o1.x, o2.x);
/*     */           }
/*     */         });
/*  70 */     this.comparators.add(new Comparator<Item>()
/*     */         {
/*     */           public int compare(LocationTree.Item o1, LocationTree.Item o2) {
/*  73 */             return Float.compare(o1.y, o2.y);
/*     */           }
/*     */         });
/*  76 */     this.root = createTree(items, 0);
/*     */   }
/*     */   
/*     */   private Item createTree(List<Item> items, int depth) {
/*  80 */     if (items.isEmpty()) {
/*  81 */       return null;
/*     */     }
/*  83 */     Collections.sort(items, this.comparators.get(depth % 2));
/*  84 */     int currentIndex = items.size() / 2;
/*  85 */     Item median = items.get(currentIndex);
/*  86 */     median.left = createTree(new ArrayList<>(items.subList(0, currentIndex)), depth + 1);
/*  87 */     median.right = createTree(new ArrayList<>(items.subList(currentIndex + 1, items.size())), depth + 1);
/*  88 */     return median;
/*     */   }
/*     */   
/*     */   public Item findNearest(Item search) {
/*  92 */     return findNearest(this.root, search, 0);
/*     */   }
/*     */   private Item findNearest(Item current, Item search, int depth) {
/*     */     Item next, other;
/*  96 */     int direction = ((Comparator<Item>)this.comparators.get(depth % 2)).compare(search, current);
/*     */ 
/*     */     
/*  99 */     if (direction < 0) {
/* 100 */       next = current.left;
/* 101 */       other = current.right;
/*     */     } else {
/* 103 */       next = current.right;
/* 104 */       other = current.left;
/*     */     } 
/*     */     
/* 107 */     Item best = current;
/* 108 */     if (next != null) {
/* 109 */       best = findNearest(next, search, depth + 1);
/*     */     }
/*     */     
/* 112 */     if (current.squaredDistance(search) < best.squaredDistance(search)) {
/* 113 */       best = current;
/*     */     }
/* 115 */     if (other != null && current.axisSquaredDistance(search, depth % 2) < best.squaredDistance(search)) {
/* 116 */       Item possibleBest = findNearest(other, search, depth + 1);
/* 117 */       if (possibleBest.squaredDistance(search) < best.squaredDistance(search)) {
/* 118 */         best = possibleBest;
/*     */       }
/*     */     } 
/*     */     
/* 122 */     return best;
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\helper\LocationTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */