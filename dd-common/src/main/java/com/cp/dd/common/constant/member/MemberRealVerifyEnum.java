package com.cp.dd.common.constant.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberRealVerifyEnum {
    /**
     * 0未实名1已实名
     */

    REAL_VERIFY(1,"已实名"),

    NO_REAL_VERIFY(0,"未实名");
    /**
     * 编号
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String description;
}
