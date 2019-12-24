package com.cp.dd.common.constant.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户类型  1会员用户2后台用户
 *
 * @author chengp
 * @date 2019/11/26
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum {

    /**
     * WEB
     */
    MEMBER(1, "会员用户"),

    /**
     * 后台用户
     */
    ADMIN(2, "后台用户");


    /**
     * 类型值
     */
    private final int type;

    /**
     * 类型描述
     */
    private final String description;
}
