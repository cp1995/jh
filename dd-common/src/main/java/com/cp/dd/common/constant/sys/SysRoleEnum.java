package com.cp.dd.common.constant.sys;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统角色 见表 sys_role
 *
 * @author chengp
 * @date 2019/11/14
 */
@Getter
@AllArgsConstructor
public enum SysRoleEnum {

    /**
     *
     */
    ADMIN("admin", "系统管理员"),

    OP_OPERATOR("op_operator", "运营团队操作员"),

    OP_AUDITOR("op_auditor", "运营团队审核员"),

    UNIT_LEADER("unit_leader", "文化单位负责人"),

    UNIT_OPERATOR("unit_operator", "文化单位操作员"),

    UNIT_AUDITOR("unit_auditor", "文化单位审核员");

    /**
     * 角色编号
     */
    private final String code;

    /**
     * 角色描述
     */
    private final String description;
}
