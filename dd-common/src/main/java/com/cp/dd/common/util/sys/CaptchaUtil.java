package com.cp.dd.common.util.sys;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 系统验证码 服务实现类
 * <p>
 * 使用 https://github.com/whvcse/EasyCaptcha
 *
 * @author chengp
 * @date 2019-09-24
 */
public class CaptchaUtil {

    private static final String PREFIX = "CAPTCHA:";

    private static RedisTemplate redisTemplate;

    public static void init(RedisTemplate redisTemplate) {
        CaptchaUtil.redisTemplate = redisTemplate;
    }

    /**
     * 获取图片验证码
     *
     * @param sign 获取验证码的唯一标识
     */
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public static void outCaptcha(String sign, HttpServletResponse response) {
        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

       /*png类型: SpecCaptcha
         gif类型 GifCaptcha
        中文类型 ChineseCaptcha
        中文gif类型 ChineseGifCaptcha
        算术类型 ArithmeticCaptcha*/

        SpecCaptcha captcha = new SpecCaptcha(130, 48, 4);
        // 设置内置字体
        captcha.setFont(Captcha.FONT_2, 40f);

        // 获取验证码的字符
        String code = captcha.text().toLowerCase();

        // 存入redis, 过期时间15分钟
        redisTemplate.opsForValue().set(PREFIX + sign, code, 900L, TimeUnit.SECONDS);

        // 输出图片
        captcha.out(response.getOutputStream());
    }

    /**
     * 验证码效验
     *
     * @param sign sign
     * @param code 验证码
     * @return true：成功  false：失败
     */
    @SuppressWarnings("unchecked")
    public static boolean isNotValid(String sign, String code) {
        Object sourceCode = redisTemplate.opsForValue().get(PREFIX + sign);

        boolean result = sourceCode != null && code.equalsIgnoreCase(sourceCode.toString());
        if (result) {
            redisTemplate.delete(PREFIX + sign);
        }
        return !result;
    }
}
