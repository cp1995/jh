package com.cp.dd.common.support.validator.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * <p>
 * 手机号码校验器 {@link Mobile}
 * </p>
 *
 * @author chengp
 * @date 2019/5/29
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

    /**
     * 手机验证规则
     */
    private Pattern pattern;

    @Override
    public void initialize(Mobile constraintAnnotation) {
        pattern = Pattern.compile(constraintAnnotation.pattern());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return pattern.matcher(value).matches();
    }
}
