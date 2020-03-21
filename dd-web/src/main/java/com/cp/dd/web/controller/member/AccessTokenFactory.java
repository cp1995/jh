package com.cp.dd.web.controller.member;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.cp.dd.common.util.http.HttpRequest;


/**
 * 获取access_token
 * @author zhenjunzhuo
 */
public class AccessTokenFactory {
	
	private static AccessToken accessToken;
	//有效时间
	private static long expiresDate;
	

	private static long jsExpiresDate;
	
	/**
	 * 获取 access_token
	 */
	public static String getAccessToken(String appId, String appSecret) {
		String token = accessApi(appId, appSecret);
		return token;
	}
	
	
	public static String accessApi(String appId, String appSecret){
		String url =  ApiConstant.accessApi + "?grant_type=client_credential&appid="+ appId + "&secret=" + appSecret;
	
		String str = HttpRequest.sendGet(url);
		if(str.indexOf("errcode") != -1){
			return null;
		}else{
			JSONObject jsonObj = JSONObject.parseObject(str);
			String name = jsonObj.get("access_token").toString();
			return name;
		}
	}



	
}
