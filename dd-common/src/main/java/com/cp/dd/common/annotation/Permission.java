package com.cp.dd.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限限制，通过角色控制接口权限
 *
 * @author chengp
 * @date 2019/9/20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
public @interface Permission {

    /**
     * 角色编号，见表 sys_role.role_code
     */
    String[] value() default {};
}
