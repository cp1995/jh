package com.cp.dd.schedule.task;

import com.cp.dd.schedule.model.ITask;
import com.cp.dd.schedule.model.Task;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * solr相关定时任务
 *
 * @author chengp
 * @date 2019/10/18
 */
@AllArgsConstructor
@Component
public class TestTask implements ITask {



    @Task(desc = "测试定时器", time = "每5秒钟执行一次")
    @Scheduled(cron = "0/5 * * * * ?")
    public void solrFullUpdate() throws IOException {
        System.out.println("测试");
    }
}
