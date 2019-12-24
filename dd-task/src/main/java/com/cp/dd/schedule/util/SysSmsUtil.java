package com.cp.dd.schedule.util;

import com.cp.dd.common.constant.sys.SysConfigEnum;
import com.cp.dd.common.util.http.HttpUtil;
import com.cp.dd.common.util.StringToolUtil;
import com.cp.dd.common.util.sys.SysConfigUtil;
import com.cp.dd.common.vo.sms.ReceiveMessageResult;
import com.cp.dd.common.vo.sms.SendMessageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 短信发送工具类
 *
 * @author chengp
 * @date 2019/10/14
 */
@Slf4j
public class SysSmsUtil {

    /**
     * 发送短信
     *
     * @param msg     短信内容
     * @param mobiles 手机号
     * @return 发送结果
     */
    public SendMessageResult sendSms(String msg, String... mobiles) {
        return sendSms(msg, null, mobiles);
    }

    /**
     * 发送短信
     *
     * @param msg      短信内容
     * @param sendTime 发送时间, 为空表示即使发送
     * @param mobiles  手机号
     * @return 发送结果
     */
    public SendMessageResult sendSms(String msg, @Future LocalDateTime sendTime, String... mobiles) {
        Map<String, Object> params = new HashMap<>(16);
        params.put("userid", SysConfigUtil.getString(SysConfigEnum.sms_userid));
        params.put("password", SysConfigUtil.getString(SysConfigEnum.sms_password));
        params.put("destnumbers", StringToolUtil.arrayToString(mobiles));
        params.put("msg", msg);
        if (sendTime != null) {
            params.put("sendtime", sendTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        String result = HttpUtil.post(SysConfigUtil.getString(SysConfigEnum.sms_sendMessageUrl), params);
        if (StringUtils.isBlank(result)) {
            return SendMessageResult.fail("短信发送接口返回为空");
        }

        try {
            Document document = DocumentHelper.parseText(result);
            Element rootElement = document.getRootElement();
            SendMessageResult sendMessageResult = new SendMessageResult();
            sendMessageResult.setCode(Integer.parseInt(rootElement.attributeValue("return")));
            sendMessageResult.setSuccess(sendMessageResult.getCode() == 0);
            sendMessageResult.setInfo(rootElement.attributeValue("info"));
            if (sendMessageResult.isSuccess()) {
                sendMessageResult.setMsgId(rootElement.attributeValue("msgid"));
                sendMessageResult.setNumbers(Integer.parseInt(rootElement.attributeValue("numbers")));
                sendMessageResult.setMessages(Integer.parseInt(rootElement.attributeValue("messages")));
            }
            return sendMessageResult;
        } catch (DocumentException e) {
            e.printStackTrace();
            return SendMessageResult.fail(e.getMessage());
        }
    }

    /**
     * 接收上行短信(上行短成功收取一次后,下一次不可以再收到)
     *
     * @return 结果
     */
    public List<ReceiveMessageResult> receiveMsg() {
        List<ReceiveMessageResult> results = new ArrayList<>();
        Map<String, Object> params = new HashMap<>(16);
        params.put("userid", SysConfigUtil.getString(SysConfigEnum.sms_userid));
        params.put("password", SysConfigUtil.getString(SysConfigEnum.sms_password));

        String result = HttpUtil.get(SysConfigUtil.getString(SysConfigEnum.sms_receiveMessageUrl), params);
        if (StringUtils.isBlank(result)) {
            return results;
        }

        try {
            Document document = DocumentHelper.parseText(result);
            List<Element> elements = document.getRootElement().elements();
            for (Element element : elements) {
                ReceiveMessageResult receiveMessageResult = new ReceiveMessageResult();
                receiveMessageResult.setSender(element.attributeValue("sender"));
                receiveMessageResult.setTarget(element.attributeValue("target"));
                receiveMessageResult.setUptime(LocalDateTime.parse(element.attributeValue("uptime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                receiveMessageResult.setMsg(element.attributeValue("msg"));
                receiveMessageResult.setMt(element.attributeValue("mt"));
                results.add(receiveMessageResult);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return results;
    }

}
