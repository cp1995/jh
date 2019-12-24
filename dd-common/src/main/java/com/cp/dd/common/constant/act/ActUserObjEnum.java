package com.cp.dd.common.constant.act;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActUserObjEnum {

    NO_REAL_AUTHENTICATION(0,"未实名"),

    REAL_AUTHENTICATION(1,"已实名");

    /**
     * 编号
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String description;
}
