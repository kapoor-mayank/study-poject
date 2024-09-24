/*     */ package com.google.protobuf;
/*     */ 
/*     */ import java.util.AbstractList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RepeatedFieldBuilder<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder>
/*     */   implements GeneratedMessage.BuilderParent
/*     */ {
/*     */   private GeneratedMessage.BuilderParent parent;
/*     */   private List<MType> messages;
/*     */   private boolean isMessagesListMutable;
/*     */   private List<SingleFieldBuilder<MType, BType, IType>> builders;
/*     */   private boolean isClean;
/*     */   private MessageExternalList<MType, BType, IType> externalMessageList;
/*     */   private BuilderExternalList<MType, BType, IType> externalBuilderList;
/*     */   private MessageOrBuilderExternalList<MType, BType, IType> externalMessageOrBuilderList;
/*     */   
/*     */   public RepeatedFieldBuilder(List<MType> messages, boolean isMessagesListMutable, GeneratedMessage.BuilderParent parent, boolean isClean) {
/* 131 */     this.messages = messages;
/* 132 */     this.isMessagesListMutable = isMessagesListMutable;
/* 133 */     this.parent = parent;
/* 134 */     this.isClean = isClean;
/*     */   }
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 139 */     this.parent = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ensureMutableMessageList() {
/* 147 */     if (!this.isMessagesListMutable) {
/* 148 */       this.messages = new ArrayList<>(this.messages);
/* 149 */       this.isMessagesListMutable = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ensureBuilders() {
/* 158 */     if (this.builders == null) {
/* 159 */       this.builders = new ArrayList<>(this.messages.size());
/* 160 */       for (int i = 0; i < this.messages.size(); i++) {
/* 161 */         this.builders.add(null);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCount() {
/* 172 */     return this.messages.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 181 */     return this.messages.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MType getMessage(int index) {
/* 193 */     return getMessage(index, false);
/*     */   }
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
/*     */   private MType getMessage(int index, boolean forBuild) {
/* 207 */     if (this.builders == null)
/*     */     {
/*     */ 
/*     */       
/* 211 */       return this.messages.get(index);
/*     */     }
/*     */     
/* 214 */     SingleFieldBuilder<MType, BType, IType> builder = this.builders.get(index);
/* 215 */     if (builder == null)
/*     */     {
/*     */ 
/*     */       
/* 219 */       return this.messages.get(index);
/*     */     }
/*     */     
/* 222 */     return forBuild ? builder.build() : builder.getMessage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BType getBuilder(int index) {
/* 234 */     ensureBuilders();
/* 235 */     SingleFieldBuilder<MType, BType, IType> builder = this.builders.get(index);
/* 236 */     if (builder == null) {
/* 237 */       GeneratedMessage generatedMessage = (GeneratedMessage)this.messages.get(index);
/* 238 */       builder = new SingleFieldBuilder<>((MType)generatedMessage, this, this.isClean);
/* 239 */       this.builders.set(index, builder);
/*     */     } 
/* 241 */     return builder.getBuilder();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IType getMessageOrBuilder(int index) {
/* 253 */     if (this.builders == null)
/*     */     {
/*     */ 
/*     */       
/* 257 */       return (IType)this.messages.get(index);
/*     */     }
/*     */     
/* 260 */     SingleFieldBuilder<MType, BType, IType> builder = this.builders.get(index);
/* 261 */     if (builder == null)
/*     */     {
/*     */ 
/*     */       
/* 265 */       return (IType)this.messages.get(index);
/*     */     }
/*     */     
/* 268 */     return builder.getMessageOrBuilder();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RepeatedFieldBuilder<MType, BType, IType> setMessage(int index, MType message) {
/* 280 */     Internal.checkNotNull(message);
/* 281 */     ensureMutableMessageList();
/* 282 */     this.messages.set(index, message);
/* 283 */     if (this.builders != null) {
/* 284 */       SingleFieldBuilder<MType, BType, IType> entry = this.builders.set(index, null);
/* 285 */       if (entry != null) {
/* 286 */         entry.dispose();
/*     */       }
/*     */     } 
/* 289 */     onChanged();
/* 290 */     incrementModCounts();
/* 291 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RepeatedFieldBuilder<MType, BType, IType> addMessage(MType message) {
/* 301 */     Internal.checkNotNull(message);
/* 302 */     ensureMutableMessageList();
/* 303 */     this.messages.add(message);
/* 304 */     if (this.builders != null) {
/* 305 */       this.builders.add(null);
/*     */     }
/* 307 */     onChanged();
/* 308 */     incrementModCounts();
/* 309 */     return this;
/*     */   }
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
/*     */   public RepeatedFieldBuilder<MType, BType, IType> addMessage(int index, MType message) {
/* 322 */     Internal.checkNotNull(message);
/* 323 */     ensureMutableMessageList();
/* 324 */     this.messages.add(index, message);
/* 325 */     if (this.builders != null) {
/* 326 */       this.builders.add(index, null);
/*     */     }
/* 328 */     onChanged();
/* 329 */     incrementModCounts();
/* 330 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RepeatedFieldBuilder<MType, BType, IType> addAllMessages(Iterable<? extends MType> values) {
/* 342 */     for (GeneratedMessage generatedMessage : values) {
/* 343 */       Internal.checkNotNull(generatedMessage);
/*     */     }
/*     */ 
/*     */     
/* 347 */     int size = -1;
/* 348 */     if (values instanceof Collection) {
/* 349 */       Collection<?> collection = (Collection)values;
/* 350 */       if (collection.isEmpty()) {
/* 351 */         return this;
/*     */       }
/* 353 */       size = collection.size();
/*     */     } 
/* 355 */     ensureMutableMessageList();
/*     */     
/* 357 */     if (size >= 0 && this.messages instanceof ArrayList) {
/* 358 */       ((ArrayList)this.messages).ensureCapacity(this.messages.size() + size);
/*     */     }
/*     */     
/* 361 */     for (GeneratedMessage generatedMessage : values) {
/* 362 */       addMessage((MType)generatedMessage);
/*     */     }
/*     */     
/* 365 */     onChanged();
/* 366 */     incrementModCounts();
/* 367 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BType addBuilder(MType message) {
/* 377 */     ensureMutableMessageList();
/* 378 */     ensureBuilders();
/* 379 */     SingleFieldBuilder<MType, BType, IType> builder = new SingleFieldBuilder<>(message, this, this.isClean);
/*     */     
/* 381 */     this.messages.add(null);
/* 382 */     this.builders.add(builder);
/* 383 */     onChanged();
/* 384 */     incrementModCounts();
/* 385 */     return builder.getBuilder();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BType addBuilder(int index, MType message) {
/* 397 */     ensureMutableMessageList();
/* 398 */     ensureBuilders();
/* 399 */     SingleFieldBuilder<MType, BType, IType> builder = new SingleFieldBuilder<>(message, this, this.isClean);
/*     */     
/* 401 */     this.messages.add(index, null);
/* 402 */     this.builders.add(index, builder);
/* 403 */     onChanged();
/* 404 */     incrementModCounts();
/* 405 */     return builder.getBuilder();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(int index) {
/* 415 */     ensureMutableMessageList();
/* 416 */     this.messages.remove(index);
/* 417 */     if (this.builders != null) {
/* 418 */       SingleFieldBuilder<MType, BType, IType> entry = this.builders.remove(index);
/* 419 */       if (entry != null) {
/* 420 */         entry.dispose();
/*     */       }
/*     */     } 
/* 423 */     onChanged();
/* 424 */     incrementModCounts();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 429 */     this.messages = Collections.emptyList();
/* 430 */     this.isMessagesListMutable = false;
/* 431 */     if (this.builders != null) {
/* 432 */       for (SingleFieldBuilder<MType, BType, IType> entry : this.builders) {
/* 433 */         if (entry != null) {
/* 434 */           entry.dispose();
/*     */         }
/*     */       } 
/* 437 */       this.builders = null;
/*     */     } 
/* 439 */     onChanged();
/* 440 */     incrementModCounts();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<MType> build() {
/* 451 */     this.isClean = true;
/*     */     
/* 453 */     if (!this.isMessagesListMutable && this.builders == null)
/*     */     {
/* 455 */       return this.messages;
/*     */     }
/*     */     
/* 458 */     boolean allMessagesInSync = true;
/* 459 */     if (!this.isMessagesListMutable) {
/*     */ 
/*     */       
/* 462 */       for (int j = 0; j < this.messages.size(); j++) {
/* 463 */         Message message = (Message)this.messages.get(j);
/* 464 */         SingleFieldBuilder<MType, BType, IType> builder = this.builders.get(j);
/* 465 */         if (builder != null && 
/* 466 */           builder.build() != message) {
/* 467 */           allMessagesInSync = false;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 472 */       if (allMessagesInSync)
/*     */       {
/* 474 */         return this.messages;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 479 */     ensureMutableMessageList();
/* 480 */     for (int i = 0; i < this.messages.size(); i++) {
/* 481 */       this.messages.set(i, getMessage(i, true));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 486 */     this.messages = Collections.unmodifiableList(this.messages);
/* 487 */     this.isMessagesListMutable = false;
/* 488 */     return this.messages;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<MType> getMessageList() {
/* 498 */     if (this.externalMessageList == null) {
/* 499 */       this.externalMessageList = new MessageExternalList<>(this);
/*     */     }
/* 501 */     return this.externalMessageList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<BType> getBuilderList() {
/* 511 */     if (this.externalBuilderList == null) {
/* 512 */       this.externalBuilderList = new BuilderExternalList<>(this);
/*     */     }
/* 514 */     return this.externalBuilderList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<IType> getMessageOrBuilderList() {
/* 524 */     if (this.externalMessageOrBuilderList == null) {
/* 525 */       this.externalMessageOrBuilderList = new MessageOrBuilderExternalList<>(this);
/*     */     }
/* 527 */     return this.externalMessageOrBuilderList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void onChanged() {
/* 535 */     if (this.isClean && this.parent != null) {
/* 536 */       this.parent.markDirty();
/*     */ 
/*     */       
/* 539 */       this.isClean = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void markDirty() {
/* 545 */     onChanged();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void incrementModCounts() {
/* 553 */     if (this.externalMessageList != null) {
/* 554 */       this.externalMessageList.incrementModCount();
/*     */     }
/* 556 */     if (this.externalBuilderList != null) {
/* 557 */       this.externalBuilderList.incrementModCount();
/*     */     }
/* 559 */     if (this.externalMessageOrBuilderList != null) {
/* 560 */       this.externalMessageOrBuilderList.incrementModCount();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class MessageExternalList<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder>
/*     */     extends AbstractList<MType>
/*     */     implements List<MType>
/*     */   {
/*     */     RepeatedFieldBuilder<MType, BType, IType> builder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     MessageExternalList(RepeatedFieldBuilder<MType, BType, IType> builder) {
/* 580 */       this.builder = builder;
/*     */     }
/*     */ 
/*     */     
/*     */     public int size() {
/* 585 */       return this.builder.getCount();
/*     */     }
/*     */ 
/*     */     
/*     */     public MType get(int index) {
/* 590 */       return this.builder.getMessage(index);
/*     */     }
/*     */     
/*     */     void incrementModCount() {
/* 594 */       this.modCount++;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class BuilderExternalList<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder>
/*     */     extends AbstractList<BType>
/*     */     implements List<BType>
/*     */   {
/*     */     RepeatedFieldBuilder<MType, BType, IType> builder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     BuilderExternalList(RepeatedFieldBuilder<MType, BType, IType> builder) {
/* 614 */       this.builder = builder;
/*     */     }
/*     */ 
/*     */     
/*     */     public int size() {
/* 619 */       return this.builder.getCount();
/*     */     }
/*     */ 
/*     */     
/*     */     public BType get(int index) {
/* 624 */       return this.builder.getBuilder(index);
/*     */     }
/*     */     
/*     */     void incrementModCount() {
/* 628 */       this.modCount++;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class MessageOrBuilderExternalList<MType extends GeneratedMessage, BType extends GeneratedMessage.Builder, IType extends MessageOrBuilder>
/*     */     extends AbstractList<IType>
/*     */     implements List<IType>
/*     */   {
/*     */     RepeatedFieldBuilder<MType, BType, IType> builder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     MessageOrBuilderExternalList(RepeatedFieldBuilder<MType, BType, IType> builder) {
/* 648 */       this.builder = builder;
/*     */     }
/*     */ 
/*     */     
/*     */     public int size() {
/* 653 */       return this.builder.getCount();
/*     */     }
/*     */ 
/*     */     
/*     */     public IType get(int index) {
/* 658 */       return this.builder.getMessageOrBuilder(index);
/*     */     }
/*     */     
/*     */     void incrementModCount() {
/* 662 */       this.modCount++;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\RepeatedFieldBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */