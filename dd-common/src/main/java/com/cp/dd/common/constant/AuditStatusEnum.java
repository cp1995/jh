package com.cp.dd.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 活动 审核状态（审核状态 0待提交，1待审核，2 已审核，3审核不通过）
 *
 * @author chengp
 * @date 2019/9/26
 */
@Getter
@AllArgsConstructor
public enum AuditStatusEnum {

    /**
     * 待提交
     */
    NOT(0, "待提交"),

    /**
     * 待审核
     */
    UNDER(1, "待审核"),

    /**
     * 已审核
     */
    PASS(2, "审核通过"),

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

    /**
     * 根据状态值获取描述
     *
     * @param status 状态
     * @return 描述
     */
    public static String getDescription(int status) {
        for (AuditStatusEnum statusEnum : AuditStatusEnum.values()) {
            if (status == statusEnum.getStatus()) {
                return statusEnum.getDescription();
            }
        }
        return "";
    }

}
