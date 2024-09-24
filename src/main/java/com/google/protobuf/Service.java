package com.google.protobuf;

public interface Service {
  Descriptors.ServiceDescriptor getDescriptorForType();
  
  void callMethod(Descriptors.MethodDescriptor paramMethodDescriptor, RpcController paramRpcController, Message paramMessage, RpcCallback<Message> paramRpcCallback);
  
  Message getRequestPrototype(Descriptors.MethodDescriptor paramMethodDescriptor);
  
  Message getResponsePrototype(Descriptors.MethodDescriptor paramMethodDescriptor);
}


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\Service.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */