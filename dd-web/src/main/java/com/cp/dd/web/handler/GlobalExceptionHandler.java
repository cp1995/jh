package com.cp.dd.web.handler;

import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.cp.dd.common.support.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 全局统一异常处理
 *
 * @author chengp
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${global.exception.show}")
    private Boolean isShow;

    /**
     * Api接口自定义异常拦截
     *
     * @param e Api接口自定义异常
     * @return 错误返回消息
     */
    @ExceptionHandler(ApiException.class)
    public Result apiExceptionHandler(ApiException e) {
        log.info("Api接口自定义异常：{}", e.getMessage());
        return Result.fail(e);
    }

    /**
     * hibernate validator 数据绑定验证异常拦截
     *
     * @param e 绑定验证异常
     * @return 错误返回消息
     */
    @ExceptionHandler(BindException.class)
    public Result defaultErrorHandler(BindException e) {
        ObjectError error = e.getAllErrors().get(0);
        log.info("数据验证异常：{}", error.getDefaultMessage());
        return Result.fail(error.getDefaultMessage());
    }

    /**
     * hibernate validator 数据绑定验证异常拦截，@RequestBody + @Valid 验证错误时抛出异常
     *
     * @param e 绑定验证异常
     * @return 错误返回消息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result validateErrorHandler(MethodArgumentNotValidException e) {
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        log.info("数据验证异常：{}", error.getDefaultMessage());
        return Result.fail(error.getDefaultMessage());
    }

    /**
     * spring validator 方法参数验证异常拦截
     *
     * @param e 绑定验证异常
     * @return 错误返回消息
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result defaultErrorHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        log.info("数据验证异常：{}", violation.getMessage());
        return Result.fail(violation.getMessage());
    }


    /**
     * 统一异常拦截
     *
     * @param e 异常
     * @return 错误返回消息
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result defaultErrorHandler(Exception e) {
        log.error("服务异常", e);
        if (isShow) {
            return Result.fail(e.getMessage());
        }
        return Result.fail("系统错误");
    }
}
