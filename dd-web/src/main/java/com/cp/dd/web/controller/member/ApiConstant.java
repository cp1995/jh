package com.cp.dd.web.controller.member;

public class ApiConstant {
	//网页授权
	public final static String authApi = "https://open.weixin.qq.com/connect/oauth2/authorize";
	//通过code换取网页授权access_token
	public final static String oauth2Api = "https://api.weixin.qq.com/sns/oauth2/access_token";
	
	//获取access_token
	public final static String accessApi = "https://api.weixin.qq.com/cgi-bin/token";
	//获取jsapi ticket
	public final static String jsapiTicketApi = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	
	
	//关注者列表
	public final static String userListApi = "https://api.weixin.qq.com/cgi-bin/user/get";
	//用户信息
	public final static String userInfoApi = "https://api.weixin.qq.com/sns/userinfo";
	//发送信息
	public final static String sendMessageApi = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
	
	//发送模板消息
	public final static String send_templatemsg_url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
	
	//增加永久图文素材
	public final static String addNewsApi = "https://api.weixin.qq.com/cgi-bin/material/add_news";
	//上传永久照片
	public final static String addMaterialApi = "https://api.weixin.qq.com/cgi-bin/material/add_material";
	//获取永久素材的列表
	public final static String materialListApi = "https://api.weixin.qq.com/cgi-bin/material/batchget_material";
	
	//新增临时素材
	public final static String addTemporaryApi ="https://api.weixin.qq.com/cgi-bin/media/upload";
	
	//创建菜单
	public final static String createMenuApi = "https://api.weixin.qq.com/cgi-bin/menu/create";
	//删除菜单
	public final static String deleteMenuApi = "https://api.weixin.qq.com/cgi-bin/menu/delete";
	//获取公众号当前使用菜单
	public final static String getMenuListApi = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info";
	//获取自定义菜单
	public final static String getCustomMenuList = "https://api.weixin.qq.com/cgi-bin/menu/get";
	

	
}
