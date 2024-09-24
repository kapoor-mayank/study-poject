/*      */ package com.google.protobuf;
/*      */ 
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.WeakHashMap;
/*      */ import java.util.logging.Logger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class Descriptors
/*      */ {
/*   81 */   private static final Logger logger = Logger.getLogger(Descriptors.class.getName());
/*      */   public static final class FileDescriptor extends GenericDescriptor { private DescriptorProtos.FileDescriptorProto proto; private final Descriptor[] messageTypes;
/*      */     private final EnumDescriptor[] enumTypes;
/*      */     private final ServiceDescriptor[] services;
/*      */     private final FieldDescriptor[] extensions;
/*      */     private final FileDescriptor[] dependencies;
/*      */     private final FileDescriptor[] publicDependencies;
/*      */     private final DescriptorPool pool;
/*      */     
/*      */     public DescriptorProtos.FileDescriptorProto toProto() {
/*   91 */       return this.proto;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String getName() {
/*   97 */       return this.proto.getName();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public FileDescriptor getFile() {
/*  103 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String getFullName() {
/*  109 */       return this.proto.getName();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getPackage() {
/*  117 */       return this.proto.getPackage();
/*      */     }
/*      */ 
/*      */     
/*      */     public DescriptorProtos.FileOptions getOptions() {
/*  122 */       return this.proto.getOptions();
/*      */     }
/*      */ 
/*      */     
/*      */     public List<Descriptor> getMessageTypes() {
/*  127 */       return Collections.unmodifiableList(Arrays.asList(this.messageTypes));
/*      */     }
/*      */ 
/*      */     
/*      */     public List<EnumDescriptor> getEnumTypes() {
/*  132 */       return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
/*      */     }
/*      */ 
/*      */     
/*      */     public List<ServiceDescriptor> getServices() {
/*  137 */       return Collections.unmodifiableList(Arrays.asList(this.services));
/*      */     }
/*      */ 
/*      */     
/*      */     public List<FieldDescriptor> getExtensions() {
/*  142 */       return Collections.unmodifiableList(Arrays.asList(this.extensions));
/*      */     }
/*      */ 
/*      */     
/*      */     public List<FileDescriptor> getDependencies() {
/*  147 */       return Collections.unmodifiableList(Arrays.asList(this.dependencies));
/*      */     }
/*      */ 
/*      */     
/*      */     public List<FileDescriptor> getPublicDependencies() {
/*  152 */       return Collections.unmodifiableList(Arrays.asList(this.publicDependencies));
/*      */     }
/*      */     @Deprecated
/*      */     public static interface InternalDescriptorAssigner {
/*      */       ExtensionRegistry assignDescriptors(FileDescriptor param2FileDescriptor); }
/*  157 */     public enum Syntax { UNKNOWN("unknown"),
/*  158 */       PROTO2("proto2"),
/*  159 */       PROTO3("proto3"); private final String name;
/*      */       
/*      */       Syntax(String name) {
/*  162 */         this.name = name;
/*      */       } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Syntax getSyntax() {
/*  170 */       if (Syntax.PROTO3.name.equals(this.proto.getSyntax())) {
/*  171 */         return Syntax.PROTO3;
/*      */       }
/*  173 */       return Syntax.PROTO2;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Descriptor findMessageTypeByName(String name) {
/*  185 */       if (name.indexOf('.') != -1) {
/*  186 */         return null;
/*      */       }
/*  188 */       String packageName = getPackage();
/*  189 */       if (!packageName.isEmpty()) {
/*  190 */         name = packageName + '.' + name;
/*      */       }
/*  192 */       GenericDescriptor result = this.pool.findSymbol(name);
/*  193 */       if (result != null && result instanceof Descriptor && result.getFile() == this) {
/*  194 */         return (Descriptor)result;
/*      */       }
/*  196 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public EnumDescriptor findEnumTypeByName(String name) {
/*  209 */       if (name.indexOf('.') != -1) {
/*  210 */         return null;
/*      */       }
/*  212 */       String packageName = getPackage();
/*  213 */       if (!packageName.isEmpty()) {
/*  214 */         name = packageName + '.' + name;
/*      */       }
/*  216 */       GenericDescriptor result = this.pool.findSymbol(name);
/*  217 */       if (result != null && result instanceof EnumDescriptor && result.getFile() == this) {
/*  218 */         return (EnumDescriptor)result;
/*      */       }
/*  220 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ServiceDescriptor findServiceByName(String name) {
/*  233 */       if (name.indexOf('.') != -1) {
/*  234 */         return null;
/*      */       }
/*  236 */       String packageName = getPackage();
/*  237 */       if (!packageName.isEmpty()) {
/*  238 */         name = packageName + '.' + name;
/*      */       }
/*  240 */       GenericDescriptor result = this.pool.findSymbol(name);
/*  241 */       if (result != null && result instanceof ServiceDescriptor && result.getFile() == this) {
/*  242 */         return (ServiceDescriptor)result;
/*      */       }
/*  244 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public FieldDescriptor findExtensionByName(String name) {
/*  255 */       if (name.indexOf('.') != -1) {
/*  256 */         return null;
/*      */       }
/*  258 */       String packageName = getPackage();
/*  259 */       if (!packageName.isEmpty()) {
/*  260 */         name = packageName + '.' + name;
/*      */       }
/*  262 */       GenericDescriptor result = this.pool.findSymbol(name);
/*  263 */       if (result != null && result instanceof FieldDescriptor && result.getFile() == this) {
/*  264 */         return (FieldDescriptor)result;
/*      */       }
/*  266 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto proto, FileDescriptor[] dependencies) throws DescriptorValidationException {
/*  282 */       return buildFrom(proto, dependencies, false);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static FileDescriptor buildFrom(DescriptorProtos.FileDescriptorProto proto, FileDescriptor[] dependencies, boolean allowUnknownDependencies) throws DescriptorValidationException {
/*  310 */       DescriptorPool pool = new DescriptorPool(dependencies, allowUnknownDependencies);
/*  311 */       FileDescriptor result = new FileDescriptor(proto, dependencies, pool, allowUnknownDependencies);
/*      */       
/*  313 */       result.crossLink();
/*  314 */       return result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static byte[] latin1Cat(String[] strings) {
/*  327 */       if (strings.length == 1) {
/*  328 */         return strings[0].getBytes(Internal.ISO_8859_1);
/*      */       }
/*  330 */       StringBuilder descriptorData = new StringBuilder();
/*  331 */       for (String part : strings) {
/*  332 */         descriptorData.append(part);
/*      */       }
/*  334 */       return descriptorData.toString().getBytes(Internal.ISO_8859_1);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static FileDescriptor[] findDescriptors(Class<?> descriptorOuterClass, String[] dependencyClassNames, String[] dependencyFileNames) {
/*  341 */       List<FileDescriptor> descriptors = new ArrayList<>();
/*  342 */       for (int i = 0; i < dependencyClassNames.length; i++) {
/*      */         try {
/*  344 */           Class<?> clazz = descriptorOuterClass.getClassLoader().loadClass(dependencyClassNames[i]);
/*  345 */           descriptors.add((FileDescriptor)clazz.getField("descriptor").get(null));
/*  346 */         } catch (Exception e) {
/*      */ 
/*      */           
/*  349 */           Descriptors.logger.warning("Descriptors for \"" + dependencyFileNames[i] + "\" can not be found.");
/*      */         } 
/*      */       } 
/*  352 */       return descriptors.<FileDescriptor>toArray(new FileDescriptor[0]);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @Deprecated
/*      */     public static void internalBuildGeneratedFileFrom(String[] descriptorDataParts, FileDescriptor[] dependencies, InternalDescriptorAssigner descriptorAssigner) {
/*      */       DescriptorProtos.FileDescriptorProto proto;
/*      */       FileDescriptor result;
/*  364 */       byte[] descriptorBytes = latin1Cat(descriptorDataParts);
/*      */ 
/*      */       
/*      */       try {
/*  368 */         proto = DescriptorProtos.FileDescriptorProto.parseFrom(descriptorBytes);
/*  369 */       } catch (InvalidProtocolBufferException e) {
/*  370 */         throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  378 */         result = buildFrom(proto, dependencies, true);
/*  379 */       } catch (DescriptorValidationException e) {
/*  380 */         throw new IllegalArgumentException("Invalid embedded descriptor for \"" + proto
/*  381 */             .getName() + "\".", e);
/*      */       } 
/*      */       
/*  384 */       ExtensionRegistry registry = descriptorAssigner.assignDescriptors(result);
/*      */       
/*  386 */       if (registry != null) {
/*      */         
/*      */         try {
/*  389 */           proto = DescriptorProtos.FileDescriptorProto.parseFrom(descriptorBytes, registry);
/*  390 */         } catch (InvalidProtocolBufferException e) {
/*  391 */           throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
/*      */         } 
/*      */ 
/*      */         
/*  395 */         result.setProto(proto);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static FileDescriptor internalBuildGeneratedFileFrom(String[] descriptorDataParts, FileDescriptor[] dependencies) {
/*      */       DescriptorProtos.FileDescriptorProto proto;
/*  406 */       byte[] descriptorBytes = latin1Cat(descriptorDataParts);
/*      */ 
/*      */       
/*      */       try {
/*  410 */         proto = DescriptorProtos.FileDescriptorProto.parseFrom(descriptorBytes);
/*  411 */       } catch (InvalidProtocolBufferException e) {
/*  412 */         throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  419 */         return buildFrom(proto, dependencies, true);
/*  420 */       } catch (DescriptorValidationException e) {
/*  421 */         throw new IllegalArgumentException("Invalid embedded descriptor for \"" + proto
/*  422 */             .getName() + "\".", e);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @Deprecated
/*      */     public static void internalBuildGeneratedFileFrom(String[] descriptorDataParts, Class<?> descriptorOuterClass, String[] dependencyClassNames, String[] dependencyFileNames, InternalDescriptorAssigner descriptorAssigner) {
/*  437 */       FileDescriptor[] dependencies = findDescriptors(descriptorOuterClass, dependencyClassNames, dependencyFileNames);
/*      */       
/*  439 */       internalBuildGeneratedFileFrom(descriptorDataParts, dependencies, descriptorAssigner);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static FileDescriptor internalBuildGeneratedFileFrom(String[] descriptorDataParts, Class<?> descriptorOuterClass, String[] dependencyClassNames, String[] dependencyFileNames) {
/*  452 */       FileDescriptor[] dependencies = findDescriptors(descriptorOuterClass, dependencyClassNames, dependencyFileNames);
/*      */       
/*  454 */       return internalBuildGeneratedFileFrom(descriptorDataParts, dependencies);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static void internalUpdateFileDescriptor(FileDescriptor descriptor, ExtensionRegistry registry) {
/*      */       DescriptorProtos.FileDescriptorProto proto;
/*  464 */       ByteString bytes = descriptor.proto.toByteString();
/*      */       
/*      */       try {
/*  467 */         proto = DescriptorProtos.FileDescriptorProto.parseFrom(bytes, registry);
/*  468 */       } catch (InvalidProtocolBufferException e) {
/*  469 */         throw new IllegalArgumentException("Failed to parse protocol buffer descriptor for generated code.", e);
/*      */       } 
/*      */       
/*  472 */       descriptor.setProto(proto);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private FileDescriptor(DescriptorProtos.FileDescriptorProto proto, FileDescriptor[] dependencies, DescriptorPool pool, boolean allowUnknownDependencies) throws DescriptorValidationException {
/*  507 */       this.pool = pool;
/*  508 */       this.proto = proto;
/*  509 */       this.dependencies = (FileDescriptor[])dependencies.clone();
/*  510 */       HashMap<String, FileDescriptor> nameToFileMap = new HashMap<>();
/*  511 */       for (FileDescriptor file : dependencies) {
/*  512 */         nameToFileMap.put(file.getName(), file);
/*      */       }
/*  514 */       List<FileDescriptor> publicDependencies = new ArrayList<>(); int i;
/*  515 */       for (i = 0; i < proto.getPublicDependencyCount(); i++) {
/*  516 */         int index = proto.getPublicDependency(i);
/*  517 */         if (index < 0 || index >= proto.getDependencyCount()) {
/*  518 */           throw new DescriptorValidationException(this, "Invalid public dependency index.");
/*      */         }
/*  520 */         String name = proto.getDependency(index);
/*  521 */         FileDescriptor file = nameToFileMap.get(name);
/*  522 */         if (file == null) {
/*  523 */           if (!allowUnknownDependencies) {
/*  524 */             throw new DescriptorValidationException(this, "Invalid public dependency: " + name);
/*      */           }
/*      */         } else {
/*      */           
/*  528 */           publicDependencies.add(file);
/*      */         } 
/*      */       } 
/*  531 */       this.publicDependencies = new FileDescriptor[publicDependencies.size()];
/*  532 */       publicDependencies.toArray(this.publicDependencies);
/*      */       
/*  534 */       pool.addPackage(getPackage(), this);
/*      */       
/*  536 */       this.messageTypes = new Descriptor[proto.getMessageTypeCount()];
/*  537 */       for (i = 0; i < proto.getMessageTypeCount(); i++) {
/*  538 */         this.messageTypes[i] = new Descriptor(proto.getMessageType(i), this, null, i);
/*      */       }
/*      */       
/*  541 */       this.enumTypes = new EnumDescriptor[proto.getEnumTypeCount()];
/*  542 */       for (i = 0; i < proto.getEnumTypeCount(); i++) {
/*  543 */         this.enumTypes[i] = new EnumDescriptor(proto.getEnumType(i), this, null, i);
/*      */       }
/*      */       
/*  546 */       this.services = new ServiceDescriptor[proto.getServiceCount()];
/*  547 */       for (i = 0; i < proto.getServiceCount(); i++) {
/*  548 */         this.services[i] = new ServiceDescriptor(proto.getService(i), this, i);
/*      */       }
/*      */       
/*  551 */       this.extensions = new FieldDescriptor[proto.getExtensionCount()];
/*  552 */       for (i = 0; i < proto.getExtensionCount(); i++) {
/*  553 */         this.extensions[i] = new FieldDescriptor(proto.getExtension(i), this, null, i, true);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     FileDescriptor(String packageName, Descriptor message) throws DescriptorValidationException {
/*  559 */       this.pool = new DescriptorPool(new FileDescriptor[0], true);
/*  560 */       this
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  565 */         .proto = DescriptorProtos.FileDescriptorProto.newBuilder().setName(message.getFullName() + ".placeholder.proto").setPackage(packageName).addMessageType(message.toProto()).build();
/*  566 */       this.dependencies = new FileDescriptor[0];
/*  567 */       this.publicDependencies = new FileDescriptor[0];
/*      */       
/*  569 */       this.messageTypes = new Descriptor[] { message };
/*  570 */       this.enumTypes = new EnumDescriptor[0];
/*  571 */       this.services = new ServiceDescriptor[0];
/*  572 */       this.extensions = new FieldDescriptor[0];
/*      */       
/*  574 */       this.pool.addPackage(packageName, this);
/*  575 */       this.pool.addSymbol(message);
/*      */     }
/*      */ 
/*      */     
/*      */     private void crossLink() throws DescriptorValidationException {
/*  580 */       for (Descriptor messageType : this.messageTypes) {
/*  581 */         messageType.crossLink();
/*      */       }
/*      */       
/*  584 */       for (ServiceDescriptor service : this.services) {
/*  585 */         service.crossLink();
/*      */       }
/*      */       
/*  588 */       for (FieldDescriptor extension : this.extensions) {
/*  589 */         extension.crossLink();
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void setProto(DescriptorProtos.FileDescriptorProto proto) {
/*  602 */       this.proto = proto;
/*      */       int i;
/*  604 */       for (i = 0; i < this.messageTypes.length; i++) {
/*  605 */         this.messageTypes[i].setProto(proto.getMessageType(i));
/*      */       }
/*      */       
/*  608 */       for (i = 0; i < this.enumTypes.length; i++) {
/*  609 */         this.enumTypes[i].setProto(proto.getEnumType(i));
/*      */       }
/*      */       
/*  612 */       for (i = 0; i < this.services.length; i++) {
/*  613 */         this.services[i].setProto(proto.getService(i));
/*      */       }
/*      */       
/*  616 */       for (i = 0; i < this.extensions.length; i++) {
/*  617 */         this.extensions[i].setProto(proto.getExtension(i));
/*      */       }
/*      */     }
/*      */     
/*      */     boolean supportsUnknownEnumValue() {
/*  622 */       return (getSyntax() == Syntax.PROTO3);
/*      */     } }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final class Descriptor
/*      */     extends GenericDescriptor
/*      */   {
/*      */     private final int index;
/*      */     
/*      */     private DescriptorProtos.DescriptorProto proto;
/*      */     
/*      */     private final String fullName;
/*      */     
/*      */     private final FileDescriptor file;
/*      */     
/*      */     private final Descriptor containingType;
/*      */     private final Descriptor[] nestedTypes;
/*      */     private final EnumDescriptor[] enumTypes;
/*      */     private final FieldDescriptor[] fields;
/*      */     private final FieldDescriptor[] extensions;
/*      */     private final OneofDescriptor[] oneofs;
/*      */     private final int realOneofCount;
/*      */     
/*      */     public int getIndex() {
/*  647 */       return this.index;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public DescriptorProtos.DescriptorProto toProto() {
/*  653 */       return this.proto;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String getName() {
/*  659 */       return this.proto.getName();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getFullName() {
/*  676 */       return this.fullName;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public FileDescriptor getFile() {
/*  682 */       return this.file;
/*      */     }
/*      */ 
/*      */     
/*      */     public Descriptor getContainingType() {
/*  687 */       return this.containingType;
/*      */     }
/*      */ 
/*      */     
/*      */     public DescriptorProtos.MessageOptions getOptions() {
/*  692 */       return this.proto.getOptions();
/*      */     }
/*      */ 
/*      */     
/*      */     public List<FieldDescriptor> getFields() {
/*  697 */       return Collections.unmodifiableList(Arrays.asList(this.fields));
/*      */     }
/*      */ 
/*      */     
/*      */     public List<OneofDescriptor> getOneofs() {
/*  702 */       return Collections.unmodifiableList(Arrays.asList(this.oneofs));
/*      */     }
/*      */ 
/*      */     
/*      */     public List<OneofDescriptor> getRealOneofs() {
/*  707 */       return Collections.unmodifiableList(Arrays.<OneofDescriptor>asList(this.oneofs).subList(0, this.realOneofCount));
/*      */     }
/*      */ 
/*      */     
/*      */     public List<FieldDescriptor> getExtensions() {
/*  712 */       return Collections.unmodifiableList(Arrays.asList(this.extensions));
/*      */     }
/*      */ 
/*      */     
/*      */     public List<Descriptor> getNestedTypes() {
/*  717 */       return Collections.unmodifiableList(Arrays.asList(this.nestedTypes));
/*      */     }
/*      */ 
/*      */     
/*      */     public List<EnumDescriptor> getEnumTypes() {
/*  722 */       return Collections.unmodifiableList(Arrays.asList(this.enumTypes));
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isExtensionNumber(int number) {
/*  727 */       for (DescriptorProtos.DescriptorProto.ExtensionRange range : this.proto.getExtensionRangeList()) {
/*  728 */         if (range.getStart() <= number && number < range.getEnd()) {
/*  729 */           return true;
/*      */         }
/*      */       } 
/*  732 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isReservedNumber(int number) {
/*  737 */       for (DescriptorProtos.DescriptorProto.ReservedRange range : this.proto.getReservedRangeList()) {
/*  738 */         if (range.getStart() <= number && number < range.getEnd()) {
/*  739 */           return true;
/*      */         }
/*      */       } 
/*  742 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isReservedName(String name) {
/*  747 */       Internal.checkNotNull(name);
/*  748 */       for (String reservedName : this.proto.getReservedNameList()) {
/*  749 */         if (reservedName.equals(name)) {
/*  750 */           return true;
/*      */         }
/*      */       } 
/*  753 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isExtendable() {
/*  761 */       return (this.proto.getExtensionRangeList().size() != 0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public FieldDescriptor findFieldByName(String name) {
/*  774 */       GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
/*  775 */       if (result != null && result instanceof FieldDescriptor) {
/*  776 */         return (FieldDescriptor)result;
/*      */       }
/*  778 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public FieldDescriptor findFieldByNumber(int number) {
/*  789 */       return (FieldDescriptor)this.file.pool.fieldsByNumber.get(new DescriptorPool.DescriptorIntPair(this, number));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Descriptor findNestedTypeByName(String name) {
/*  799 */       GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
/*  800 */       if (result != null && result instanceof Descriptor) {
/*  801 */         return (Descriptor)result;
/*      */       }
/*  803 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public EnumDescriptor findEnumTypeByName(String name) {
/*  814 */       GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
/*  815 */       if (result != null && result instanceof EnumDescriptor) {
/*  816 */         return (EnumDescriptor)result;
/*      */       }
/*  818 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     Descriptor(String fullname) throws DescriptorValidationException {
/*  836 */       String name = fullname;
/*  837 */       String packageName = "";
/*  838 */       int pos = fullname.lastIndexOf('.');
/*  839 */       if (pos != -1) {
/*  840 */         name = fullname.substring(pos + 1);
/*  841 */         packageName = fullname.substring(0, pos);
/*      */       } 
/*  843 */       this.index = 0;
/*  844 */       this
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  849 */         .proto = DescriptorProtos.DescriptorProto.newBuilder().setName(name).addExtensionRange(DescriptorProtos.DescriptorProto.ExtensionRange.newBuilder().setStart(1).setEnd(536870912).build()).build();
/*  850 */       this.fullName = fullname;
/*  851 */       this.containingType = null;
/*      */       
/*  853 */       this.nestedTypes = new Descriptor[0];
/*  854 */       this.enumTypes = new EnumDescriptor[0];
/*  855 */       this.fields = new FieldDescriptor[0];
/*  856 */       this.extensions = new FieldDescriptor[0];
/*  857 */       this.oneofs = new OneofDescriptor[0];
/*  858 */       this.realOneofCount = 0;
/*      */ 
/*      */       
/*  861 */       this.file = new FileDescriptor(packageName, this);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private Descriptor(DescriptorProtos.DescriptorProto proto, FileDescriptor file, Descriptor parent, int index) throws DescriptorValidationException {
/*  870 */       this.index = index;
/*  871 */       this.proto = proto;
/*  872 */       this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
/*  873 */       this.file = file;
/*  874 */       this.containingType = parent;
/*      */       
/*  876 */       this.oneofs = new OneofDescriptor[proto.getOneofDeclCount()]; int i;
/*  877 */       for (i = 0; i < proto.getOneofDeclCount(); i++) {
/*  878 */         this.oneofs[i] = new OneofDescriptor(proto.getOneofDecl(i), file, this, i);
/*      */       }
/*      */       
/*  881 */       this.nestedTypes = new Descriptor[proto.getNestedTypeCount()];
/*  882 */       for (i = 0; i < proto.getNestedTypeCount(); i++) {
/*  883 */         this.nestedTypes[i] = new Descriptor(proto.getNestedType(i), file, this, i);
/*      */       }
/*      */       
/*  886 */       this.enumTypes = new EnumDescriptor[proto.getEnumTypeCount()];
/*  887 */       for (i = 0; i < proto.getEnumTypeCount(); i++) {
/*  888 */         this.enumTypes[i] = new EnumDescriptor(proto.getEnumType(i), file, this, i);
/*      */       }
/*      */       
/*  891 */       this.fields = new FieldDescriptor[proto.getFieldCount()];
/*  892 */       for (i = 0; i < proto.getFieldCount(); i++) {
/*  893 */         this.fields[i] = new FieldDescriptor(proto.getField(i), file, this, i, false);
/*      */       }
/*      */       
/*  896 */       this.extensions = new FieldDescriptor[proto.getExtensionCount()];
/*  897 */       for (i = 0; i < proto.getExtensionCount(); i++) {
/*  898 */         this.extensions[i] = new FieldDescriptor(proto.getExtension(i), file, this, i, true);
/*      */       }
/*      */       
/*  901 */       for (i = 0; i < proto.getOneofDeclCount(); i++) {
/*  902 */         (this.oneofs[i]).fields = new FieldDescriptor[this.oneofs[i].getFieldCount()];
/*  903 */         (this.oneofs[i]).fieldCount = 0;
/*      */       } 
/*  905 */       for (i = 0; i < proto.getFieldCount(); i++) {
/*  906 */         OneofDescriptor oneofDescriptor = this.fields[i].getContainingOneof();
/*  907 */         if (oneofDescriptor != null) {
/*  908 */           oneofDescriptor.fields[oneofDescriptor.fieldCount++] = this.fields[i];
/*      */         }
/*      */       } 
/*      */       
/*  912 */       int syntheticOneofCount = 0;
/*  913 */       for (OneofDescriptor oneof : this.oneofs) {
/*  914 */         if (oneof.isSynthetic()) {
/*  915 */           syntheticOneofCount++;
/*      */         }
/*  917 */         else if (syntheticOneofCount > 0) {
/*  918 */           throw new DescriptorValidationException(this, "Synthetic oneofs must come last.");
/*      */         } 
/*      */       } 
/*      */       
/*  922 */       this.realOneofCount = this.oneofs.length - syntheticOneofCount;
/*      */       
/*  924 */       file.pool.addSymbol(this);
/*      */     }
/*      */ 
/*      */     
/*      */     private void crossLink() throws DescriptorValidationException {
/*  929 */       for (Descriptor nestedType : this.nestedTypes) {
/*  930 */         nestedType.crossLink();
/*      */       }
/*      */       
/*  933 */       for (FieldDescriptor field : this.fields) {
/*  934 */         field.crossLink();
/*      */       }
/*      */       
/*  937 */       for (FieldDescriptor extension : this.extensions) {
/*  938 */         extension.crossLink();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     private void setProto(DescriptorProtos.DescriptorProto proto) {
/*  944 */       this.proto = proto;
/*      */       int i;
/*  946 */       for (i = 0; i < this.nestedTypes.length; i++) {
/*  947 */         this.nestedTypes[i].setProto(proto.getNestedType(i));
/*      */       }
/*      */       
/*  950 */       for (i = 0; i < this.oneofs.length; i++) {
/*  951 */         this.oneofs[i].setProto(proto.getOneofDecl(i));
/*      */       }
/*      */       
/*  954 */       for (i = 0; i < this.enumTypes.length; i++) {
/*  955 */         this.enumTypes[i].setProto(proto.getEnumType(i));
/*      */       }
/*      */       
/*  958 */       for (i = 0; i < this.fields.length; i++) {
/*  959 */         this.fields[i].setProto(proto.getField(i));
/*      */       }
/*      */       
/*  962 */       for (i = 0; i < this.extensions.length; i++) {
/*  963 */         this.extensions[i].setProto(proto.getExtension(i));
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final class FieldDescriptor
/*      */     extends GenericDescriptor
/*      */     implements Comparable<FieldDescriptor>, FieldSet.FieldDescriptorLite<FieldDescriptor>
/*      */   {
/*      */     public int getIndex() {
/*  979 */       return this.index;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public DescriptorProtos.FieldDescriptorProto toProto() {
/*  985 */       return this.proto;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String getName() {
/*  991 */       return this.proto.getName();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getNumber() {
/*  997 */       return this.proto.getNumber();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getFullName() {
/* 1007 */       return this.fullName;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getJsonName() {
/* 1012 */       return this.jsonName;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public JavaType getJavaType() {
/* 1020 */       return this.type.getJavaType();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WireFormat.JavaType getLiteJavaType() {
/* 1026 */       return getLiteType().getJavaType();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public FileDescriptor getFile() {
/* 1032 */       return this.file;
/*      */     }
/*      */ 
/*      */     
/*      */     public Type getType() {
/* 1037 */       return this.type;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WireFormat.FieldType getLiteType() {
/* 1043 */       return table[this.type.ordinal()];
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean needsUtf8Check() {
/* 1048 */       if (this.type != Type.STRING) {
/* 1049 */         return false;
/*      */       }
/* 1051 */       if (getContainingType().getOptions().getMapEntry())
/*      */       {
/* 1053 */         return true;
/*      */       }
/* 1055 */       if (getFile().getSyntax() == FileDescriptor.Syntax.PROTO3) {
/* 1056 */         return true;
/*      */       }
/* 1058 */       return getFile().getOptions().getJavaStringCheckUtf8();
/*      */     }
/*      */     
/*      */     public boolean isMapField() {
/* 1062 */       return (getType() == Type.MESSAGE && 
/* 1063 */         isRepeated() && 
/* 1064 */         getMessageType().getOptions().getMapEntry());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1070 */     private static final WireFormat.FieldType[] table = WireFormat.FieldType.values(); private final int index; private DescriptorProtos.FieldDescriptorProto proto; private final String fullName; private final String jsonName; private final FileDescriptor file;
/*      */     private final Descriptor extensionScope;
/*      */     
/*      */     public boolean isRequired() {
/* 1074 */       return (this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REQUIRED);
/*      */     }
/*      */     private final boolean isProto3Optional; private Type type; private Descriptor containingType; private Descriptor messageType; private OneofDescriptor containingOneof; private EnumDescriptor enumType; private Object defaultValue;
/*      */     
/*      */     public boolean isOptional() {
/* 1079 */       return (this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_OPTIONAL);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isRepeated() {
/* 1085 */       return (this.proto.getLabel() == DescriptorProtos.FieldDescriptorProto.Label.LABEL_REPEATED);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isPacked() {
/* 1094 */       if (!isPackable()) {
/* 1095 */         return false;
/*      */       }
/* 1097 */       if (getFile().getSyntax() == FileDescriptor.Syntax.PROTO2) {
/* 1098 */         return getOptions().getPacked();
/*      */       }
/* 1100 */       return (!getOptions().hasPacked() || getOptions().getPacked());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean isPackable() {
/* 1106 */       return (isRepeated() && getLiteType().isPackable());
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean hasDefaultValue() {
/* 1111 */       return this.proto.hasDefaultValue();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object getDefaultValue() {
/* 1120 */       if (getJavaType() == JavaType.MESSAGE) {
/* 1121 */         throw new UnsupportedOperationException("FieldDescriptor.getDefaultValue() called on an embedded message field.");
/*      */       }
/*      */       
/* 1124 */       return this.defaultValue;
/*      */     }
/*      */ 
/*      */     
/*      */     public DescriptorProtos.FieldOptions getOptions() {
/* 1129 */       return this.proto.getOptions();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isExtension() {
/* 1134 */       return this.proto.hasExtendee();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Descriptor getContainingType() {
/* 1142 */       return this.containingType;
/*      */     }
/*      */ 
/*      */     
/*      */     public OneofDescriptor getContainingOneof() {
/* 1147 */       return this.containingOneof;
/*      */     }
/*      */ 
/*      */     
/*      */     public OneofDescriptor getRealContainingOneof() {
/* 1152 */       return (this.containingOneof != null && !this.containingOneof.isSynthetic()) ? this.containingOneof : null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean hasOptionalKeyword() {
/* 1160 */       return (this.isProto3Optional || (this.file
/* 1161 */         .getSyntax() == FileDescriptor.Syntax.PROTO2 && isOptional() && getContainingOneof() == null));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     boolean hasPresence() {
/* 1175 */       if (isRepeated()) {
/* 1176 */         return false;
/*      */       }
/* 1178 */       return (getType() == Type.MESSAGE || 
/* 1179 */         getType() == Type.GROUP || 
/* 1180 */         getContainingOneof() != null || this.file
/* 1181 */         .getSyntax() == FileDescriptor.Syntax.PROTO2);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Descriptor getExtensionScope() {
/* 1206 */       if (!isExtension()) {
/* 1207 */         throw new UnsupportedOperationException(
/* 1208 */             String.format("This field is not an extension. (%s)", new Object[] { this.fullName }));
/*      */       }
/* 1210 */       return this.extensionScope;
/*      */     }
/*      */ 
/*      */     
/*      */     public Descriptor getMessageType() {
/* 1215 */       if (getJavaType() != JavaType.MESSAGE) {
/* 1216 */         throw new UnsupportedOperationException(
/* 1217 */             String.format("This field is not of message type. (%s)", new Object[] { this.fullName }));
/*      */       }
/* 1219 */       return this.messageType;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public EnumDescriptor getEnumType() {
/* 1225 */       if (getJavaType() != JavaType.ENUM) {
/* 1226 */         throw new UnsupportedOperationException(
/* 1227 */             String.format("This field is not of enum type. (%s)", new Object[] { this.fullName }));
/*      */       }
/* 1229 */       return this.enumType;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int compareTo(FieldDescriptor other) {
/* 1242 */       if (other.containingType != this.containingType) {
/* 1243 */         throw new IllegalArgumentException("FieldDescriptors can only be compared to other FieldDescriptors for fields of the same message type.");
/*      */       }
/*      */ 
/*      */       
/* 1247 */       return getNumber() - other.getNumber();
/*      */     }
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1252 */       return getFullName();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public enum Type
/*      */     {
/* 1273 */       DOUBLE((String) JavaType.DOUBLE),
/* 1274 */       FLOAT((String) JavaType.FLOAT),
/* 1275 */       INT64((String) JavaType.LONG),
/* 1276 */       UINT64((String) JavaType.LONG),
/* 1277 */       INT32((String) JavaType.INT),
/* 1278 */       FIXED64((String) JavaType.LONG),
/* 1279 */       FIXED32((String) JavaType.INT),
/* 1280 */       BOOL((String) JavaType.BOOLEAN),
/* 1281 */       STRING((String) JavaType.STRING),
/* 1282 */       GROUP((String) JavaType.MESSAGE),
/* 1283 */       MESSAGE((String) JavaType.MESSAGE),
/* 1284 */       BYTES((String) JavaType.BYTE_STRING),
/* 1285 */       UINT32((String) JavaType.INT),
/* 1286 */       ENUM((String) JavaType.ENUM),
/* 1287 */       SFIXED32((String) JavaType.INT),
/* 1288 */       SFIXED64((String) JavaType.LONG),
/* 1289 */       SINT32((String) JavaType.INT),
/* 1290 */       SINT64((String) JavaType.LONG); private JavaType javaType;
/*      */       
/*      */       Type(JavaType javaType) {
/* 1293 */         this.javaType = javaType;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public DescriptorProtos.FieldDescriptorProto.Type toProto() {
/* 1299 */         return DescriptorProtos.FieldDescriptorProto.Type.forNumber(ordinal() + 1);
/*      */       }
/*      */       
/*      */       public JavaType getJavaType() {
/* 1303 */         return this.javaType;
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     static {
/* 1313 */       if ((Type.values()).length != (DescriptorProtos.FieldDescriptorProto.Type.values()).length) {
/* 1314 */         throw new RuntimeException("descriptor.proto has a new declared type but Descriptors.java wasn't updated.");
/*      */       }
/*      */     }
/*      */     
/*      */     public enum JavaType
/*      */     {
/* 1320 */       INT((String)Integer.valueOf(0)),
/* 1321 */       LONG((String)Long.valueOf(0L)),
/* 1322 */       FLOAT((String)Float.valueOf(0.0F)),
/* 1323 */       DOUBLE((String)Double.valueOf(0.0D)),
/* 1324 */       BOOLEAN((String)Boolean.valueOf(false)),
/* 1325 */       STRING(""),
/* 1326 */       BYTE_STRING((String)ByteString.EMPTY),
/* 1327 */       ENUM(null),
/* 1328 */       MESSAGE(null); private final Object defaultDefault;
/*      */       
/*      */       JavaType(Object defaultDefault) {
/* 1331 */         this.defaultDefault = defaultDefault;
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static String fieldNameToJsonName(String name) {
/* 1344 */       int length = name.length();
/* 1345 */       StringBuilder result = new StringBuilder(length);
/* 1346 */       boolean isNextUpperCase = false;
/* 1347 */       for (int i = 0; i < length; i++) {
/* 1348 */         char ch = name.charAt(i);
/* 1349 */         if (ch == '_') {
/* 1350 */           isNextUpperCase = true;
/* 1351 */         } else if (isNextUpperCase) {
/*      */ 
/*      */           
/* 1354 */           if ('a' <= ch && ch <= 'z') {
/* 1355 */             ch = (char)(ch - 97 + 65);
/*      */           }
/* 1357 */           result.append(ch);
/* 1358 */           isNextUpperCase = false;
/*      */         } else {
/* 1360 */           result.append(ch);
/*      */         } 
/*      */       } 
/* 1363 */       return result.toString();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private FieldDescriptor(DescriptorProtos.FieldDescriptorProto proto, FileDescriptor file, Descriptor parent, int index, boolean isExtension) throws DescriptorValidationException {
/* 1373 */       this.index = index;
/* 1374 */       this.proto = proto;
/* 1375 */       this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
/* 1376 */       this.file = file;
/* 1377 */       if (proto.hasJsonName()) {
/* 1378 */         this.jsonName = proto.getJsonName();
/*      */       } else {
/* 1380 */         this.jsonName = fieldNameToJsonName(proto.getName());
/*      */       } 
/*      */       
/* 1383 */       if (proto.hasType()) {
/* 1384 */         this.type = Type.valueOf(proto.getType());
/*      */       }
/*      */       
/* 1387 */       this.isProto3Optional = proto.getProto3Optional();
/*      */       
/* 1389 */       if (getNumber() <= 0) {
/* 1390 */         throw new DescriptorValidationException(this, "Field numbers must be positive integers.");
/*      */       }
/*      */       
/* 1393 */       if (isExtension) {
/* 1394 */         if (!proto.hasExtendee()) {
/* 1395 */           throw new DescriptorValidationException(this, "FieldDescriptorProto.extendee not set for extension field.");
/*      */         }
/*      */         
/* 1398 */         this.containingType = null;
/* 1399 */         if (parent != null) {
/* 1400 */           this.extensionScope = parent;
/*      */         } else {
/* 1402 */           this.extensionScope = null;
/*      */         } 
/*      */         
/* 1405 */         if (proto.hasOneofIndex()) {
/* 1406 */           throw new DescriptorValidationException(this, "FieldDescriptorProto.oneof_index set for extension field.");
/*      */         }
/*      */         
/* 1409 */         this.containingOneof = null;
/*      */       } else {
/* 1411 */         if (proto.hasExtendee()) {
/* 1412 */           throw new DescriptorValidationException(this, "FieldDescriptorProto.extendee set for non-extension field.");
/*      */         }
/*      */         
/* 1415 */         this.containingType = parent;
/*      */         
/* 1417 */         if (proto.hasOneofIndex()) {
/* 1418 */           if (proto.getOneofIndex() < 0 || proto
/* 1419 */             .getOneofIndex() >= parent.toProto().getOneofDeclCount()) {
/* 1420 */             throw new DescriptorValidationException(this, "FieldDescriptorProto.oneof_index is out of range for type " + parent
/*      */                 
/* 1422 */                 .getName());
/*      */           }
/* 1424 */           this.containingOneof = parent.getOneofs().get(proto.getOneofIndex());
/* 1425 */           this.containingOneof.fieldCount++;
/*      */         } else {
/* 1427 */           this.containingOneof = null;
/*      */         } 
/* 1429 */         this.extensionScope = null;
/*      */       } 
/*      */       
/* 1432 */       file.pool.addSymbol(this);
/*      */     }
/*      */ 
/*      */     
/*      */     private void crossLink() throws DescriptorValidationException {
/* 1437 */       if (this.proto.hasExtendee()) {
/*      */         
/* 1439 */         GenericDescriptor extendee = this.file.pool.lookupSymbol(this.proto
/* 1440 */             .getExtendee(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
/* 1441 */         if (!(extendee instanceof Descriptor)) {
/* 1442 */           throw new DescriptorValidationException(this, '"' + this.proto
/* 1443 */               .getExtendee() + "\" is not a message type.");
/*      */         }
/* 1445 */         this.containingType = (Descriptor)extendee;
/*      */         
/* 1447 */         if (!getContainingType().isExtensionNumber(getNumber())) {
/* 1448 */           throw new DescriptorValidationException(this, '"' +
/*      */ 
/*      */               
/* 1451 */               getContainingType().getFullName() + "\" does not declare " + 
/*      */               
/* 1453 */               getNumber() + " as an extension number.");
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1458 */       if (this.proto.hasTypeName()) {
/*      */         
/* 1460 */         GenericDescriptor typeDescriptor = this.file.pool.lookupSymbol(this.proto
/* 1461 */             .getTypeName(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
/*      */         
/* 1463 */         if (!this.proto.hasType())
/*      */         {
/* 1465 */           if (typeDescriptor instanceof Descriptor) {
/* 1466 */             this.type = Type.MESSAGE;
/* 1467 */           } else if (typeDescriptor instanceof EnumDescriptor) {
/* 1468 */             this.type = Type.ENUM;
/*      */           } else {
/* 1470 */             throw new DescriptorValidationException(this, '"' + this.proto
/* 1471 */                 .getTypeName() + "\" is not a type.");
/*      */           } 
/*      */         }
/*      */         
/* 1475 */         if (getJavaType() == JavaType.MESSAGE) {
/* 1476 */           if (!(typeDescriptor instanceof Descriptor)) {
/* 1477 */             throw new DescriptorValidationException(this, '"' + this.proto
/* 1478 */                 .getTypeName() + "\" is not a message type.");
/*      */           }
/* 1480 */           this.messageType = (Descriptor)typeDescriptor;
/*      */           
/* 1482 */           if (this.proto.hasDefaultValue()) {
/* 1483 */             throw new DescriptorValidationException(this, "Messages can't have default values.");
/*      */           }
/* 1485 */         } else if (getJavaType() == JavaType.ENUM) {
/* 1486 */           if (!(typeDescriptor instanceof EnumDescriptor)) {
/* 1487 */             throw new DescriptorValidationException(this, '"' + this.proto
/* 1488 */                 .getTypeName() + "\" is not an enum type.");
/*      */           }
/* 1490 */           this.enumType = (EnumDescriptor)typeDescriptor;
/*      */         } else {
/* 1492 */           throw new DescriptorValidationException(this, "Field with primitive type has type_name.");
/*      */         }
/*      */       
/* 1495 */       } else if (getJavaType() == JavaType.MESSAGE || getJavaType() == JavaType.ENUM) {
/* 1496 */         throw new DescriptorValidationException(this, "Field with message or enum type missing type_name.");
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1502 */       if (this.proto.getOptions().getPacked() && !isPackable()) {
/* 1503 */         throw new DescriptorValidationException(this, "[packed = true] can only be specified for repeated primitive fields.");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1509 */       if (this.proto.hasDefaultValue()) {
/* 1510 */         if (isRepeated()) {
/* 1511 */           throw new DescriptorValidationException(this, "Repeated fields cannot have default values.");
/*      */         }
/*      */ 
/*      */         
/*      */         try {
/* 1516 */           switch (getType()) {
/*      */             case ENUM:
/*      */             case MESSAGE:
/*      */             case null:
/* 1520 */               this.defaultValue = Integer.valueOf(TextFormat.parseInt32(this.proto.getDefaultValue()));
/*      */               break;
/*      */             case null:
/*      */             case null:
/* 1524 */               this.defaultValue = Integer.valueOf(TextFormat.parseUInt32(this.proto.getDefaultValue()));
/*      */               break;
/*      */             case null:
/*      */             case null:
/*      */             case null:
/* 1529 */               this.defaultValue = Long.valueOf(TextFormat.parseInt64(this.proto.getDefaultValue()));
/*      */               break;
/*      */             case null:
/*      */             case null:
/* 1533 */               this.defaultValue = Long.valueOf(TextFormat.parseUInt64(this.proto.getDefaultValue()));
/*      */               break;
/*      */             case null:
/* 1536 */               if (this.proto.getDefaultValue().equals("inf")) {
/* 1537 */                 this.defaultValue = Float.valueOf(Float.POSITIVE_INFINITY); break;
/* 1538 */               }  if (this.proto.getDefaultValue().equals("-inf")) {
/* 1539 */                 this.defaultValue = Float.valueOf(Float.NEGATIVE_INFINITY); break;
/* 1540 */               }  if (this.proto.getDefaultValue().equals("nan")) {
/* 1541 */                 this.defaultValue = Float.valueOf(Float.NaN); break;
/*      */               } 
/* 1543 */               this.defaultValue = Float.valueOf(this.proto.getDefaultValue());
/*      */               break;
/*      */             
/*      */             case null:
/* 1547 */               if (this.proto.getDefaultValue().equals("inf")) {
/* 1548 */                 this.defaultValue = Double.valueOf(Double.POSITIVE_INFINITY); break;
/* 1549 */               }  if (this.proto.getDefaultValue().equals("-inf")) {
/* 1550 */                 this.defaultValue = Double.valueOf(Double.NEGATIVE_INFINITY); break;
/* 1551 */               }  if (this.proto.getDefaultValue().equals("nan")) {
/* 1552 */                 this.defaultValue = Double.valueOf(Double.NaN); break;
/*      */               } 
/* 1554 */               this.defaultValue = Double.valueOf(this.proto.getDefaultValue());
/*      */               break;
/*      */             
/*      */             case null:
/* 1558 */               this.defaultValue = Boolean.valueOf(this.proto.getDefaultValue());
/*      */               break;
/*      */             case null:
/* 1561 */               this.defaultValue = this.proto.getDefaultValue();
/*      */               break;
/*      */             case null:
/*      */               try {
/* 1565 */                 this.defaultValue = TextFormat.unescapeBytes(this.proto.getDefaultValue());
/* 1566 */               } catch (InvalidEscapeSequenceException e) {
/* 1567 */                 throw new DescriptorValidationException(this, "Couldn't parse default value: " + e
/* 1568 */                     .getMessage(), e);
/*      */               } 
/*      */               break;
/*      */             case null:
/* 1572 */               this.defaultValue = this.enumType.findValueByName(this.proto.getDefaultValue());
/* 1573 */               if (this.defaultValue == null) {
/* 1574 */                 throw new DescriptorValidationException(this, "Unknown enum default value: \"" + this.proto
/* 1575 */                     .getDefaultValue() + '"');
/*      */               }
/*      */               break;
/*      */             case null:
/*      */             case null:
/* 1580 */               throw new DescriptorValidationException(this, "Message type had default value.");
/*      */           } 
/* 1582 */         } catch (NumberFormatException e) {
/* 1583 */           throw new DescriptorValidationException(this, "Could not parse default value: \"" + this.proto
/* 1584 */               .getDefaultValue() + '"', e);
/*      */         }
/*      */       
/*      */       }
/* 1588 */       else if (isRepeated()) {
/* 1589 */         this.defaultValue = Collections.emptyList();
/*      */       } else {
/* 1591 */         switch (getJavaType()) {
/*      */ 
/*      */           
/*      */           case ENUM:
/* 1595 */             this.defaultValue = this.enumType.getValues().get(0);
/*      */             break;
/*      */           case MESSAGE:
/* 1598 */             this.defaultValue = null;
/*      */             break;
/*      */           default:
/* 1601 */             this.defaultValue = (getJavaType()).defaultDefault;
/*      */             break;
/*      */         } 
/*      */ 
/*      */       
/*      */       } 
/* 1607 */       if (!isExtension()) {
/* 1608 */         this.file.pool.addFieldByNumber(this);
/*      */       }
/*      */       
/* 1611 */       if (this.containingType != null && this.containingType.getOptions().getMessageSetWireFormat()) {
/* 1612 */         if (isExtension()) {
/* 1613 */           if (!isOptional() || getType() != Type.MESSAGE) {
/* 1614 */             throw new DescriptorValidationException(this, "Extensions of MessageSets must be optional messages.");
/*      */           }
/*      */         } else {
/*      */           
/* 1618 */           throw new DescriptorValidationException(this, "MessageSets cannot have fields, only extensions.");
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private void setProto(DescriptorProtos.FieldDescriptorProto proto) {
/* 1626 */       this.proto = proto;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public MessageLite.Builder internalMergeFrom(MessageLite.Builder to, MessageLite from) {
/* 1634 */       return ((Message.Builder)to).mergeFrom((Message)from);
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class EnumDescriptor
/*      */     extends GenericDescriptor
/*      */     implements Internal.EnumLiteMap<EnumValueDescriptor>
/*      */   {
/*      */     private final int index;
/*      */     private DescriptorProtos.EnumDescriptorProto proto;
/*      */     private final String fullName;
/*      */     private final FileDescriptor file;
/*      */     private final Descriptor containingType;
/*      */     private EnumValueDescriptor[] values;
/*      */     
/*      */     public int getIndex() {
/* 1650 */       return this.index;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public DescriptorProtos.EnumDescriptorProto toProto() {
/* 1656 */       return this.proto;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String getName() {
/* 1662 */       return this.proto.getName();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getFullName() {
/* 1672 */       return this.fullName;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public FileDescriptor getFile() {
/* 1678 */       return this.file;
/*      */     }
/*      */ 
/*      */     
/*      */     public Descriptor getContainingType() {
/* 1683 */       return this.containingType;
/*      */     }
/*      */ 
/*      */     
/*      */     public DescriptorProtos.EnumOptions getOptions() {
/* 1688 */       return this.proto.getOptions();
/*      */     }
/*      */ 
/*      */     
/*      */     public List<EnumValueDescriptor> getValues() {
/* 1693 */       return Collections.unmodifiableList(Arrays.asList(this.values));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public EnumValueDescriptor findValueByName(String name) {
/* 1703 */       GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
/* 1704 */       if (result != null && result instanceof EnumValueDescriptor) {
/* 1705 */         return (EnumValueDescriptor)result;
/*      */       }
/* 1707 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public EnumValueDescriptor findValueByNumber(int number) {
/* 1720 */       return (EnumValueDescriptor)this.file.pool.enumValuesByNumber.get(new DescriptorPool.DescriptorIntPair(this, number));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public EnumValueDescriptor findValueByNumberCreatingIfUnknown(int number) {
/* 1728 */       EnumValueDescriptor result = findValueByNumber(number);
/* 1729 */       if (result != null) {
/* 1730 */         return result;
/*      */       }
/*      */       
/* 1733 */       synchronized (this) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1763 */         Integer key = new Integer(number);
/* 1764 */         WeakReference<EnumValueDescriptor> reference = this.unknownValues.get(key);
/* 1765 */         if (reference != null) {
/* 1766 */           result = reference.get();
/*      */         }
/* 1768 */         if (result == null) {
/* 1769 */           result = new EnumValueDescriptor(this.file, this, key);
/* 1770 */           this.unknownValues.put(key, new WeakReference<>(result));
/*      */         } 
/*      */       } 
/* 1773 */       return result;
/*      */     }
/*      */ 
/*      */     
/*      */     int getUnknownEnumValueDescriptorCount() {
/* 1778 */       return this.unknownValues.size();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1787 */     private final WeakHashMap<Integer, WeakReference<EnumValueDescriptor>> unknownValues = new WeakHashMap<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private EnumDescriptor(DescriptorProtos.EnumDescriptorProto proto, FileDescriptor file, Descriptor parent, int index) throws DescriptorValidationException {
/* 1796 */       this.index = index;
/* 1797 */       this.proto = proto;
/* 1798 */       this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
/* 1799 */       this.file = file;
/* 1800 */       this.containingType = parent;
/*      */       
/* 1802 */       if (proto.getValueCount() == 0)
/*      */       {
/*      */         
/* 1805 */         throw new DescriptorValidationException(this, "Enums must contain at least one value.");
/*      */       }
/*      */       
/* 1808 */       this.values = new EnumValueDescriptor[proto.getValueCount()];
/* 1809 */       for (int i = 0; i < proto.getValueCount(); i++) {
/* 1810 */         this.values[i] = new EnumValueDescriptor(proto.getValue(i), file, this, i);
/*      */       }
/*      */       
/* 1813 */       file.pool.addSymbol(this);
/*      */     }
/*      */ 
/*      */     
/*      */     private void setProto(DescriptorProtos.EnumDescriptorProto proto) {
/* 1818 */       this.proto = proto;
/*      */       
/* 1820 */       for (int i = 0; i < this.values.length; i++) {
/* 1821 */         this.values[i].setProto(proto.getValue(i));
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static final class EnumValueDescriptor
/*      */     extends GenericDescriptor
/*      */     implements Internal.EnumLite
/*      */   {
/*      */     private final int index;
/*      */     
/*      */     private DescriptorProtos.EnumValueDescriptorProto proto;
/*      */     
/*      */     private final String fullName;
/*      */     
/*      */     private final FileDescriptor file;
/*      */     private final EnumDescriptor type;
/*      */     
/*      */     public int getIndex() {
/* 1841 */       return this.index;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public DescriptorProtos.EnumValueDescriptorProto toProto() {
/* 1847 */       return this.proto;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String getName() {
/* 1853 */       return this.proto.getName();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public int getNumber() {
/* 1859 */       return this.proto.getNumber();
/*      */     }
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1864 */       return this.proto.getName();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getFullName() {
/* 1874 */       return this.fullName;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public FileDescriptor getFile() {
/* 1880 */       return this.file;
/*      */     }
/*      */ 
/*      */     
/*      */     public EnumDescriptor getType() {
/* 1885 */       return this.type;
/*      */     }
/*      */ 
/*      */     
/*      */     public DescriptorProtos.EnumValueOptions getOptions() {
/* 1890 */       return this.proto.getOptions();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private EnumValueDescriptor(DescriptorProtos.EnumValueDescriptorProto proto, FileDescriptor file, EnumDescriptor parent, int index) throws DescriptorValidationException {
/* 1905 */       this.index = index;
/* 1906 */       this.proto = proto;
/* 1907 */       this.file = file;
/* 1908 */       this.type = parent;
/*      */       
/* 1910 */       this.fullName = parent.getFullName() + '.' + proto.getName();
/*      */       
/* 1912 */       file.pool.addSymbol(this);
/* 1913 */       file.pool.addEnumValueByNumber(this);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private EnumValueDescriptor(FileDescriptor file, EnumDescriptor parent, Integer number) {
/* 1919 */       String name = "UNKNOWN_ENUM_VALUE_" + parent.getName() + "_" + number;
/*      */       
/* 1921 */       DescriptorProtos.EnumValueDescriptorProto proto = DescriptorProtos.EnumValueDescriptorProto.newBuilder().setName(name).setNumber(number.intValue()).build();
/* 1922 */       this.index = -1;
/* 1923 */       this.proto = proto;
/* 1924 */       this.file = file;
/* 1925 */       this.type = parent;
/* 1926 */       this.fullName = parent.getFullName() + '.' + proto.getName();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void setProto(DescriptorProtos.EnumValueDescriptorProto proto) {
/* 1933 */       this.proto = proto;
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class ServiceDescriptor extends GenericDescriptor {
/*      */     private final int index;
/*      */     private DescriptorProtos.ServiceDescriptorProto proto;
/*      */     private final String fullName;
/*      */     private final FileDescriptor file;
/*      */     private MethodDescriptor[] methods;
/*      */     
/*      */     public int getIndex() {
/* 1945 */       return this.index;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public DescriptorProtos.ServiceDescriptorProto toProto() {
/* 1951 */       return this.proto;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String getName() {
/* 1957 */       return this.proto.getName();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getFullName() {
/* 1967 */       return this.fullName;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public FileDescriptor getFile() {
/* 1973 */       return this.file;
/*      */     }
/*      */ 
/*      */     
/*      */     public DescriptorProtos.ServiceOptions getOptions() {
/* 1978 */       return this.proto.getOptions();
/*      */     }
/*      */ 
/*      */     
/*      */     public List<MethodDescriptor> getMethods() {
/* 1983 */       return Collections.unmodifiableList(Arrays.asList(this.methods));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public MethodDescriptor findMethodByName(String name) {
/* 1993 */       GenericDescriptor result = this.file.pool.findSymbol(this.fullName + '.' + name);
/* 1994 */       if (result != null && result instanceof MethodDescriptor) {
/* 1995 */         return (MethodDescriptor)result;
/*      */       }
/* 1997 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private ServiceDescriptor(DescriptorProtos.ServiceDescriptorProto proto, FileDescriptor file, int index) throws DescriptorValidationException {
/* 2010 */       this.index = index;
/* 2011 */       this.proto = proto;
/* 2012 */       this.fullName = Descriptors.computeFullName(file, null, proto.getName());
/* 2013 */       this.file = file;
/*      */       
/* 2015 */       this.methods = new MethodDescriptor[proto.getMethodCount()];
/* 2016 */       for (int i = 0; i < proto.getMethodCount(); i++) {
/* 2017 */         this.methods[i] = new MethodDescriptor(proto.getMethod(i), file, this, i);
/*      */       }
/*      */       
/* 2020 */       file.pool.addSymbol(this);
/*      */     }
/*      */     
/*      */     private void crossLink() throws DescriptorValidationException {
/* 2024 */       for (MethodDescriptor method : this.methods) {
/* 2025 */         method.crossLink();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     private void setProto(DescriptorProtos.ServiceDescriptorProto proto) {
/* 2031 */       this.proto = proto;
/*      */       
/* 2033 */       for (int i = 0; i < this.methods.length; i++)
/* 2034 */         this.methods[i].setProto(proto.getMethod(i)); 
/*      */     } }
/*      */   
/*      */   public static final class MethodDescriptor extends GenericDescriptor {
/*      */     private final int index;
/*      */     private DescriptorProtos.MethodDescriptorProto proto;
/*      */     private final String fullName;
/*      */     private final FileDescriptor file;
/*      */     private final ServiceDescriptor service;
/*      */     private Descriptor inputType;
/*      */     private Descriptor outputType;
/*      */     
/*      */     public int getIndex() {
/* 2047 */       return this.index;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public DescriptorProtos.MethodDescriptorProto toProto() {
/* 2053 */       return this.proto;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public String getName() {
/* 2059 */       return this.proto.getName();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getFullName() {
/* 2069 */       return this.fullName;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public FileDescriptor getFile() {
/* 2075 */       return this.file;
/*      */     }
/*      */ 
/*      */     
/*      */     public ServiceDescriptor getService() {
/* 2080 */       return this.service;
/*      */     }
/*      */ 
/*      */     
/*      */     public Descriptor getInputType() {
/* 2085 */       return this.inputType;
/*      */     }
/*      */ 
/*      */     
/*      */     public Descriptor getOutputType() {
/* 2090 */       return this.outputType;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isClientStreaming() {
/* 2095 */       return this.proto.getClientStreaming();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isServerStreaming() {
/* 2100 */       return this.proto.getServerStreaming();
/*      */     }
/*      */ 
/*      */     
/*      */     public DescriptorProtos.MethodOptions getOptions() {
/* 2105 */       return this.proto.getOptions();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private MethodDescriptor(DescriptorProtos.MethodDescriptorProto proto, FileDescriptor file, ServiceDescriptor parent, int index) throws DescriptorValidationException {
/* 2124 */       this.index = index;
/* 2125 */       this.proto = proto;
/* 2126 */       this.file = file;
/* 2127 */       this.service = parent;
/*      */       
/* 2129 */       this.fullName = parent.getFullName() + '.' + proto.getName();
/*      */       
/* 2131 */       file.pool.addSymbol(this);
/*      */     }
/*      */ 
/*      */     
/*      */     private void crossLink() throws DescriptorValidationException {
/* 2136 */       GenericDescriptor input = this.file.pool.lookupSymbol(this.proto
/* 2137 */           .getInputType(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
/* 2138 */       if (!(input instanceof Descriptor)) {
/* 2139 */         throw new DescriptorValidationException(this, '"' + this.proto
/* 2140 */             .getInputType() + "\" is not a message type.");
/*      */       }
/* 2142 */       this.inputType = (Descriptor)input;
/*      */ 
/*      */       
/* 2145 */       GenericDescriptor output = this.file.pool.lookupSymbol(this.proto
/* 2146 */           .getOutputType(), this, DescriptorPool.SearchFilter.TYPES_ONLY);
/* 2147 */       if (!(output instanceof Descriptor)) {
/* 2148 */         throw new DescriptorValidationException(this, '"' + this.proto
/* 2149 */             .getOutputType() + "\" is not a message type.");
/*      */       }
/* 2151 */       this.outputType = (Descriptor)output;
/*      */     }
/*      */ 
/*      */     
/*      */     private void setProto(DescriptorProtos.MethodDescriptorProto proto) {
/* 2156 */       this.proto = proto;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String computeFullName(FileDescriptor file, Descriptor parent, String name) {
/* 2164 */     if (parent != null) {
/* 2165 */       return parent.getFullName() + '.' + name;
/*      */     }
/*      */     
/* 2168 */     String packageName = file.getPackage();
/* 2169 */     if (!packageName.isEmpty()) {
/* 2170 */       return packageName + '.' + name;
/*      */     }
/*      */     
/* 2173 */     return name;
/*      */   }
/*      */ 
/*      */   
/*      */   public static abstract class GenericDescriptor
/*      */   {
/*      */     public abstract FileDescriptor getFile();
/*      */ 
/*      */     
/*      */     public abstract String getFullName();
/*      */ 
/*      */     
/*      */     public abstract String getName();
/*      */ 
/*      */     
/*      */     public abstract Message toProto();
/*      */     
/*      */     private GenericDescriptor() {}
/*      */   }
/*      */   
/*      */   public static class DescriptorValidationException
/*      */     extends Exception
/*      */   {
/*      */     private static final long serialVersionUID = 5750205775490483148L;
/*      */     private final String name;
/*      */     private final Message proto;
/*      */     private final String description;
/*      */     
/*      */     public String getProblemSymbolName() {
/* 2202 */       return this.name;
/*      */     }
/*      */ 
/*      */     
/*      */     public Message getProblemProto() {
/* 2207 */       return this.proto;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getDescription() {
/* 2212 */       return this.description;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private DescriptorValidationException(GenericDescriptor problemDescriptor, String description) {
/* 2221 */       super(problemDescriptor.getFullName() + ": " + description);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2226 */       this.name = problemDescriptor.getFullName();
/* 2227 */       this.proto = problemDescriptor.toProto();
/* 2228 */       this.description = description;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private DescriptorValidationException(GenericDescriptor problemDescriptor, String description, Throwable cause) {
/* 2235 */       this(problemDescriptor, description);
/* 2236 */       initCause(cause);
/*      */     }
/*      */ 
/*      */     
/*      */     private DescriptorValidationException(FileDescriptor problemDescriptor, String description) {
/* 2241 */       super(problemDescriptor.getName() + ": " + description);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2246 */       this.name = problemDescriptor.getName();
/* 2247 */       this.proto = problemDescriptor.toProto();
/* 2248 */       this.description = description;
/*      */     }
/*      */   }
/*      */   
/*      */   private static final class DescriptorPool
/*      */   {
/*      */     private final Set<FileDescriptor> dependencies;
/*      */     private boolean allowUnknownDependencies;
/*      */     private final Map<String, GenericDescriptor> descriptorsByName;
/*      */     private final Map<DescriptorIntPair, FieldDescriptor> fieldsByNumber;
/*      */     private final Map<DescriptorIntPair, EnumValueDescriptor> enumValuesByNumber;
/*      */     
/*      */     enum SearchFilter
/*      */     {
/* 2262 */       TYPES_ONLY,
/* 2263 */       AGGREGATES_ONLY,
/* 2264 */       ALL_SYMBOLS;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     DescriptorPool(FileDescriptor[] dependencies, boolean allowUnknownDependencies)
/*      */     {
/* 2300 */       this.descriptorsByName = new HashMap<>();
/*      */       
/* 2302 */       this.fieldsByNumber = new HashMap<>();
/*      */       
/* 2304 */       this.enumValuesByNumber = new HashMap<>(); this.dependencies = new HashSet<>(); this.allowUnknownDependencies = allowUnknownDependencies; for (int i = 0; i < dependencies.length; i++) { this.dependencies.add(dependencies[i]); importPublicDependencies(dependencies[i]); }
/*      */        for (FileDescriptor dependency : this.dependencies) { try { addPackage(dependency.getPackage(), dependency); }
/*      */         catch (DescriptorValidationException e)
/*      */         { throw new AssertionError(e); }
/*      */          }
/* 2309 */        } GenericDescriptor findSymbol(String fullName) { return findSymbol(fullName, SearchFilter.ALL_SYMBOLS); }
/*      */      private void importPublicDependencies(FileDescriptor file) {
/*      */       for (FileDescriptor dependency : file.getPublicDependencies()) {
/*      */         if (this.dependencies.add(dependency))
/*      */           importPublicDependencies(dependency); 
/*      */       } 
/*      */     }
/*      */     GenericDescriptor findSymbol(String fullName, SearchFilter filter) {
/* 2317 */       GenericDescriptor result = this.descriptorsByName.get(fullName);
/* 2318 */       if (result != null && (
/* 2319 */         filter == SearchFilter.ALL_SYMBOLS || (filter == SearchFilter.TYPES_ONLY && 
/* 2320 */         isType(result)) || (filter == SearchFilter.AGGREGATES_ONLY && 
/* 2321 */         isAggregate(result)))) {
/* 2322 */         return result;
/*      */       }
/*      */ 
/*      */       
/* 2326 */       for (FileDescriptor dependency : this.dependencies) {
/* 2327 */         result = dependency.pool.descriptorsByName.get(fullName);
/* 2328 */         if (result != null && (
/* 2329 */           filter == SearchFilter.ALL_SYMBOLS || (filter == SearchFilter.TYPES_ONLY && 
/* 2330 */           isType(result)) || (filter == SearchFilter.AGGREGATES_ONLY && 
/* 2331 */           isAggregate(result)))) {
/* 2332 */           return result;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 2337 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     boolean isType(GenericDescriptor descriptor) {
/* 2342 */       return (descriptor instanceof Descriptor || descriptor instanceof EnumDescriptor);
/*      */     }
/*      */ 
/*      */     
/*      */     boolean isAggregate(GenericDescriptor descriptor) {
/* 2347 */       return (descriptor instanceof Descriptor || descriptor instanceof EnumDescriptor || descriptor instanceof PackageDescriptor || descriptor instanceof ServiceDescriptor);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     GenericDescriptor lookupSymbol(String name, GenericDescriptor relativeTo, SearchFilter filter) throws DescriptorValidationException {
/*      */       GenericDescriptor result;
/*      */       String fullname;
/* 2367 */       if (name.startsWith(".")) {
/*      */         
/* 2369 */         fullname = name.substring(1);
/* 2370 */         result = findSymbol(fullname, filter);
/*      */       } else {
/*      */         String firstPart;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2385 */         int firstPartLength = name.indexOf('.');
/*      */         
/* 2387 */         if (firstPartLength == -1) {
/* 2388 */           firstPart = name;
/*      */         } else {
/* 2390 */           firstPart = name.substring(0, firstPartLength);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 2395 */         StringBuilder scopeToTry = new StringBuilder(relativeTo.getFullName());
/*      */ 
/*      */         
/*      */         while (true) {
/* 2399 */           int dotpos = scopeToTry.lastIndexOf(".");
/* 2400 */           if (dotpos == -1) {
/* 2401 */             fullname = name;
/* 2402 */             GenericDescriptor genericDescriptor = findSymbol(name, filter);
/*      */             break;
/*      */           } 
/* 2405 */           scopeToTry.setLength(dotpos + 1);
/*      */ 
/*      */           
/* 2408 */           scopeToTry.append(firstPart);
/* 2409 */           result = findSymbol(scopeToTry.toString(), SearchFilter.AGGREGATES_ONLY);
/*      */           
/* 2411 */           if (result != null) {
/* 2412 */             if (firstPartLength != -1) {
/*      */ 
/*      */ 
/*      */               
/* 2416 */               scopeToTry.setLength(dotpos + 1);
/* 2417 */               scopeToTry.append(name);
/* 2418 */               result = findSymbol(scopeToTry.toString(), filter);
/*      */             } 
/* 2420 */             fullname = scopeToTry.toString();
/*      */             
/*      */             break;
/*      */           } 
/*      */           
/* 2425 */           scopeToTry.setLength(dotpos);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2430 */       if (result == null) {
/* 2431 */         if (this.allowUnknownDependencies && filter == SearchFilter.TYPES_ONLY) {
/* 2432 */           Descriptors.logger.warning("The descriptor for message type \"" + name + "\" can not be found and a placeholder is created for it");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2442 */           result = new Descriptor(fullname);
/*      */ 
/*      */           
/* 2445 */           this.dependencies.add(result.getFile());
/* 2446 */           return result;
/*      */         } 
/* 2448 */         throw new DescriptorValidationException(relativeTo, '"' + name + "\" is not defined.");
/*      */       } 
/*      */       
/* 2451 */       return result;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void addSymbol(GenericDescriptor descriptor) throws DescriptorValidationException {
/* 2460 */       validateSymbolName(descriptor);
/*      */       
/* 2462 */       String fullName = descriptor.getFullName();
/*      */       
/* 2464 */       GenericDescriptor old = this.descriptorsByName.put(fullName, descriptor);
/* 2465 */       if (old != null) {
/* 2466 */         this.descriptorsByName.put(fullName, old);
/*      */         
/* 2468 */         if (descriptor.getFile() == old.getFile()) {
/* 2469 */           int dotpos = fullName.lastIndexOf('.');
/* 2470 */           if (dotpos == -1) {
/* 2471 */             throw new DescriptorValidationException(descriptor, '"' + fullName + "\" is already defined.");
/*      */           }
/*      */           
/* 2474 */           throw new DescriptorValidationException(descriptor, '"' + fullName
/*      */ 
/*      */               
/* 2477 */               .substring(dotpos + 1) + "\" is already defined in \"" + fullName
/*      */               
/* 2479 */               .substring(0, dotpos) + "\".");
/*      */         } 
/*      */ 
/*      */         
/* 2483 */         throw new DescriptorValidationException(descriptor, '"' + fullName + "\" is already defined in file \"" + old
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 2488 */             .getFile().getName() + "\".");
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     private static final class PackageDescriptor
/*      */       extends GenericDescriptor
/*      */     {
/*      */       private final String name;
/*      */       
/*      */       private final String fullName;
/*      */       private final FileDescriptor file;
/*      */       
/*      */       public Message toProto() {
/* 2502 */         return this.file.toProto();
/*      */       }
/*      */ 
/*      */       
/*      */       public String getName() {
/* 2507 */         return this.name;
/*      */       }
/*      */ 
/*      */       
/*      */       public String getFullName() {
/* 2512 */         return this.fullName;
/*      */       }
/*      */ 
/*      */       
/*      */       public FileDescriptor getFile() {
/* 2517 */         return this.file;
/*      */       }
/*      */       
/*      */       PackageDescriptor(String name, String fullName, FileDescriptor file) {
/* 2521 */         this.file = file;
/* 2522 */         this.fullName = fullName;
/* 2523 */         this.name = name;
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void addPackage(String fullName, FileDescriptor file) throws DescriptorValidationException {
/*      */       String name;
/* 2538 */       int dotpos = fullName.lastIndexOf('.');
/*      */       
/* 2540 */       if (dotpos == -1) {
/* 2541 */         name = fullName;
/*      */       } else {
/* 2543 */         addPackage(fullName.substring(0, dotpos), file);
/* 2544 */         name = fullName.substring(dotpos + 1);
/*      */       } 
/*      */ 
/*      */       
/* 2548 */       GenericDescriptor old = this.descriptorsByName.put(fullName, new PackageDescriptor(name, fullName, file));
/* 2549 */       if (old != null) {
/* 2550 */         this.descriptorsByName.put(fullName, old);
/* 2551 */         if (!(old instanceof PackageDescriptor)) {
/* 2552 */           throw new DescriptorValidationException(file, '"' + name + "\" is already defined (as something other than a package) in file \"" + old
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2558 */               .getFile().getName() + "\".");
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     private static final class DescriptorIntPair
/*      */     {
/*      */       private final GenericDescriptor descriptor;
/*      */       private final int number;
/*      */       
/*      */       DescriptorIntPair(GenericDescriptor descriptor, int number) {
/* 2570 */         this.descriptor = descriptor;
/* 2571 */         this.number = number;
/*      */       }
/*      */ 
/*      */       
/*      */       public int hashCode() {
/* 2576 */         return this.descriptor.hashCode() * 65535 + this.number;
/*      */       }
/*      */ 
/*      */       
/*      */       public boolean equals(Object obj) {
/* 2581 */         if (!(obj instanceof DescriptorIntPair)) {
/* 2582 */           return false;
/*      */         }
/* 2584 */         DescriptorIntPair other = (DescriptorIntPair)obj;
/* 2585 */         return (this.descriptor == other.descriptor && this.number == other.number);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void addFieldByNumber(FieldDescriptor field) throws DescriptorValidationException {
/* 2595 */       DescriptorIntPair key = new DescriptorIntPair(field.getContainingType(), field.getNumber());
/* 2596 */       FieldDescriptor old = this.fieldsByNumber.put(key, field);
/* 2597 */       if (old != null) {
/* 2598 */         this.fieldsByNumber.put(key, old);
/* 2599 */         throw new DescriptorValidationException(field, "Field number " + field
/*      */ 
/*      */             
/* 2602 */             .getNumber() + " has already been used in \"" + field
/*      */             
/* 2604 */             .getContainingType().getFullName() + "\" by field \"" + old
/*      */             
/* 2606 */             .getName() + "\".");
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void addEnumValueByNumber(EnumValueDescriptor value) {
/* 2617 */       DescriptorIntPair key = new DescriptorIntPair(value.getType(), value.getNumber());
/* 2618 */       EnumValueDescriptor old = this.enumValuesByNumber.put(key, value);
/* 2619 */       if (old != null) {
/* 2620 */         this.enumValuesByNumber.put(key, old);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     static void validateSymbolName(GenericDescriptor descriptor) throws DescriptorValidationException {
/* 2632 */       String name = descriptor.getName();
/* 2633 */       if (name.length() == 0) {
/* 2634 */         throw new DescriptorValidationException(descriptor, "Missing name.");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2641 */       for (int i = 0; i < name.length(); ) {
/* 2642 */         char c = name.charAt(i);
/* 2643 */         if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || c == '_' || ('0' <= c && c <= '9' && i > 0)) {
/*      */           i++;
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/* 2649 */         throw new DescriptorValidationException(descriptor, '"' + name + "\" is not a valid identifier.");
/*      */       } 
/*      */     } }
/*      */   public static final class OneofDescriptor extends GenericDescriptor { private final int index; private DescriptorProtos.OneofDescriptorProto proto; private final String fullName;
/*      */     private final FileDescriptor file;
/*      */     private Descriptor containingType;
/*      */     private int fieldCount;
/*      */     private FieldDescriptor[] fields;
/*      */     
/*      */     public int getIndex() {
/* 2659 */       return this.index;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getName() {
/* 2664 */       return this.proto.getName();
/*      */     }
/*      */ 
/*      */     
/*      */     public FileDescriptor getFile() {
/* 2669 */       return this.file;
/*      */     }
/*      */ 
/*      */     
/*      */     public String getFullName() {
/* 2674 */       return this.fullName;
/*      */     }
/*      */     
/*      */     public Descriptor getContainingType() {
/* 2678 */       return this.containingType;
/*      */     }
/*      */     
/*      */     public int getFieldCount() {
/* 2682 */       return this.fieldCount;
/*      */     }
/*      */     
/*      */     public DescriptorProtos.OneofOptions getOptions() {
/* 2686 */       return this.proto.getOptions();
/*      */     }
/*      */     
/*      */     public boolean isSynthetic() {
/* 2690 */       return (this.fields.length == 1 && (this.fields[0]).isProto3Optional);
/*      */     }
/*      */ 
/*      */     
/*      */     public List<FieldDescriptor> getFields() {
/* 2695 */       return Collections.unmodifiableList(Arrays.asList(this.fields));
/*      */     }
/*      */     
/*      */     public FieldDescriptor getField(int index) {
/* 2699 */       return this.fields[index];
/*      */     }
/*      */ 
/*      */     
/*      */     public DescriptorProtos.OneofDescriptorProto toProto() {
/* 2704 */       return this.proto;
/*      */     }
/*      */     
/*      */     private void setProto(DescriptorProtos.OneofDescriptorProto proto) {
/* 2708 */       this.proto = proto;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private OneofDescriptor(DescriptorProtos.OneofDescriptorProto proto, FileDescriptor file, Descriptor parent, int index) throws DescriptorValidationException {
/* 2717 */       this.proto = proto;
/* 2718 */       this.fullName = Descriptors.computeFullName(file, parent, proto.getName());
/* 2719 */       this.file = file;
/* 2720 */       this.index = index;
/*      */       
/* 2722 */       this.containingType = parent;
/* 2723 */       this.fieldCount = 0;
/*      */     } }
/*      */ 
/*      */ }


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\Descriptors.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */