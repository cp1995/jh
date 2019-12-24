package com.cp.dd.common.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cp.dd.common.entity.sys.SysFilterWords;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * 过滤敏感词表 Mapper 接口
 * </p>
 *
 * @author chengp
 * @date 2019-10-10
 */
public interface SysFilterWordsMapper extends BaseMapper<SysFilterWords> {
    List<SysFilterWords> selectAllByStatus(@Param("status") int status);
}
