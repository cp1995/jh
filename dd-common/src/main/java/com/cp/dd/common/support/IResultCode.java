package com.cp.dd.common.support;

/**
 * 业务状态码接口
 *
 * @author chengp
 */
public interface IResultCode {

    /**
     * 状态码
     *
     * @return int
     */
    int getCode();

    /**
     * 消息
     *
     * @return String
     */
    String getMsg();
}
