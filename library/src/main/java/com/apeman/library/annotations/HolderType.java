package com.apeman.library.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Rango on 2019-08-23 wangqiang@smzdm.com
 */
@Target(ElementType.TYPE)
@Retention(RUNTIME)
public @interface HolderType {
    int cellType();
}
