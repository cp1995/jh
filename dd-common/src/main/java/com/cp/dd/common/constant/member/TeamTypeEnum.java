package com.cp.dd.common.constant.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * member.type 艺术团队类型（ 1艺术团队 2其它）
 *
 * @author chengp
 * @date 2019/11/15
 */
@Getter
@AllArgsConstructor
public enum TeamTypeEnum {

    /**
     * 艺术团队
     */
    ART(1, "艺术团队"),

    /**
     * 其它
     */
    OTHERS(2, "其它");

    /**
     * 类型值
     */
    private final int type;

    /**
     * 类型描述
     */
    private final String description;
}
