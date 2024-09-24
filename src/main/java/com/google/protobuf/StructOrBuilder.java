package com.google.protobuf;

import java.util.Map;

public interface StructOrBuilder extends MessageOrBuilder {
  int getFieldsCount();
  
  boolean containsFields(String paramString);
  
  @Deprecated
  Map<String, Value> getFields();
  
  Map<String, Value> getFieldsMap();
  
  Value getFieldsOrDefault(String paramString, Value paramValue);
  
  Value getFieldsOrThrow(String paramString);
}


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\StructOrBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */