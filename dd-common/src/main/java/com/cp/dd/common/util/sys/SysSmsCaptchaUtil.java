package com.cp.dd.common.util.sys;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.cp.dd.common.constant.sys.SmsTemplateEnum;
import com.cp.dd.common.entity.sys.SysSms;
import com.cp.dd.common.entity.sys.SysSmsTemplate;
import com.cp.dd.common.mapper.sys.SysSmsMapper;
import com.cp.dd.common.mapper.sys.SysSmsTemplateMapper;
import com.cp.dd.common.vo.sys.SysSmsCaptchaVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 短信验证码工具类
 *
 * @author chengp
 * @date 2019/10/14
 */
@Slf4j
public class SysSmsCaptchaUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private SysSmsMapper sysSmsMapper;

    @Resource
    private SysSmsTemplateMapper sysSmsTemplateMapper;

    /**
     * 验证码失效时间300秒，即5分钟
     */
    private final static long CAPTCHA_TIMEOUT = 300L;

    /**
     * 再次请求间隔时间60秒，即1分钟
     */
    private final static long INTERVAL_TIME = 60L;

    private SysSmsCaptchaUtil() {
    }

    /**
     * 生成验证码
     *
     * @param phone 手机
     */
    public void generateCaptcha(String phone) {
        SysSmsCaptchaVO captchaVO = getSmsCaptchaVO(phone);
        if (captchaVO != null && captchaVO.getCreateTime().plusSeconds(INTERVAL_TIME).isAfter(LocalDateTime.now())) {
            throw new ApiException("再次请求间隔时间为60秒");
        }

        // 生成验证码
        final String code = RandomStringUtils.randomNumeric(4);

        // 保存验证短信
        SysSmsTemplate captchaSmsTemplate = sysSmsTemplateMapper.selectOne(
                Wrappers.<SysSmsTemplate>lambdaQuery().eq(SysSmsTemplate::getCode, SmsTemplateEnum.CAPTCHA.getCode()));
        SysSms sysSms = SysSms.newInstance(phone, MessageFormat.format(captchaSmsTemplate.getTemplate(), code), captchaSmsTemplate);
        sysSmsMapper.insert(sysSms);

        // 存入缓存
        SysSmsCaptchaVO captcha = new SysSmsCaptchaVO();
        captcha.setPhone(phone);
        captcha.setCode(code);
        captcha.setCreateTime(LocalDateTime.now());
        redisTemplate.opsForValue().set(phone, captcha, CAPTCHA_TIMEOUT, TimeUnit.SECONDS);
        log.debug("为手机【{}】生成验证码【{}】", phone, code);
    }

    /**
     * 根据手机获取验证码
     *
     * @param phone 手机
     * @return 暂时返回验证码
     */
    public String getCaptcha(String phone) {
        SysSmsCaptchaVO captchaVO = getSmsCaptchaVO(phone);
        if (captchaVO == null) {
            throw new ApiException("短信验证码不存在或已失效");
        } else {
            return captchaVO.getCode();
        }
    }

    /**
     * 获取存入redis中的短信验证码对象
     *
     * @param phone 手机
     * @return SysSmsCaptchaVO
     */
    private SysSmsCaptchaVO getSmsCaptchaVO(String phone) {
        Object obj = redisTemplate.opsForValue().get(phone);
        return obj == null ? null : (SysSmsCaptchaVO) obj;
    }

    /**
     * 校验短信验证码
     *
     * @param phone 手机
     * @param code  验证码
     * @return true：成功  false：失败
     */
    public boolean checkCode(String phone, String code) {
        final String captcha = getCaptcha(phone);
        boolean flag = code.equals(captcha);
        if (flag) {
            redisTemplate.delete(phone);
        }
        return flag;
    }
}
