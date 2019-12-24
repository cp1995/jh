package com.cp.dd.common.support;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zou
 */
@Data
@ToString
@ApiModel(description = "返回消息")
public class Result<T> implements Serializable {

    @ApiModelProperty(value = "状态码", required = true)
    private int code;
    @ApiModelProperty(value = "是否成功", required = true)
    private boolean success;
    @ApiModelProperty(value = "返回消息", required = true)
    private String msg;
    @ApiModelProperty("承载数据")
    private T data;


    private Result(IResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private Result(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.success = ResultCode.SUCCESS.getCode() == code;
    }

    public static <T> Result<PageModel<T>> success(IPage<T> page) {
        return build(ResultCode.SUCCESS, new PageModel<>(page));
    }

    public static <T> Result<T> success() {
        return build(ResultCode.SUCCESS);
    }

    public static <T> Result<T> success(T data) {
        return build(ResultCode.SUCCESS, data);
    }

    public static <T> Result<T> success(String msg) {
        return build(ResultCode.SUCCESS, msg);
    }

    public static <T> Result<T> success(T data, String msg) {
        return build(ResultCode.SUCCESS, data, msg);
    }

    public static <T> Result<T> build(IResultCode resultCode) {
        return build(resultCode, null, resultCode.getMsg());
    }

    public static <T> Result<T> build(IResultCode resultCode, T data) {
        return build(resultCode, data, resultCode.getMsg());
    }

    public static <T> Result<T> build(IResultCode resultCode, String msg) {
        return build(resultCode, null, msg);
    }

    public static <T> Result<T> build(IResultCode resultCode, T data, String msg) {
        return new Result<>(resultCode, data, msg);
    }

    public static <T> Result<T> build(int code, String msg) {
        return new Result<>(code, null, msg);
    }

    public static Result fail(String msg) {
        return build(ResultCode.FAILURE, msg);
    }

    public static Result fail(IResultCode resultCode) {
        return build(resultCode);
    }

    public static <T> Result<T> fail(T data) {
        return build(ResultCode.FAILURE, data);
    }

    public static <T> Result<T> fail(T data, String msg) {
        return build(ResultCode.FAILURE, data, msg);
    }

}
