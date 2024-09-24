package com.google.protobuf;

public interface BlockingRpcChannel {
  Message callBlockingMethod(Descriptors.MethodDescriptor paramMethodDescriptor, RpcController paramRpcController, Message paramMessage1, Message paramMessage2) throws ServiceException;
}


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\BlockingRpcChannel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */