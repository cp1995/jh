package com.cp.dd.common.support.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * Request的包装类
 * 用于修改Request请求，这是拦截器Interceptor所不能做到的
 *
 * @author chengp
 * @date 2019/12/4
 */
public class XssRequestWrapper extends HttpServletRequestWrapper {

    XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * 对数组参数进行特殊字符过滤
     *
     * @param name 参数名
     * @return java.lang.String[]
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) {
            return null;
        }
        for (int i = 0; i < values.length; i++) {
            values[i] = clearXss(values[i]);
        }
        return values;
    }

    /**
     * 对参数中特殊字符进行过滤
     *
     * @param name 参数名
     * @return java.lang.String
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return value == null ? null : clearXss(value);
    }

    /**
     * 覆盖getParameterMap方法，将参数名和参数值都做xss & sql过滤
     * 【一般post表单请求，或者前台接收为实体需要这样处理】
     *
     * @return java.util.Map
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map getParameterMap() {
        Map<String, Object> paramMap = new HashMap<>(16);
        Map<String, String[]> requestMap = super.getParameterMap();
        for (Map.Entry<String, String[]> entry : requestMap.entrySet()) {
            String[] values = entry.getValue();
            for (int i = 0; i < values.length; i++) {
                values[i] = clearXss(values[i]);
            }
            paramMap.put(xssEncode(entry.getKey()), values);
        }
        return paramMap;
    }


    /**
     * 获取attribute, 特殊字符过滤
     *
     * @param name 参数名
     * @return java.lang.Object
     */
    @Override
    public Object getAttribute(String name) {
        Object value = super.getAttribute(name);
        if (value instanceof String) {
            clearXss((String) value);
        }
        return value;
    }

    /**
     * 对请求头部进行特殊字符过滤
     *
     * @param name 参数名
     * @return java.lang.String
     */
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return value == null ? null : clearXss(value);
    }

    /**
     * 特殊字符处理（转义或删除）
     *
     * @param value 值
     * @return java.lang.String
     */
    private String clearXss(String value) {
        if (StringUtils.isEmpty(value)) {
            return value;
        }

        // 字符编码不一致，需要转换。我们系统是UTF-8编码，这里不需要
        /*try {
            value = new String(value.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }*/

        return XssFilterUtil.stripXss(value);
    }


    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符 在保证不删除数据的情况下保存
     *
     * @param value 值
     * @return 过滤后的值
     */
    private static String xssEncode(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("<", "&lt;");
        value = value.replaceAll(">", "&gt;");
        value = value.replaceAll("'", "&apos;");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
        value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
        value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
        value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
        // value = value.replaceAll("[<>{}\\[\\];\\&]","");
        return value;
    }
}
