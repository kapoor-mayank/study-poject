package com.google.protobuf;

import java.util.List;

public interface MethodOrBuilder extends MessageOrBuilder {
  String getName();
  
  ByteString getNameBytes();
  
  String getRequestTypeUrl();
  
  ByteString getRequestTypeUrlBytes();
  
  boolean getRequestStreaming();
  
  String getResponseTypeUrl();
  
  ByteString getResponseTypeUrlBytes();
  
  boolean getResponseStreaming();
  
  List<Option> getOptionsList();
  
  Option getOptions(int paramInt);
  
  int getOptionsCount();
  
  List<? extends OptionOrBuilder> getOptionsOrBuilderList();
  
  OptionOrBuilder getOptionsOrBuilder(int paramInt);
  
  int getSyntaxValue();
  
  Syntax getSyntax();
}


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\MethodOrBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */