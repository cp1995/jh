package com.cp.dd.common.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * <p>
 * 系统配置
 * </p>
 *
 * @author CodeGenerator
 * @date 2019-09-30
 */
@Data
public final class SysConfig {

    /**
     * 键
     */
    @TableId(value = "name", type = IdType.INPUT)
    private String name;

    /**
     * 值
     */
    private String value;
}
