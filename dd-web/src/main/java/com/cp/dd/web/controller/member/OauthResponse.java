package com.cp.dd.web.controller.member;

import lombok.Data;

@Data
public class OauthResponse {
	
	//网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	private String access_token;
	
	//access_token接口调用凭证超时时间，单位（秒）
	private String expires_in;
	
	//用户刷新access_token
	private String refresh_token;
	
	//用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
	private String openid;
	
	//户授权的作用域，使用逗号（,）分隔
	private String scope;
	
	//只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
	private String unionid;


	
}
