package com.cp.dd.common.constant;

/**
 *
 *<p>
 *微信api
 *<p>
 * @author chnegp
 * @date 2019年11月6日
 */
public class ApiConstant {
    //通过code换取网页授权access_token
    public final static String oauth2Api = "https://api.weixin.qq.com/sns/oauth2/access_token";
    //用户信息
    public final static String userInfoApi = "https://api.weixin.qq.com/sns/userinfo";
    //刷新refresh_token
    public final static String refreshTokenApi = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
    //检验授权凭证（access_token）是否有效
    public final static String  authApi = "https://api.weixin.qq.com/sns/auth";
    //获取access_token
    public final static String accessApi = "https://api.weixin.qq.com/cgi-bin/token";
    //获取小程序二维码
    public final static String miniQrCodeApi = "https://api.weixin.qq.com/wxa/getwxacodeunlimit";


}

