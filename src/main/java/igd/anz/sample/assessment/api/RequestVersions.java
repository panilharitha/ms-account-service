package igd.anz.sample.assessment.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestVersions {

    int[] supportedVersions() default {};
    String togglePropertyPrefix();
    String toggleedOffMessage() default "";
    String pathPrefix() default "";

}
