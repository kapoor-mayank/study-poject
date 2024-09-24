package com.google.protobuf;

import java.util.List;

public interface EnumValueOrBuilder extends MessageOrBuilder {
  String getName();
  
  ByteString getNameBytes();
  
  int getNumber();
  
  List<Option> getOptionsList();
  
  Option getOptions(int paramInt);
  
  int getOptionsCount();
  
  List<? extends OptionOrBuilder> getOptionsOrBuilderList();
  
  OptionOrBuilder getOptionsOrBuilder(int paramInt);
}


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\EnumValueOrBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */