package com.cp.dd.web.handler;

import com.cp.dd.common.mapper.sys.SysConfigMapper;
import com.cp.dd.common.util.sys.CaptchaUtil;
import com.cp.dd.common.util.sys.SysConfigUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 应用启动监听器
 *
 * @author chengp
 * @date 2019/10/15
 */
@Component
public class StartUpListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private SysConfigMapper sysConfigMapper;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 同时集成了spring和springMVC的话，上下文中会存在父、子容器，该方法会执行两次
        // 控制只执行一次
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {

            // 系统配置初始化
            SysConfigUtil.init(redisTemplate, sysConfigMapper);

            // 验证码初始化
            CaptchaUtil.init(redisTemplate);
        }
    }

}
