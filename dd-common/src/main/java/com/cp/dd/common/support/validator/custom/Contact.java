package com.cp.dd.common.support.validator.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 联系电话验证（手机+固话）
 */
@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Constraint(validatedBy = {ContactValidator.class})
@Retention(RUNTIME)
public @interface Contact {

    /**
     * 描述
     */
    String message() default "联系电话不正确";

    String pattern() default "^((1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8})|(0[1,2]{1}\\d{1}-?\\d{8})|(0[3-9]{1}\\d{2}-?\\d{7,8})|(0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4}))|(0[3-9]{1}\\d{2}-?\\d{7,8}-(\\d{1,4})))$";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
