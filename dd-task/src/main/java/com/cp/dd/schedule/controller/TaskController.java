package com.cp.dd.schedule.controller;

import com.cp.dd.common.support.Result;
import com.cp.dd.common.util.spring.SpringContextUtil;
import com.cp.dd.schedule.model.TaskBean;
import com.cp.dd.schedule.model.TaskBeanManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 任务监控 控制器
 *
 * @author chengp
 * @date 2019/10/16
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    /**
     * 获取定时任务列表
     *
     * @return List<TaskBean>
     */
    @GetMapping("/list")
    public Result<Map<String,Object>> list() {
        Map<String,Object> map = new HashMap<>();
        map.put("records",TaskBeanManager.getTaskBeanList());
        return Result.success(map);
    }

    /**
     * 执行定时方法
     *
     * @param sign 标识
     * @return result
     */
    @PostMapping("/execute")
    public Result execute(String sign) {
        TaskBean taskBean = TaskBeanManager.getTaskBeanBySign(sign);
        if (taskBean == null) {
            return Result.fail("定时方法不存在");
        }
        Object task = SpringContextUtil.getBean(taskBean.getInstanceName());
        Method method;
        try {
            method = task.getClass().getMethod(taskBean.getMethodName());
            method.invoke(task);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return Result.fail("定时方法执行错误");
        }
        return Result.success();
    }

    /**
     * 关闭或开启 定时方法
     *
     * @param sign 标识
     * @return result
     */
    @PostMapping("/close")
    public Result close(String sign) {
        TaskBean taskBean = TaskBeanManager.getTaskBeanBySign(sign);
        if (taskBean == null) {
            return Result.fail("定时方法不存在");
        }

        taskBean.setClose(!taskBean.isClose());
        if (taskBean.isClose()) {
            taskBean.setNextExecuteTime(null);
        } else {
            TaskBeanManager.setNextExecuteTime(taskBean);
        }
        return Result.success();
    }
}
