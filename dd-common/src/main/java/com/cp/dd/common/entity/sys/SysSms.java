package com.cp.dd.common.entity.sys;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cp.dd.common.constant.sys.SysSmsStatusEnum;
import com.cp.dd.common.vo.sms.SendMessageResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 短信
 * </p>
 *
 * @author CodeGenerator
 * @date 2019-10-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SysSms对象", description = "短信")
public class SysSms implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "发送手机")
    private String mobile;

    @ApiModelProperty(value = "发送内容")
    private String msg;

    @ApiModelProperty(value = "发送状态（0-未发送，1-发送成功，2-发送失败）")
    private Integer status;

    @ApiModelProperty(value = "短信批号")
    private String msgId;

    @ApiModelProperty(value = "返回编码")
    private Integer code;

    @ApiModelProperty(value = "返回信息")
    private String info;

    @ApiModelProperty(value = "模板id")
    private Integer templateId;

    @ApiModelProperty(value = "模板描述")
    private String templateDesc;

    @ApiModelProperty(value = "发送时间")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "锁")
    private String lockId;

    /**
     * 获取一个新的短信对象
     *
     * @param mobile 手机
     * @param msg    短信内容
     * @return 新短信对象
     */
    public static SysSms newInstance(String mobile, String msg) {
        SysSms sysSms = new SysSms();
        sysSms.setMobile(mobile);
        sysSms.setMsg(msg);
        sysSms.setStatus(SysSmsStatusEnum.NOT.getStatus());
        sysSms.setCreateTime(LocalDateTime.now());
        return sysSms;
    }

    public static SysSms newInstance(String mobile, String msg, SysSmsTemplate template) {
        SysSms sysSms = newInstance(mobile, msg);
        sysSms.setTemplateId(template.getId());
        sysSms.setTemplateDesc(template.getDescription());
        return sysSms;
    }

    /**
     * 设置结果
     *
     * @param result 短信发送结果
     */
    public void setResult(SendMessageResult result) {
        setCode(result.getCode());
        setStatus(result.getCode() == 0 ? SysSmsStatusEnum.SUCCESS.getStatus() : SysSmsStatusEnum.FAIL.getStatus());
        setMsgId(result.getMsgId());
        setInfo(result.getInfo());
        setSendTime(LocalDateTime.now());
    }

}
