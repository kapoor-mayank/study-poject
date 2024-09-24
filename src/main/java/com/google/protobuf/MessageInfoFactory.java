package com.google.protobuf;

interface MessageInfoFactory {
  boolean isSupported(Class<?> paramClass);
  
  MessageInfo messageInfoFor(Class<?> paramClass);
}


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\MessageInfoFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */