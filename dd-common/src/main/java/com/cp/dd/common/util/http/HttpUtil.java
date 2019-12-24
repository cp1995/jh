package com.cp.dd.common.util.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Http 请求工具类
 *
 * @author chengp
 * @date 2019/10/25
 */
@Slf4j
public class HttpUtil {

    /**
     * 定义默认编码格式 UTF-8
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final int CONNECTION_TIME_OUT = 2000;
    private static final int SOCKET_TIME_OUT = 2000;
    private static final int MAX_CONNECTION_PER_HOST = 20;
    private static final int MAX_TOTAL_CONNECTIONS = 20;

    private static HttpClient client;

    static {
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(CONNECTION_TIME_OUT);
        connectionManager.getParams().setSoTimeout(SOCKET_TIME_OUT);
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(MAX_CONNECTION_PER_HOST);
        connectionManager.getParams().setMaxTotalConnections(MAX_TOTAL_CONNECTIONS);
        client = new HttpClient(connectionManager);
    }

    /**
     * POST方式提交数据, 默认编码UTF-8
     *
     * @param url    待请求的URL
     * @param params 要提交的数据
     * @return 响应结果
     */
    public static String post(String url, Map<String, Object> params) {
        return post(url, params, DEFAULT_CHARSET);
    }

    /**
     * POST方式提交数据
     *
     * @param url    待请求的URL
     * @param params 要提交的数据
     * @param enc    编码
     * @return 响应结果
     */
    public static String post(String url, Map<String, Object> params, String enc) {
        String response = null;
        PostMethod postMethod = new PostMethod(url);
        postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);

        //将表单的值放入postMethod中
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            postMethod.addParameter(entry.getKey(), String.valueOf(entry.getValue()));
        }

        try {
            //执行postMethod
            if (client.executeMethod(postMethod) == HttpStatus.SC_OK) {
                response = postMethod.getResponseBodyAsString();
            } else {
                log.error("Http Post 请求失败，url={},  params={}, statusCode={}", url, params, postMethod.getStatusCode());
            }
        } catch (IOException e) {
            log.error("Http Post 请求失败: url={}, params={}", url, params);
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }

        return response;
    }

    /**
     * GET方式提交数据，默认编码UTF-8
     *
     * @param url    待请求的URL
     * @param params 要提交的数据
     * @return 响应结果
     */
    public static String get(String url, Map<String, Object> params) {
        return get(url, params, DEFAULT_CHARSET);
    }

    /**
     * GET方式提交数据
     *
     * @param url    待请求的URL
     * @param params 要提交的数据
     * @param enc    编码
     * @return 响应结果
     */
    public static String get(String url, Map<String, Object> params, String enc) {
        String response = null;
        url = getUrl(url, params, enc);

        GetMethod getMethod = new GetMethod(url);
        getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);

        try {
            //执行getMethod
            if (client.executeMethod(getMethod) == HttpStatus.SC_OK) {
                response = getMethod.getResponseBodyAsString();
            } else {
                log.error("Http Get 请求失败，url={}, statusCode={}", url, getMethod.getStatusCode());
            }
        } catch (IOException e) {
            log.error("Http Get 请求失败，url={}", url);
            e.printStackTrace();
        } finally {
            getMethod.releaseConnection();
        }
        return response;
    }

    /**
     * 据Map生成URL字符串
     *
     * @param url    原url
     * @param params Map
     * @param enc    URL编码
     * @return URL
     */
    private static String getUrl(String url, Map<String, Object> params, String enc) {
        if (params == null || params.isEmpty()) {
            return url;
        }

        StringBuilder destUrl = new StringBuilder(url);
        destUrl.append(!url.contains("?") ? "?" : "&");
        // 拼接参数
        params.forEach((key, value) -> {
            try {
                destUrl.append(key).append("=").append(URLEncoder.encode(String.valueOf(value), enc)).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });

        destUrl.deleteCharAt(destUrl.length() - 1);
        return destUrl.toString();
    }


    public static void main(String[] args) {
        String url = "http://smsapi.right100.cn:9801/CASServer/SmsAPI2/SendMessage.jsp";
        // String url = "http://smsapi.right100.cn:9801/CASServer/SmsAPI2/ReceiveMessage.jsp";

        Map<String, Object> params = new HashMap<>(16);
        params.put("userid", "70283");
        params.put("password", "gdzs:10-25");
        params.put("destnumbers", "18826135697");
        params.put("msg", "您的验证码是XXXX，在5分钟内有效。如非本人操作，请忽略本短信。");

        // System.out.println(get(url, params));
        System.out.println(post(url, params));
    }
}
