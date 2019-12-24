package com.cp.dd.common.constant.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 团队用户 审核状态（1待审核，2 已审核，3审核不通过）
 *
 * @author chengp
 * @date 2019/9/26
 */
@Getter
@AllArgsConstructor
public enum MemberAuditStatusEnum {



    /**
     * 待审核
     */
    UNDER(1, "待审核"),

    /**
     * 已审核
     */
    PASS(2, "已审核"),

    /**
     * 审核不通过
     */
    NOT_PASS(3, "审核不通过"),

    /**
     * 取消预约
     */
    CANCEL(4, "取消预约");

    /**
     * 状态
     */
    private final int status;

    /**
     * 状态描述
     */
    private final String description;
}
