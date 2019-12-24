package com.cp.dd.common.constant.sys;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 发送状态（0-未发送，1-发送成功，2-发送失败）
 *
 * @author chengp
 * @date 2019/10/25
 */
@Getter
@AllArgsConstructor
public enum SysSmsStatusEnum {

    /**
     * 未发送
     */
    NOT(0, "未发送"),

    /**
     * 发送成功
     */
    SUCCESS(1, "发送成功"),

    /**
     * 发送失败
     */
    FAIL(2, "发送失败"),

    /**
     * 无效
     */
    INVALID(-1, "无效");

    /**
     * 类型
     */
    private final int status;

    /**
     * 描述
     */
    private final String description;
}
