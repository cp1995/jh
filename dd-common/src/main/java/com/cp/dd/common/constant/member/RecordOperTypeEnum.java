package com.cp.dd.common.constant.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 统计表操作枚举
 */
@Getter
@AllArgsConstructor
public enum RecordOperTypeEnum {

    /**
     * 删除
     */
    DELETE(0,"删除"),

    /**
     * 新增
     */
    ADD(1,"新增");

    /**
     * 状态值
     */
    private final int type;

    /**
     * 状态描述
     */
    private final String description;

}
