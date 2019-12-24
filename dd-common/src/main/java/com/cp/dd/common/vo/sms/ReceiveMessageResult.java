package com.cp.dd.common.vo.sms;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 接收上行短信 接收对象
 *
 * @author chengp
 * @date 2019/10/25
 */
@Data
public class ReceiveMessageResult {

    /**
     * 手机号码
     */
    private String sender;

    /**
     * 上行的目标号
     */
    private String target;

    /**
     * 发送时间
     */
    private LocalDateTime uptime;

    /**
     * 发送内容
     */
    private String msg;

    /**
     * 对应的下行内容
     */
    private String mt;

}
