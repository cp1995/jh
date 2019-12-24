package com.cp.dd.common.support.validator;


import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据校验
 *
 * @author chengp
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new ApiException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new ApiException(message);
        }
    }
}
