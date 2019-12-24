package com.cp.dd.common.constant.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登陆来源0 web,1 app,2 小程序,
 *
 * @author chengp
 * @date 2019/11/26
 */
@Getter
@AllArgsConstructor
public enum MemberLoginRecordEnum {

    /**
     * WEB
     */
    WEB(0, "WEB"),

    /**
     * APP
     */
    APP(1, "APP"),

    /**
     * 小程序
     */
    MINI(2, "小程序");

    /**
     * 类型值
     */
    private final int type;

    /**
     * 类型描述
     */
    private final String description;
}
