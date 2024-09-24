package com.google.protobuf;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.PACKAGE, ElementType.TYPE})
@Documented
public @interface ExperimentalApi {
  String value() default "";
}


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\com\google\protobuf\ExperimentalApi.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */