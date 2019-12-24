package com.cp.dd.common.constant.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * member_collect /收藏/关注/点赞类型（ 1-收藏, 2-关注，3-点赞，4-分享）
 * <p>
 * 新增类型往后面添加
 *
 * @author chengp
 * @date 2019/9/26
 */
@Getter
@AllArgsConstructor
public enum CollectOperTypeEnum {

    /**
     * 收藏
     */
    COLLECT(1, "收藏"),

    /**
     * 关注
     */
    ATTENTION(2, "关注"),

    /**
     * 点赞
     */
    LIKE(3, "点赞"),

    /**
     * 分享
     */
    SHAIR(4,"分享");

    /**
     * 状态值
     */
    private final int type;

    /**
     * 状态描述
     */
    private final String description;

    public static CollectOperTypeEnum getEnumByType(Integer type) {
        for (CollectOperTypeEnum typeEnum : CollectOperTypeEnum.values()) {
            if (typeEnum.getType() == type) {
                return typeEnum;
            }
        }
        return null;
    }
}
