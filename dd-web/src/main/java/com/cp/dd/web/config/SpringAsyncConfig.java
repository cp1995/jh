package com.cp.dd.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 开启 Spring 异步调用
 *
 * @author chengp
 */
@Configuration
@EnableAsync
public class SpringAsyncConfig {

    private static final int MAX_POOL_SIZE = 20;

    private static final int CORE_POOL_SIZE = 10;

    /**
     * 线程池配置
     *
     * @return AsyncTaskExecutor
     */
    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        taskExecutor.setThreadNamePrefix("async-task-thread-pool");
        taskExecutor.initialize();
        return taskExecutor;
    }
}
