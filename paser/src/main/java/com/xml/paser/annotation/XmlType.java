package com.xml.paser.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface XmlType {
    String name();

    String[] propOrder();

    String namespace();

    Class factoryClass();

    String factoryMethod();

}
