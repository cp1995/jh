package com.cp.dd.common.constant.sys;

/**
 * 系统字典code常量
 *
 * @author chengp
 * @date 2019/9/26
 */
public enum SysDictCodeConstant {

    /**
     * 文化设施类型
     */
    venue_category,

    /**
     * 文化设施活动场室功能
     */
    venue_room_func,

    /**
     * 文化设施活动场室设备
     */
    venue_room_equipment;



    public static SysDictCodeConstant getEnum(String code) {
        for (SysDictCodeConstant value : SysDictCodeConstant.values()) {
            if (value.name().equalsIgnoreCase(code)) {
                return value;
            }
        }
        return null;
    }

}
