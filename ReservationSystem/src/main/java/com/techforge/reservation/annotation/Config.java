package com.techforge.reservation.annotation;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@ConfigurationProperties
public @interface Config {

    @AliasFor(annotation = ConfigurationProperties.class, attribute = "prefix")
    String value();

}
