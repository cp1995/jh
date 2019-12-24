package com.cp.dd.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.cp.dd.common.constant.sys.SysConfigEnum;
import com.cp.dd.common.util.http.HttpUtil;
import com.cp.dd.common.util.sys.SysConfigUtil;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 工作日判断
 * <p>
 * api 地址：https://www.nowapi.com/api/life.workday
 *
 * @author chengp
 * @date 2019/11/6
 */
public class WorkdayUtil {

    /*private static final String APP_KEY = "10003";
    private static final String SIGN = "b59bc3ef6191eb9f747dd4e83c99f2a4";
    private static final String WORKDAY_URL = "http://api.k780.com/?app=life.workday&format=json";*/

    /**
     * 获取工作日信息
     */
    public static Workday getWorkdayInfo(LocalDate date) {
        Map<String, Object> params = new HashMap<>(16);
        params.put("appkey", SysConfigUtil.getString(SysConfigEnum.workday_appkey));
        params.put("sign", SysConfigUtil.getString(SysConfigEnum.workday_sign));
        params.put("date", date.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        final String res = HttpUtil.get(SysConfigUtil.getString(SysConfigEnum.workday_url), params);
        if (res == null) {
            throw new ApiException("获取工作日信息接口请求错误");
        }

        JSONObject json = JSON.parseObject(res);
        Workday workday = new Workday();
        workday.setSuccess(json.getString("success"));
        if ("1".equals(workday.getSuccess())) {
            JSONObject result = json.getJSONObject("result");
            workday.setDate(LocalDate.parse(result.getString("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            workday.setWorkmk(result.getInteger("workmk"));
            workday.setWorknm(result.getString("worknm"));
            workday.setRemark(result.getString("remark"));
        } else {
            workday.setMsgId(json.getString("msgid"));
            workday.setMsg(json.getString("msg"));
        }

        return workday;
    }

    @Data
    public static class Workday {

        /**
         * 成功返回 1
         */
        private String success;

        /**
         * 失败的消息ID
         */
        private String msgId;

        /**
         * 请求失败的消息
         */
        private String msg;

        /**
         * 查询的日期
         */
        private LocalDate date;

        /**
         * 1:工作日 2:假日
         */
        private Integer workmk;

        /**
         * 工作日/假日
         */
        private String worknm;

        /**
         * 当天特殊备注
         */
        private String remark;

    }

    public static void main(String[] args) {
        final Workday workdayInfo = getWorkdayInfo(LocalDate.now());
        System.out.println(workdayInfo);
    }

}
