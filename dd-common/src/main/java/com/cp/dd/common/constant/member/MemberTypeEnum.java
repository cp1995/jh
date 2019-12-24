package com.cp.dd.common.constant.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * member.type 会员类型（ 1-个人用户, 2-企业用户）
 *
 * @author chengp
 * @date 2019/9/26
 */
@Getter
@AllArgsConstructor
public enum MemberTypeEnum {

    /**
     * 个人用户
     */
    PERSONAL(1, "个人用户"),

    /**
     * 团队用户
     */
    TEAM(2, "团队用户");

    /**
     * 类型值
     */
    private final int type;

    /**
     * 类型描述
     */
    private final String description;
}
