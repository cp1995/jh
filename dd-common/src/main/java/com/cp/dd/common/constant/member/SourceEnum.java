package com.cp.dd.common.constant.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 注册来源枚举
 */
@Getter
@AllArgsConstructor
public enum SourceEnum {

    /**
     * web
     */
    WEB(1,"web"),

    /**
     * admin
     */
    ADMIN(2,"admin"),


    /**
     * android
     */
    ANDROID(3,"android"),

    /**
     * ios
     */
    IOS(4,"ios"),

    /**
     * app
     */
    MINI(5,"mini");




    /**
     * 状态值
     */
    private final int type;

    /**
     * 状态描述
     */
    private final String description;

    public static SourceEnum getEnumByType(Integer type) {
        for (SourceEnum sourceEnum : SourceEnum.values()) {
            if (sourceEnum.getType() == type) {
                return sourceEnum;
            }
        }
        // 默认返回一个
        return SourceEnum.WEB;
    }

}
