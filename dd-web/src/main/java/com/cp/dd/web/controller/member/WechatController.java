

package com.cp.dd.web.controller.member;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cp.dd.common.util.http.HttpRequest;
import com.cp.dd.web.config.WechatApiUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 *  微信公众号 前端控制器
 * </p>
 * @author chengp
 * @Date 2020-02-18
 * */


@RestController
@Api(tags = "微信公众号")
@RequestMapping("/api/wx/wechat")
@Slf4j
public class WechatController   {

      private String appId="wx8e90d895d4778abe";
      private String appSecret = "1ea928d369a97ffa7cf3bd03f0653302";
       /*private String appId="wxda4646e0a611f32c";
       private String appSecret = "94781ea7232d7048207725b2aab69748";*/

    /*private String appId="wx3f8eac91e40fc0ee";
    private String appSecret = "94781ea7232d7048207725b2aab69748";*/


    //服务器域名
    private String domain;




    @ApiOperation(value = "网页授权", notes = "网页授权")
    @GetMapping(value = "/auth")
    public void auth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String redirectUrl = domain ;
        //指定重定向
       // String wxredirect = request.getParameter("wxredt");
      //  if(StringUtils.isNotBlank(wxredirect)){
            redirectUrl = "http://d42d74.natappfree.cc/dd-web" + "/api/wx/wechat/callBack";
      //  }
        String scope = "snsapi_base";
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = ApiConstant.authApi + "?appid=" + appId + "&redirect_uri=" + redirectUrl + "&response_type=code&scope=" + scope + "&state="+ scope +"#wechat_redirect";
        log.info("微信公众号重定向地址:"+url);
        response.sendRedirect(url);
    }

    @GetMapping(value = "/callBack")
    public  void  callBack(HttpServletRequest req, HttpServletResponse resp){
        String code = req.getParameter("code");
        OauthResponse or =  WechatApiUtils.getAccessTokenByCode(code, appId, appSecret);
        if(or == null){
            System.out.println("获取openid失败");
        }
        String openid = or.getOpenid();
        System.out.println("openid=="+openid);

    }

    @GetMapping(value = "/createMune2")
    public void createMune2 (HttpServletRequest request) throws UnsupportedEncodingException {
        List<GghMenu> gghMenu = new ArrayList<>();
        String redirectUrl = "http://www.meililianzhou.cn/mlxc/epidemic/control";
        redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
        String url ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+redirectUrl+"&response_type=code&scope=snsapi_base&state=d91313cb79974718bdee31cfbce05806#wechat_redirect";

        String xjredirectUrl="http://www.meililianzhou.cn/mlxc/villageAppearance";
        xjredirectUrl = URLEncoder.encode(xjredirectUrl, "UTF-8");
        String xjurl ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+xjredirectUrl+"&response_type=code&scope=snsapi_base&state=d91313cb79974718bdee31cfbce05806#wechat_redirect";
        GghMenu  aa = new  GghMenu(Long.valueOf("1"),"美丽乡村",xjurl,3);
        String gredirectUrl="http://www.meililianzhou.cn/mlxc/personalNew";
        gredirectUrl = URLEncoder.encode(gredirectUrl, "UTF-8");
        String gurl ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+gredirectUrl+"&response_type=code&scope=snsapi_base&state=d91313cb79974718bdee31cfbce05806#wechat_redirect";
        GghMenu  bb = new  GghMenu(Long.valueOf("1"),"个人中心",gurl,3);
        gghMenu.add(aa);
        gghMenu.add(bb);
        JSONArray sub_button1=new JSONArray(); //
        JSONArray sub_button2=new JSONArray();
        JSONArray sub_button3=new JSONArray();
        if(gghMenu.size()>0) {
            for(int i=0;i<gghMenu.size();i++) {
                    ViewButton zx=new ViewButton();
                     zx.setUrl(gghMenu.get(i).getUrl());
                    //zx.setMedia_id("Xm5_ZWk5Artct5xbrQhqiq9HQmEuKfzwruBxC5eOk8o");
                     zx.setName(gghMenu.get(i).getName());
                     zx.setType("view");
                    if(gghMenu.get(i).getParent()==1) {
                        sub_button1.add(zx);
                    }else if(gghMenu.get(i).getParent()==2) {
                        sub_button2.add(zx);
                    }else {
                        sub_button3.add(zx);
                    }
            }
        }

        JSONObject buttonOne=new JSONObject();
        buttonOne.put("name", "乡村大喇叭");
        buttonOne.put("type", "view");
        buttonOne.put("url", "http://liukeshen.cn");
        buttonOne.put("sub_button", sub_button1);
        JSONObject buttonOne2=new JSONObject();
        buttonOne2.put("name", "疫情防控");
        buttonOne2.put("type", "view");
        buttonOne2.put("url", url);
      //  buttonOne2.put("sub_button", sub_button2);
        JSONObject buttonOne3=new JSONObject();
        buttonOne3.put("name", "魅力乡村");
        buttonOne3.put("sub_button", sub_button3);

        JSONArray button=new JSONArray();
        button.add(buttonOne);
        button.add(buttonOne2);
        button.add(buttonOne3);
        JSONObject menujson=new JSONObject();
        menujson.put("button", button);
        System.out.println(menujson);
        //这里为请求接口的url   +号后面的是token，这里就不做过多对token获取的方法解释
        String token = AccessTokenFactory.getAccessToken(appId, appSecret);
        String urls = ApiConstant.createMenuApi + "?access_token=" + token;
        try{
            String responseStr = HttpRequest.sendPost(urls, menujson.toString());
            System.out.println(responseStr);
        }catch(Exception e){
            System.out.println("请求错误！");
        }
    }


    @GetMapping(value = "/wechatCreateMune")
    public void createMune (HttpServletRequest request) throws UnsupportedEncodingException {
        List<GghMenu> gghMenu = new ArrayList<>();
        String redirectUrl = "http://www.meililianzhou.cn/mlxc/";
        redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
        String url ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+redirectUrl+"&response_type=code&scope=snsapi_base&state=e10adc3949ba59abbe56e057f20f883e#wechat_redirect";
        GghMenu  aa = new  GghMenu(Long.valueOf("1"),"乡村电商",url,3);
        String xjredirectUrl="http://www.meililianzhou.cn/mlxc/agriculture/search";
        String xjurl ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+xjredirectUrl+"&response_type=code&scope=snsapi_base&state=e10adc3949ba59abbe56e057f20f883e#wechat_redirect";
        GghMenu  bb = new  GghMenu(Long.valueOf("1"),"休闲农业",xjurl,3);
        String gredirectUrl="http://www.meililianzhou.cn/mlxc/video";
        String gurl ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+gredirectUrl+"&response_type=code&scope=snsapi_base&state=e10adc3949ba59abbe56e057f20f883e#wechat_redirect";
        GghMenu  cc = new  GghMenu(Long.valueOf("1"),"5G视频矩阵",gurl,3);
        String ddredirectUrl="http://www.meililianzhou.cn/mlxc/villageNewsInfoList";
        String ddurl ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+ddredirectUrl+"&response_type=code&scope=snsapi_base&state=e10adc3949ba59abbe56e057f20f883e#wechat_redirect";
        GghMenu  dd = new  GghMenu(Long.valueOf("1"),"资讯",ddurl,3);
        String eeedirectUrl="http://www.meililianzhou.cn/mlxc/epidemic/control";
        String eeurl ="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+eeedirectUrl+"&response_type=code&scope=snsapi_base&state=e10adc3949ba59abbe56e057f20f883e#wechat_redirect";
        GghMenu  ee = new  GghMenu(Long.valueOf("1"),"疫情防控",eeurl,3);
        GghMenu  ff = new  GghMenu(Long.valueOf("1"),"水晶梨","http://120.78.10.25:39999",1);
        gghMenu.add(aa);
        gghMenu.add(bb);
        gghMenu.add(cc);
        gghMenu.add(dd);
        gghMenu.add(ee);
        gghMenu.add(ff);
        JSONArray sub_button1=new JSONArray(); //
        JSONArray sub_button2=new JSONArray();
        JSONArray sub_button3=new JSONArray();
        if(gghMenu.size()>0) {
            for(int i=0;i<gghMenu.size();i++) {
                ViewButton zx=new ViewButton();
                //zx.setUrl(gghMenu.get(i).getUrl());
                //zx.setMedia_id("Xm5_ZWk5Artct5xbrQhqiq9HQmEuKfzwruBxC5eOk8o");
                // zx.setName(gghMenu.get(i).getName());
                // zx.setType("view");

                if(gghMenu.get(i).getParent()==1) {
                    zx.setName(gghMenu.get(i).getName());
                    zx.setMedia_id("Xm5_ZWk5Artct5xbrQhqitf0TUWXtpFbqRW9rVK5sOI");
                    zx.setType("view_limited");
                    sub_button1.add(zx);
                }else if(gghMenu.get(i).getParent()==2) {
                    sub_button2.add(zx);
                }else {
                    zx.setName(gghMenu.get(i).getName());
                    zx.setUrl(gghMenu.get(i).getUrl());
                    zx.setType("view");
                    sub_button3.add(zx);
                }
            }
        }

        JSONObject buttonOne=new JSONObject();
        buttonOne.put("name", "一村一品");
        buttonOne.put("type", "view");
        // buttonOne.put("type", "miniprogram");
        // buttonOne.put("url", "http://liukeshen.cn");
        //  buttonOne.put("pagepath", "pages/index/index?page_id=50");
        // buttonOne.put("appid", "wx76fec2a7cc377ef2");
        buttonOne.put("sub_button", sub_button1);
        JSONObject buttonOne2=new JSONObject();
        buttonOne2.put("name", "乡村党建");
        buttonOne2.put("type", "view");
        buttonOne2.put("url", url);
        //  buttonOne2.put("sub_button", sub_button2);
        JSONObject buttonOne3=new JSONObject();
        buttonOne3.put("name", "美丽乡村");
        buttonOne3.put("sub_button", sub_button3);

        JSONArray button=new JSONArray();
        button.add(buttonOne);
        button.add(buttonOne2);
        button.add(buttonOne3);
        JSONObject menujson=new JSONObject();
        menujson.put("button", button);
        System.out.println(menujson);
        //这里为请求接口的url   +号后面的是token，这里就不做过多对token获取的方法解释
        String token = AccessTokenFactory.getAccessToken(appId, appSecret);
        String urls = ApiConstant.createMenuApi + "?access_token=" + token;
        try{
            String responseStr = HttpRequest.sendPost(urls, menujson.toString());
            System.out.println(responseStr);
        }catch(Exception e){
            System.out.println("请求错误！");
        }
    }

    @GetMapping(value = "/addpic")
    public  void  addpic(){
        String picUrl ="f:\\3.jpg";
        String token = AccessTokenFactory.getAccessToken(appId, appSecret);
        String result = WechatApiUtils.addImageMaterial(token, picUrl);
        System.out.println(result);
    }

    @GetMapping(value = "/addTp")
    public void addTp(){
        String title = "水晶梨";										 //标题
        String mediaId = "Xm5_ZWk5Artct5xbrQhqirmU30HC_c8ZX_an_wOL2oQ";  //图文消息的封面图片素材id（必须是永久mediaID）
        String digest = "水晶梨";											 //图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
        String content = "&nbsp;&nbsp;水晶梨果实为圆球形或扁圆形。平均单果质量385克．最大560克。果皮近成熟时乳黄色，表面晶莹光亮，有透明感，外观诱人。果肉白色，肉质细腻，致密嫩脆，汁液多，可溶性固形物含量14%，石细胞极少。果心小，味蜜甜，香味浓郁，品质特优。" +
                "<br/> 果实耐贮运，货架寿命长。在西安气候条件下10月上中旬成熟。该品种抗寒、抗旱、基本无黑星病、炭疽病、轮纹病发生。极耐贮藏，在自然条件下可贮存5个月。属梨中珍品，极具市场前景。冷藏可贮翌年2月份。自花结实力强，苗木定植后次年结果，三年株产12公斤。市场潜力大，是出口梨的首选品种";											 //图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
        String sourceUrl = "";				 //图文消息的原文地址，即点击“阅读原文”后的URL
        String author = "美丽乡村-老马村";										 //作者
        int showCoverPic = 1;											 //是否显示封面，0为false，即不显示，1为true，即显示
        String token = AccessTokenFactory.getAccessToken(appId, appSecret);
        String result = WechatApiUtils.addNewsMaterial(token, title, mediaId, digest, content, sourceUrl, author, showCoverPic);
        System.out.println(result);
    }



}


