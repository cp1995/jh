package com.cp.dd.common.constant.sys;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 岗位状态（-1:删除,1:启用,0:禁用）
 */
@Getter
@AllArgsConstructor
public enum SysDeptStatusEnum {
    /**
     * 删除
     */
    DELETE(-1, "删除"),

    /**
     * 启用
     */
    ENABLE(1, "启用"),

    /**
     * 禁用
     */
    DISABLE(0, "禁用");

    /**
     * 类型
     */
    private final int status;

    /**
     * 描述
     */
    private final String description;
}

