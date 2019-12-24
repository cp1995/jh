package com.cp.dd.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimesUtil {

    private static DateTimeFormatter secondFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static DateTimeFormatter minuteFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static String getTimes(LocalDateTime time1, LocalDateTime time2) {
        if (time1.equals(time2)) {
            return time1.format(minuteFormatter);
        }
        LocalDateTime bigDate;
        LocalDateTime smallDate;
        if (time1.isBefore(time2)) {
            bigDate = time2;
            smallDate = time1;
        }else{
            bigDate = time1;
            smallDate = time2;
        }
        String bigDay = dayFormatter.format(bigDate);
        String smallDay = dayFormatter.format(smallDate);
        if (bigDay.equals(smallDay)) {
            return bigDay + " " + getHourSecond(smallDate) + " 至 " + getHourSecond(bigDate);
        } else {
            return smallDate.format(minuteFormatter) + " 至 " + bigDate.format(minuteFormatter);
        }
    }

    //获取时分秒
    private static String getHourSecond(LocalDateTime localDateTime) {
        String hour = localDateTime.getHour() > 9 ? "" + localDateTime.getHour() : "0" + localDateTime.getHour();
        String minute = localDateTime.getMinute() > 9 ? "" + localDateTime.getMinute() : "0" + localDateTime.getMinute();
        return hour + ":" + minute;
    }

    public static void main(String[] args) {
        String str1 = "2019-11-25 09:00:00";
        String str2 = "2019-11-25 10:00:00";

        LocalDateTime localDateTime1 = LocalDateTime.parse(str1,secondFormatter);
        LocalDateTime localDateTime2 = LocalDateTime.parse(str2,secondFormatter);

        System.out.println(getTimes(localDateTime2,localDateTime1));

    }
}
