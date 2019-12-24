package com.cp.dd.common.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 过滤敏感词表
 * </p>
 *
 * @author CodeGenerator
 * @date 2019-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SysFilterWords对象", description = "过滤敏感词表")
public class SysFilterWords implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "敏感词")
    private String word;

    @ApiModelProperty(value = "状态(0-无效,1-有效)")
    private Integer status;

    @ApiModelProperty(value = "创建用户id")
    private Long createUserId;

    @ApiModelProperty(value = "创建用户名")
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改用户id")
    private Long modifyUserId;

    @ApiModelProperty(value = "修改用户名")
    private String modifyUserName;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyTime;


}
