package com.cp.dd.common.constant.sys;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作日志类型 枚举 sys_oper_log.type 0-其他，1-新增，2-修改，3-删除，4-审核
 *
 * @author chengp
 * @date 2019/10/25
 */
@Getter
@AllArgsConstructor
public enum OperTypeEnum {

    /**
     * 操作日志类型
     */
    OTHER(0, "其他"),

    ADD(1, "新增"),

    UPDATE(2, "修改"),

    DELETE(3, "删除"),

    AUDIT(4, "审核");

    /**
     * 短信模板编号
     */
    private final int type;

    /**
     * 短信模板描述
     */
    private final String description;
}
