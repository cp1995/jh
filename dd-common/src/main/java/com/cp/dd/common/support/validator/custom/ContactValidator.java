package com.cp.dd.common.support.validator.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 *<p>
 * 联系方式校验器 {@link Contact}
 * </p>
 */
public class ContactValidator implements ConstraintValidator<Contact, String> {
    /**
     * 联系方式验证规则
     */
    private Pattern pattern;

    @Override
    public void initialize(Contact constraintAnnotation) {
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
