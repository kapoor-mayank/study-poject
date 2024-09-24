package com.google.protobuf;

interface MessageInfo {
  ProtoSyntax getSyntax();
  
  boolean isMessageSetWireFormat();
  
  MessageLite getDefaultInstance();
}


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\MessageInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */