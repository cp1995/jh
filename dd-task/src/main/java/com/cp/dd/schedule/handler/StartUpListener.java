package com.cp.dd.schedule.handler;

import com.cp.dd.common.util.spring.SpringContextUtil;
import com.cp.dd.common.util.sys.SysConfigUtil;
import com.cp.dd.schedule.model.ITask;
import com.cp.dd.schedule.model.TaskBeanManager;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * 应用启动监听器
 *
 * @author chengp
 * @date 2019/10/15
 */
@Component
public class StartUpListener implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 同时集成了spring和springMVC的话，上下文中会存在父、子容器，该方法会执行两次
        // 控制只执行一次
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {

            // 从Spring容器中，获取定时任务类 实例集合
            // 初始化定时任务信息
            TaskBeanManager.setTaskMap(SpringContextUtil.getBeansOfType(ITask.class));

            // 系统配置初始化
        }
    }

}
