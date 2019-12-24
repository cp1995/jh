package com.cp.dd.common.entity.sys;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * <p>
 * 短信模板
 * </p>
 *
 * @author CodeGenerator
 * @date 2019-10-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SysSmsTemplate对象", description = "短信模板")
public class SysSmsTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "编号")
    private String code;

    /**
     * {@link com.eshore.zswh.common.constant.sys.SmsTemplateEnum}
     */
    @ApiModelProperty(value = "模板描述，见 SmsTemplateEnum")
    private String description;

    @ApiModelProperty(value = "短信模块内容（参数用{0},{1},{2}代替）")
    private String template;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /**
     * 获取填充参数之后的短信
     *
     * @param params 填充参数
     * @return 短信内容
     */
    public String getSmsTemplateMsg(Object... params) {
        return MessageFormat.format(this.template, params);
    }
}
