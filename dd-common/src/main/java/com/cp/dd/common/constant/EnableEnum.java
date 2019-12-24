package com.cp.dd.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态（-1:删除,1:启用,0:禁用）
 *
 * @author chengp
 * @date 2019/9/26
 */
@Getter
@AllArgsConstructor
public enum EnableEnum {

    /**
     * 删除
     */
    DELETE(-1, "删除"),

    /**
     * 启用
     */
    ENABLED(1, "启用"),

    /**
     * 禁用
     */
    DISABLE(0, "禁用");

    /**
     * 状态值
     */
    private final int value;

    /**
     * 状态描述
     */
    private final String description;
}
