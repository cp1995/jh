package com.cp.dd.common.support;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分页结果模型
 *
 * @author chengp
 */
@Data
public class PageModel<T> {

    @ApiModelProperty("当前页")
    private long current;
    @ApiModelProperty("每页记录数")
    private long size;
    @ApiModelProperty("页数")
    private long pages;
    @ApiModelProperty("总记录数")
    private long total;
    @ApiModelProperty("分页记录列表")
    private List<T> records;

    public PageModel(IPage<T> page) {
        this.current = page.getCurrent();
        this.size = page.getSize();
        this.pages = page.getPages();
        this.total = page.getTotal();
        this.records = page.getRecords();
    }
    public PageModel(){}
}
