package com.cp.dd.common.util;


import com.cp.dd.common.util.security.AesEncryptUtil;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtils {
	
	 /**
     * 检测是否手机号
     * 
     * @param mobile
     * @return
     */
    public static boolean isMobileNO(String mobile) {
        Pattern telecregexp = Pattern
            .compile("^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$");
        return telecregexp.matcher(mobile).matches();
    }

    /**
     * 检查是否符合密码规则
     * 8-20位字符，至少包含数字、大写字母、小写字母、特殊字符中的三种类型
     * @param password
     * @return
     */
    public static boolean checPasswork(String password) {
    	password = AesEncryptUtil.decrypt(password);
        Pattern pattern = Pattern.compile("^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,16}$");
        return pattern.matcher(password).matches();
    }

    /**
     * 用户名 只允许输入 数字字母下划线
     *   [a-zA-Z0-9_\u4e00-\u9fa5]+$  用户名 只允许输入 数字字母下划线中文
     * @param username
     * @return
     */
    public static boolean checkUserName(String username) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{4,14}+$");
        return pattern.matcher(username).matches();
    }

    /**
     * 获取8位数随机密码
     * @return
     */
    public static String getPassword(){
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        String strAll = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 8; i++) {
            int f = (int) (Math.random()*61);
            sb.append(strAll.charAt(f));
        }
        return sb.toString();
    }

    /**
     * 获取字符串长度,中文算两个长度
     * @param str
     * @return 返回字符串长度
     */
    public static int strLength(String str){
       return  getStrByteLength(str);
    }

    /**
     * 匹配
     * 如果str符合regex的规则，则返回true否者返回false；
     * @param str ：待验证的字符
     * @param regex ：正则表达式规则
     * @return
     */
    public static boolean checkMatch(String str, String regex) {
        boolean result = true;
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            result = matcher.matches();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
    /**
     * 检测字符串的字节数
     * @param str 待检测的字符串
     * @return 字符串的字节数
     */
    public static int getStrByteLength(String str) {
        int length=0;
        for (int k = 0; k <str.length(); k++) {
            //判断是否包含中文或者中文字符
            if (checkMatch(String.valueOf(str.charAt(k)), "[\u0391-\uFFE5]")) {
                length+=2;
            }else {
                length+=1;
            }
        }
        return length;
    }

    /**
     * 检测字符串的字节数
     * @param str 待检测的字符串
     * @return 字符串的字节数
     */
    public static long getStrByteLength1(String str) {
        // 如果是中文，替换成aa，因为aa刚好是两个字节
        long length = str.replaceAll("[\u0391-\uFFE5]", "aa").length();
        return length;
    }


    /**
     * 检查联系方式
     * @param linkPhone 联系方式
     * @return true or false
     */
    public static boolean isLinkPhone(String linkPhone) {
        Pattern telecregexp = Pattern
                .compile("^((1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8})|(0[1,2]{1}\\d{1}-?\\d{8})|(0[3-9]{1}\\d{2}-?\\d{7,8})|(0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4}))|(0[3-9]{1}\\d{2}-?\\d{7,8}-(\\d{1,4})))$");
        return telecregexp.matcher(linkPhone).matches();
    }
}
