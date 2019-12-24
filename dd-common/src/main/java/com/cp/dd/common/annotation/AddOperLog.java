package com.cp.dd.common.annotation;


import com.cp.dd.common.constant.sys.OperTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chengp
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AddOperLog {

    /**
     * 业务操作
     */
    String value() default "";

    /**
     * 业务描述
     */
    String desc() default "";

    /**
     * 操作类型
     */
    OperTypeEnum type() default OperTypeEnum.OTHER;
}