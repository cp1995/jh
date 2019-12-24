package com.cp.dd.common.util;

import java.util.Map;

public class NetUtil {
    public static String paramsNotNull(Map<String,Object> params) {
        String paramsName =null;
        if(params!=null) {
            for (Map.Entry<String, Object> m : params.entrySet()) {
                if(m.getValue()==null) {
                    paramsName="请求参数错误：缺少"+m.getKey();
                    break;
                }else {
                    if(m.getValue().getClass().getName().equals("java.lang.String")) {
                        if("".equals(m.getValue())) {
                            //如果是字符串，在判断是否是空字符串
                            paramsName="请求参数错误：缺少"+m.getKey();
                            break;
                        }
                    }
                }
            }
        }
        return paramsName;
    }
}
