package com.cp.dd.web.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cp.dd.web.controller.member.ApiConstant;
import com.cp.dd.web.controller.member.HttpRequest;
import com.cp.dd.web.controller.member.OauthResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * 微信接口
 * @author chengp
 */
@Slf4j
public class WechatApiUtils {
	/**
	 * 通过code换取网页授权access_token
	 */
	public static OauthResponse getAccessTokenByCode(String code, String appId, String appSecret){
		String url = ApiConstant.oauth2Api + "?appid="+ appId +"&secret="+ appSecret +"&code="+ code +"&grant_type=authorization_code";
		String responseStr = HttpRequest.sendPost(url, null);
		if(responseStr.indexOf("errcode") != -1){
			log.info("获取网页授权令牌-获取失败-错误信息：" + responseStr);
			return null;
		}else{
			OauthResponse vo = JSONObject.parseObject(responseStr, OauthResponse.class);
			log.info("获取网页授权令牌-获取成功-令牌信息：" + responseStr);
			return vo;
		}
	}
	/**
	 * 发送图文消息
	 * @param openid		关注者ID，发送的关注者必须在24小时内与公众号有互动，不然会响应失败
	 * @param originUrl		原文链接
	 * @param imgUrl        照片链接
	 * @param title			标题
	 * @param descrition	描述
	 */
	public static boolean sendNewsMessage(String accessToken, String openid, String originUrl, String imgUrl, String title, String descrition){
		String url = ApiConstant.sendMessageApi + "?access_token=" + accessToken;
		String body = "{\"touser\":\"" + openid + "\",\"msgtype\":\"news\",\"news\":{\"articles\": [{\"title\":\""+ title +"\",\"description\":\""+ descrition +"\",\"url\":\""+ originUrl +"\",\"picurl\":\""+ imgUrl +"\"}]}}";
		String responseStr = HttpRequest.sendPost(url, body);
		if(responseStr.indexOf("errcode") != -1){
			return false;
		}else{
			return true;
		}
	}

	/**
	 *  发送文本消息
	 * @param openid		关注者ID，发送的关注者必须在24小时内与公众号有互动，不然会响应失败
	 * @param content		内容
	 */
	public static boolean sendTextMessage(String accessToken, String openid, String content){
		String url = ApiConstant.sendMessageApi + "?access_token=" + accessToken;
		String body = "{\"touser\":\""+ openid + "\",\"msgtype\":\"text\",\"text\":{\"content\":\""+ content +"\"}}";
		String responseStr = HttpRequest.sendPost(url, body);
		if(responseStr.indexOf("\"errcode\":0") == -1){
			return false;
		}else{
			return true;
		}
	}
	/**
	 *  发送图片消息
	 * @param openid		关注者ID，发送的关注者必须在24小时内与公众号有互动，不然会响应失败
	 * @param MEDIA_ID 		内容
	 */
	public static boolean sendImgMessage(String accessToken, String openid, String MEDIA_ID){
		String url = ApiConstant.sendMessageApi + "?access_token=" + accessToken;
		String body = "{\"touser\":\""+ openid + "\",\"msgtype\":\"image\",\"image\":{\"media_id\":\""+ MEDIA_ID +"\"}}";
		String responseStr = HttpRequest.sendPost(url, body);
		if(responseStr.indexOf("\"errcode\":0") == -1){
			return false;
		}else{
			return true;
		}
	}

	/**
	 *
	 * 发送模板消息
	 *
	 * @param accessToken
	 *
	 * @param
	 */

	public static void sendTemplateMsg(String accessToken, String template_id, String openid, String jsonData) {
		String requestUrl = ApiConstant.send_templatemsg_url.replace("ACCESS_TOKEN",
				accessToken);
		String body =  "{\"touser\":\""+ openid +"\",\"template_id\":\""+ template_id +"\",\"url\":\"http://weixin.qq.com/download\",\"data\":\""+ jsonData +"\"}}";
		String responseStr = HttpRequest.sendPost(requestUrl, body);
		if (responseStr != null) {

			if(responseStr.indexOf("\"errcode\":0") != -1){
				System.out.println("发送模板消息成功！");
			} else {
				System.out.println("发送模板消息失败！");
			}

		}

	}


	/**
	 * 创建菜单
	 * @param menuJson	菜单JSON数据
	 */
	public static boolean createMenu(String accessToken, String menuJson){
		String url = ApiConstant.createMenuApi + "?access_token=" + accessToken;
		String body= "{\"button\":[{\"type\":\"click\",\"name\":\"当日自定义菜单\",\"key\":\"V1001_TODAY_MUSIC\"}]}";
		String responseStr = HttpRequest.sendPost(url, body);

		if(responseStr.indexOf("\"errcode\":0") != -1){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * 删除菜单
	 */
	public static boolean deleteMenu(String accessToken){
		String url = ApiConstant.deleteMenuApi + "?access_token=" + accessToken;
		String responseStr = HttpRequest.sendGet(url);
		if(responseStr.indexOf("\"errcode\":0") != -1){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * 获取公众号当前使用的菜单
	 * 1、开启开发者模式，则返回菜单的开发配置；
	 * 2、不开启开发者模式，返回公众平台配置的菜单
	 */
	public static String getMenuList(String accessToken){
		String url = ApiConstant.getMenuListApi + "?access_token=" + accessToken;
		String responseStr = HttpRequest.sendGet(url);
		return responseStr;
	}


	/**
	 * 获取自定义菜单（使用接口创建的菜单）和全部个性化菜单信息
	 */
	public static String getCustomMenuList(String accessToken){
		String url = ApiConstant.getCustomMenuList + "?access_token=" + accessToken;
		String responseStr = HttpRequest.sendGet(url);

		/**	转换成对象
		 JSONObject jsonObj = JSONObject.fromObject(responseStr);
		 CustomMenu menu = (CustomMenu) JSONObject.toBean(jsonObj, CustomMenu.class);
		 */
		return responseStr;
	}


	/**
	 *  上传永久照片
	 * @param picUrl  	照片地址（物理地址）
	 * @return mediaId	返回上传后的mediaId
	 */
	public static String addImageMaterial(String accessToken, String picUrl){
		String url = ApiConstant.addMaterialApi + "?access_token=" + accessToken + "&type=image";
		File file = new File(picUrl);
		String result = HttpRequest.uploadImage(url, file);
		return result;
	}

	/**
	 *  上传临时照片
	 * @param picUrl  	照片地址（物理地址）
	 * @return mediaId	返回上传后的mediaId
	 */
	public static String addTemporaryImageMaterial(String accessToken, String picUrl){
		String url = ApiConstant.addTemporaryApi + "?access_token=" + accessToken + "&type=image";
		File file = new File(picUrl);

		String result = HttpRequest.uploadTempImage(url, file);

		if(result.indexOf("上传失败") != -1){
		}else{
		}
		return result;
	}

	/**
	 * 新增永久图文素材（单图文）
	 * @param title			标题
	 * @param mediaId       图文消息的封面图片素材id（必须是永久mediaID）
	 * @param digest		图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
	 * @param content       图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
	 * @param sourceUrl     图文消息的原文地址，即点击“阅读原文”后的URL
	 * @param author        作者
	 * @param showCoverPic  是否显示封面，0为false，即不显示，1为true，即显示
	 */
	public static String addNewsMaterial(String accessToken, String title, String mediaId, String digest, String content, String sourceUrl, String author,int showCoverPic){
		String result = "";

		String url = ApiConstant.addNewsApi + "?access_token=" + accessToken;
		String body = "{\"articles\": [{\"title\":\"" + title + "\", \"thumb_media_id\": \""+ mediaId +"\",\"author\": \""+ author +"\",\"digest\": \""+ digest +"\",\"show_cover_pic\": "+ showCoverPic +",\"content\": \""+ content +"\",\"content_source_url\": \""+ sourceUrl +"\"}]}";

		String responseStr = HttpRequest.sendPost(url, body);
		if(responseStr.indexOf("errcode") != -1){
			result = "新增永久图文素材-新增失败-错误信息：" + responseStr;
		}else{
			JSONObject json = JSON.parseObject(responseStr);
			result = "新增永久图文素材-新增成功，素材ID：" + json.get("media_id").toString();
		}

		return result;
	}






	/**
	 * 获取永久素材的列表
	 * @param type     素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
	 * @param offset   从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
	 * @param count    返回素材的数量，取值在1到20之间
	 */
	public static String getMaterialList(String accessToken, String type, int offset, int count){
		String url = ApiConstant.materialListApi + "?access_token=" + accessToken;
		String json = "{\"type\":\"" + type + "\",\"offset\":"+ offset + ",\"count\":" + count + "}";

		String responseStr =  HttpRequest.sendPost(url, json);
		if(responseStr.indexOf("errcode") != -1){
			return null;
		}else{
			return responseStr;
		}
	}




}
