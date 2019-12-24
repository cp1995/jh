package com.cp.dd.common.constant.member;

import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 会员评论/收藏/关注/点赞类型（1-活动，2-文化设施，3-话题，4-你点我送，5-活动场室，6-文化社团,7-文化资讯,8-远程点播,9-精品路线,10-文化旅游）
 * <p>
 * 新增类型往后面添加
 *
 * @author chengp
 * @date 2019/9/26
 */
@JSONType(serializeEnumAsJavaBean = true)
@Getter
@AllArgsConstructor
public enum EvaluateCollectTypeEnum implements IEnum<Integer> {

    /**
     * 活动
     */
    ACT(1, "活动", "act_info"),

    /**
     * 文化设施
     */
    VENUE(2, "文化设施", "venue_info"),

    /**
     * 话题
     */
    TOPIC(3, "话题", "topic_info"),

    /**
     * 你点我送
     */
    TYOMG(4, "你点我送", "tyomg_need_info"),

    /**
     * 活动场室
     */
    VENUE_ROOM(5, "活动场室", "venue_room"),

    /**
     * 文化社团
     */
    CLUB(6, "文化社团", "club_info"),

    /**
     * 文化资讯
     */
    CULTURE_INFO(7, "文化资讯", "tcultureask_info"),

    /**
     * 远程点播
     */
    VIDEO(8, "远程点播", "video_info"),

    /**
     * 精品路线
     */
    ROUTE(9, "精品路线", "tour_gold_route_info"),

    /**
     * 文化旅游
     */
    TOUR(10, "文化旅游", "tour_info");

    /**
     * 状态值
     */
    private final int type;

    /**
     * 状态描述
     */
    private final String description;

    /**
     * 表名
     */
    private final String tableName;

    @Override
    public Integer getValue() {
        return this.type;
    }

    public static EvaluateCollectTypeEnum getEnumByType(int type) {
        for (EvaluateCollectTypeEnum typeEnum : EvaluateCollectTypeEnum.values()) {
            if (typeEnum.getType() == type) {
                return typeEnum;
            }
        }
        return null;
    }
}
