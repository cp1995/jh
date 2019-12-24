package com.cp.dd.common.support;

import com.cp.dd.common.util.StringToolUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

/**
 * 分页模型
 *
 * @author chengp
 */
@ApiModel(description = "分页排序模型")
public class PageSortQuery extends PageQuery {

    @ApiModelProperty("排序字段（为空则不排序）")
    private String orderField;

    @ApiModelProperty(value = "是否升序排序（默认是true）", name = "isAsc")
    private Boolean isAsc;

    public String getOrderField() {
        // 转换为下划线 （如 evaluateNum -> evaluate_num）
        return StringUtils.isNotBlank(orderField) ? StringToolUtil.humpToLine2(orderField) : orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public Boolean getIsAsc() {
        // 默认升序查询
        return isAsc == null ? true : isAsc;
    }

    public void setIsAsc(Boolean isAsc) {
        this.isAsc = isAsc;
    }
}
