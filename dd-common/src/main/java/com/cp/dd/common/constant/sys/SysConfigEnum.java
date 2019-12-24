package com.cp.dd.common.constant.sys;

/**
 * 系统配置表 key 的常量，见表 sys_config.key
 *
 * @author chengp
 * @date 2019/11/21
 */
public enum SysConfigEnum {

    /**
     * 短信
     */
    sms_userid,
    sms_password,
    sms_sendMessageUrl,
    sms_receiveMessageUrl,
    sms_useWhiteList,
    sms_whiteList,

    /**
     * 业务系统访问路径
     */
    system_address,

    /**
     * 微信
     */
    wechat_appid,
    wechat_appsecret,
    wechat_AUTHORIZATION_CODE,

    /**
     * 微信开放平台
     */
    wx_appid,
    wx_appsecret,

    /**
     * 活动签到二维码url
     */
    memberAct_QrCode,

    /**
     * 小程序二維碼配置
     */
    mini_program_act_path,
    mini_program_qrCode_width,

    /**
     * hadoop
     */
    hadoop_DFSMasterName,
    hadoop_nameNodeURI,
    hadoop_username,

    /**
     * solr
     */
    sorl_solrUrl,
    sorl_core,

    /**
     * 获取工作日 接口信息
     */
    workday_appkey,
    workday_sign,
    workday_url,

    /**
     * 全局评论开关
     */
    evaluate_global_enable,
}
