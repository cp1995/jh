package com.cp.dd.common.constant.sys;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 短信模板 枚举 (在sys_sms_template中新增模板后，要在此添加 编号和描述)
 *
 * @author chengp
 * @date 2019/10/25
 */
@Getter
@AllArgsConstructor
public enum SmsTemplateEnum {

    /**
     * 短信模板，见表 sys_sms_template
     */
    CAPTCHA("captcha", "验证码"),

    TOPIC_AUDIT_SUCCESS("topic_audit_success", "话题审核成功"),

    TOPIC_AUDIT_FAIL("topic_audit_fail", "话题审核失败"),

    ROOM_ORDER_AUDIT_PASS("room_order_audit_pass", "活动场室预订审核通过"),

    ROOM_ORDER_AUDIT_NOT_PASS("room_order_audit_not_pass", "活动场室预订审核不通过"),

    ROOM_ORDER_CANCEL("room_order_cancel", "活动场室预订取消"),

    Add_USER_TEAM("add_user_team", "创建团队用户成功"),

    USER_TEAM_PASS("user_team_pass", "团队认证审核通过"),

    USER_TEAM_NOT_PASS("user_team_not_pass", "团队认证审核不通过"),

    ROOM_ORDER_AUDIT_NOTIFY("room_order_audit_notify","活动场室预订审核提醒"),

    SYS_USER_ACCOUNT_CREATE("sys_user_account_create","登录账户为：{0}，密码为：{1}，请登录{2}进行业务使用。"),

    SYS_USER_RESET_PASSWORD("sys_user_reset_password","您的密码重置为：{0}，请登录{1}进行业务使用。"),

    ACT_ORDER_APPLY_SUCCESS("act_order_apply_success","亲爱的{0}，恭喜您成功报名《{1}》活动，报名人数{2}人。活动进场时间为：{3}；活动签到时间为{4}，请准时参加！详细内容请前往【个人中心】查看！如需取消报名，请至“我的个人中心——我的活动“，取消截止至活动前两天！"),

    ACT_ORDER_AUDIT_SUCCESS("act_order_audit_success","亲爱的{0}，恭喜您成功报名《{1}》活动，报名人数{2}人。活动进场时间为：{3}；活动签到时间为{4}，请准时参加！详细内容请前往【个人中心】查看！如需取消报名，请至“我的个人中心——我的活动“，取消截止至活动前两天！"),

    ACT_ORDER_AUDIT_FAIL("act_order_audit_fail","亲爱的{0}，十分遗憾通知你，本次活动《{1}》报名失败！想了解更多活动信息，请关注中山文旅云平台。"),

    ACT_CANCEL("act_cancel","尊敬的{0}，十分抱歉通知你，你报名的《{1}》活动，由于遇到不可对抗原因无法正常举行，暂时取消。给你造成的不便十分抱歉，请谅解！"),

    ACT_START_INFORM("act_start_inform","尊敬的{0}，你报名的《{1}》活动即将开始，活动时间为：{2}，请提前1小时到现场进行签到！"),

    ACT_APPLY_CANCEL("act_apply_cancel","亲爱的{0}，活动《{1}》取消报名成功！"),

    ACT_ADD_TICKET_CHECKER("act_add_ticket_checker","尊敬的{0}，你已经成为《{1}》活动【{2}】场次的验票员，请于前往中山文旅云app—个人中心确认验票详细信息。")
    ;

    /**
     * 短信模板编号
     */
    private final String code;

    /**
     * 短信模板描述
     */
    private final String description;
}
