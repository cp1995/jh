package com.cp.dd.common.support.custom;

import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Value;

/**
 * 自定义MybatisSqlSessionFactoryBean，继承{@link MybatisSqlSessionFactoryBean}
 * <p>
 * 修改 setPlugins 方法，控制多环境下使用性能分析插件的情况
 * <p>
 * 注意性能分析插件会有性能损耗，只用于开发环境，不建议生产环境使用
 *
 * @author chengp
 * @date 2019/10/15
 */
public class CustomMybatisSqlSessionFactoryBean extends MybatisSqlSessionFactoryBean {

    @Value("${mybatisplus.performance.enable}")
    private Boolean performanceEnable;

    @Override
    public void setPlugins(Interceptor[] plugins) {
        if (performanceEnable != null && performanceEnable) {
            // 如果需要开启性能分析插件
            plugins = addPerformanceInterceptor(plugins);
        }
        super.setPlugins(plugins);
    }

    /**
     * 添加性能分析插件
     *
     * @param plugins 插件数组
     */
    private Interceptor[] addPerformanceInterceptor(Interceptor[] plugins) {
        // 扩展插件数组
        int length = plugins == null ? 1 : plugins.length + 1;
        Interceptor[] newPlugins = new Interceptor[length];
        if (plugins != null) {
            System.arraycopy(plugins, 0, newPlugins, 0, plugins.length);
        }
        // 添加性能分析插件
        final PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        // SQL是否格式化 默认false
        performanceInterceptor.setFormat(true);
        newPlugins[length - 1] = performanceInterceptor;
        return newPlugins;
    }

}
