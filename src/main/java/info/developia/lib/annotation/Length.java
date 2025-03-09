package info.developia.lib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Length {
    String message() default "Value length is invalid";

    int min() default 0;

    int max() default Integer.MAX_VALUE;
}
