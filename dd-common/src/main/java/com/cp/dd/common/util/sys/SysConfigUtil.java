package com.cp.dd.common.util.sys;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cp.dd.common.constant.sys.SysConfigEnum;
import com.cp.dd.common.entity.sys.SysConfig;
import com.cp.dd.common.mapper.sys.SysConfigMapper;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 系统配置工具类
 *
 * @author chengp
 * @date 2019/11/21
 */
public class SysConfigUtil {

    private static RedisTemplate redisTemplate;
    private static SysConfigMapper sysConfigMapper;

    /**
     * 配置信息
     */
    private static Map<String, String> map = new HashMap<>();

    private static final String SYS_CONFIG_KEY = "SYS_CONFIG_MAP";

    private SysConfigUtil() {
    }

    /**
     * 初始化
     */
    @SuppressWarnings("unchecked")
    public static void init(RedisTemplate redisTemplate, SysConfigMapper sysConfigMapper) {
        SysConfigUtil.sysConfigMapper = sysConfigMapper;
        SysConfigUtil.redisTemplate = redisTemplate;

        final List<SysConfig> sysConfigList = sysConfigMapper.selectList(Wrappers.query());
        for (SysConfig sysConfig : sysConfigList) {
            map.put(sysConfig.getName(), sysConfig.getValue());
        }

        if (Objects.nonNull(redisTemplate)) {
            redisTemplate.opsForValue().set(SYS_CONFIG_KEY, map);
        }
    }

    /**
     * 获取系统配置表
     *
     * @return 系统配置
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getMap() {
        if (Objects.isNull(redisTemplate)) {
            return map;
        }


        final Object obj = redisTemplate.opsForValue().get(SYS_CONFIG_KEY);
        if (Objects.nonNull(obj) && obj instanceof Map) {
            map = (Map<String, String>) obj;
        }
        return map;
    }

    /**
     * 修改系统配置
     *
     * @param name  配置名
     * @param value 配置值
     */
    @SuppressWarnings("unchecked")
    public static void put(SysConfigEnum name, Object value) {
        final SysConfig sysConfig = sysConfigMapper.selectById(name);
        if (Objects.nonNull(sysConfig)) {
            sysConfig.setValue(value.toString());
            sysConfigMapper.updateById(sysConfig);
            map.put(sysConfig.getName(), sysConfig.getValue());

            if (Objects.nonNull(redisTemplate)) {
                redisTemplate.opsForValue().set(SYS_CONFIG_KEY, map);
            }
        }
    }

    public static String getString(SysConfigEnum name) {
        return getMap().get(name.name());
    }

    public static int getInt(SysConfigEnum name) {
        return Integer.parseInt(getMap().get(name.name()));
    }

    public static long getLong(SysConfigEnum name) {
        return Long.parseLong(getMap().get(name.name()));
    }

    public static boolean getBoolean(SysConfigEnum name) {
        return "true".equalsIgnoreCase(getMap().get(name.name()));
    }
}
