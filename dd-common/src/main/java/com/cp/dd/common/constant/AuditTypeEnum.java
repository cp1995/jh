package com.cp.dd.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审核类型（1-活动，2-文化设施，3-活动场室）
 * <p>
 * 有其他类型后续加
 *
 * @author chengp
 * @date 2019/7/12
 */
@Getter
@AllArgsConstructor
public enum AuditTypeEnum {

    /**
     * 活动
     */
    ACT(1, "活动"),

    /**
     * 文化设施
     */
    VENUE(2, "文化设施"),

    /**
     * 活动场室
     */
    VENUE_ROOM(3, "活动场室"),
    /**
     * 话题
     */
    VENUE_TOPIC(4, "话题"),
    
    /**
     * 
     */
    CLUB(5, "文化社团");

    /**
     * 状态
     */
    private final int type;

    /**
     * 状态描述
     */
    private final String description;
}
