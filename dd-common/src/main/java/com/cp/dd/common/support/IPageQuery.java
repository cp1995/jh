package com.cp.dd.common.support;

import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 分页查询接口
 *
 * @author chengp
 */
public interface IPageQuery {

    /**
     * 获取每页记录数
     *
     * @return 每页记录数
     */
    Integer getSize();

    /**
     * 获取当前页
     *
     * @return 当前页
     */
    Integer getCurrent();

    /**
     * 获取mybatis-plus分页模型
     *
     * @return mybatis-plus分页模型
     */
    <T> IPage<T> loadPage();
}
