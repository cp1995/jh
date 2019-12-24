package com.cp.dd.common.exception;

import com.cp.dd.common.support.IResultCode;
import com.cp.dd.common.support.ResultCode;
import lombok.Getter;

/**
 * 接口请求异常类
 *
 * @author chengp
 */
@Getter
public class ApiException extends RuntimeException {

    /**
     * 错误码
     */
    private int code;

    public ApiException(IResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMsg());
    }

    public ApiException(String msg) {
        this(ResultCode.FAILURE.getCode(), msg);
    }

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public ApiException(Throwable cause) {
        super(cause);
        this.code = ResultCode.FAILURE.getCode();
    }
}
