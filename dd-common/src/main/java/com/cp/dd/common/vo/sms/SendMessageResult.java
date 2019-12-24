package com.cp.dd.common.vo.sms;

import lombok.Data;

/**
 * 短信发送对象 接收对象
 *
 * @author chengp
 * @date 2019/10/25
 */
@Data
public class SendMessageResult {

    /**
     * 返回结果码
     */
    private int code;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 返回结果说明
     */
    private String info;

    /**
     * 短信批号
     */
    private String msgId;

    /**
     * 总计号码个数
     */
    private int numbers;

    /**
     * 总计短信条数
     */
    private int messages;

    public static SendMessageResult fail(String msg) {
        SendMessageResult result = new SendMessageResult();
        result.setCode(-4);
        result.setInfo(msg == null ? "短信请求发送错误" : msg);
        return result;
    }
}
