package com.apeman.library.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Rango on 2019-08-23 wangqiang@smzdm.com
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface AutoWind {
    /**
     * 绑定View id
     */
    int viewId();

    /**
     * 数据字段名称
     */
    String payloadKey() default "";

    /**
     * 设置点击监听
     * @return true 添加{@link android.view.View.OnClickListener},false 不添加
     */
    boolean clickable() default false;
}

