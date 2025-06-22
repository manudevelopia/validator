package info.developia.lib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Number {
    String message() default "Value size is invalid";

    long min() default 0;

    long max() default Long.MAX_VALUE;

    boolean positive() default false;
}
