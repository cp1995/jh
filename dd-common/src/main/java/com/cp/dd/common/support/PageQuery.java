package com.cp.dd.common.support;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 分页模型
 *
 * @author chengp
 */
@Data
@ApiModel(description = "分页模型")
public class PageQuery implements IPageQuery, Serializable {

    @ApiModelProperty(value ="当前页(默认为1)")
    @NotNull(message = "当前页不为空")
    private Integer current = 1;

    @ApiModelProperty(value ="每页记录数(默认为10)")
    @NotNull(message = "每页记录数不为空")
    private Integer size = 10;

    @ApiIgnore
    @Override
    public <T> IPage<T> loadPage() {
        return new Page<T>(current, size);
    }
}
