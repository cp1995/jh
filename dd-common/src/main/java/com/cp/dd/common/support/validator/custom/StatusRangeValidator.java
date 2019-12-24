package com.cp.dd.common.support.validator.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <p>
 * 状态范围校验器 {@link StatusRange}
 * </p>
 *
 * @author chengp
 * @date 2019/5/29
 */
public class StatusRangeValidator implements ConstraintValidator<StatusRange, Number> {

    /**
     * 校验文本集
     */
    private int[] values;

    @Override
    public void initialize(StatusRange constraintAnnotation) {
        this.values = constraintAnnotation.values();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        // 为 null 或者数组长度为 0, 则不进行校验，该注释无效
        if (values == null || values.length == 0) {
            return true;
        }

        if (value == null) {
            return true;
        }

        for (int i : values) {
            if (value.intValue() == i) {
                return true;
            }
        }
        return false;
    }
}
