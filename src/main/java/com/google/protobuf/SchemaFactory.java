package com.google.protobuf;

interface SchemaFactory {
  <T> Schema<T> createSchema(Class<T> paramClass);
}


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\SchemaFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */