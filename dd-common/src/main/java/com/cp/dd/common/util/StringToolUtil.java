package com.cp.dd.common.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chengp
 * @date 2019/9/26
 */
public class StringToolUtil {

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})
     */
    public static String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线,效率比上面高
     */
    public static String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * 集合转字符串，逗号分隔 (不带最后一个分隔符)
     *
     * @param list 集合
     * @return 字符串
     */
    public static String listToString(List<?> list) {
        return listToString(list, ",");
    }



    /**
     * 集合转字符串
     *
     * @param list      集合
     * @param separator 分割符
     * @return 字符串
     */
    public static String listToString(List<?> list, String separator) {
        if (list == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : list) {
            if (sb.length() > 0) {
                sb.append(separator);
            }
            sb.append(obj.toString());
        }
        return sb.toString();
    }

    /**
     * 数组转字符串，逗号分隔 (不带最后一个分隔符)
     *
     * @param array 数组
     * @return 字符串
     */
    public static String arrayToString(Object[] array) {
        if (array == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (Object obj : array) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(obj.toString());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String lineToHump = lineToHump("f_parent_no_leader");
        System.out.println(lineToHump);
        System.out.println(humpToLine(lineToHump));
        System.out.println(humpToLine2(lineToHump));
    }
}
