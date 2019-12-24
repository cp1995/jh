package com.cp.dd.common.support.validator.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <p>
 * 文本范围校验器 {@link TextRange}
 * </p>
 *
 * @author chengp
 * @date 2019/5/29
 */
public class TextRangeValidator implements ConstraintValidator<TextRange, String> {

    /**
     * 校验文本集
     */
    private String[] values;

    @Override
    public void initialize(TextRange constraintAnnotation) {
        this.values = constraintAnnotation.values();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 为 null 或者数组长度为 0, 则不进行校验，该注释无效
        if (values == null || values.length == 0) {
            return true;
        }

        for (String s : values) {
            if (s == null) {
                if (value == null) {
                    return true;
                }
                continue;
            } else if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
