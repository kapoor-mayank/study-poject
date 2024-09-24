package com.google.protobuf;

public interface RpcChannel {
  void callMethod(Descriptors.MethodDescriptor paramMethodDescriptor, RpcController paramRpcController, Message paramMessage1, Message paramMessage2, RpcCallback<Message> paramRpcCallback);
}


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\RpcChannel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */