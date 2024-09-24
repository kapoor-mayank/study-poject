package com.google.protobuf;

public interface ValueOrBuilder extends MessageOrBuilder {
  int getNullValueValue();
  
  NullValue getNullValue();
  
  double getNumberValue();
  
  String getStringValue();
  
  ByteString getStringValueBytes();
  
  boolean getBoolValue();
  
  boolean hasStructValue();
  
  Struct getStructValue();
  
  StructOrBuilder getStructValueOrBuilder();
  
  boolean hasListValue();
  
  ListValue getListValue();
  
  ListValueOrBuilder getListValueOrBuilder();
  
  Value.KindCase getKindCase();
}


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\ValueOrBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */