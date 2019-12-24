package com.cp.dd.common.support.validator.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>文本范围校验注解<p/>
 * <p>values 为 null 或者数组长度为 0, 则不进行校验，该注释无效</p>
 *
 * @author chengp
 * @date 2019/5/29
 */
@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Constraint(validatedBy = {TextRangeValidator.class})
@Retention(RUNTIME)
public @interface TextRange {
    /**
     * 描述
     */
    String message() default "文本内容不在范围内";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 校验文本集, 为 null 或者数组长度为 0, 则不进行校验，该注释无效
     */
    String[] values();
}
