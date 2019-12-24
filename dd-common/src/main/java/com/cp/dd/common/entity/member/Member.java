package com.cp.dd.common.entity.member;

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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 会员信息
 * </p>
 *
 * @author CodeGenerator
 * @date 2019-10-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Member对象", description = "会员信息")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称/团队名称")
    private String nickname;

    @ApiModelProperty(value = "手机")
    private String phone;

    @ApiModelProperty(value = "负责人联系方式")
    private String chargePhone;

    @ApiModelProperty(value = "类型（1个人用户，2团队用户）")
    private Integer type;

    @ApiModelProperty(value = "团队类型 1艺术团队 2其它")
    private Integer teamType;

    @ApiModelProperty(value = "卡类型(0 身份证)")
    private Integer cardType;

    @ApiModelProperty(value = "证件编码/负责人身份证")
    private String idCard;

    @ApiModelProperty(value = "籍贯，精准到城市")
    private String nativePlace;

    @ApiModelProperty(value = "地址，精准到区县")
    private String address;

    @ApiModelProperty(value = "学历 1本科及以上2大专3中专4高中5小学")
    private Integer education;

    @ApiModelProperty(value = "出生日期")
    private LocalDate birthday;

    @ApiModelProperty(value = "性别0男1女")
    private Integer sex;

    @ApiModelProperty(value = "头像")
    private String headUrl;

    @ApiModelProperty(value = "证明材料")
    private String scienceUrl;

    @ApiModelProperty(value = "是否实名认证(0未实名1已实名)")
    private Integer realVerify;

    @ApiModelProperty(value = "注册来源1 web,2 admin,3 android,4 ios,5 mini")
    private Integer source;

    @ApiModelProperty(value = "审核状态 1待审核，2 已审核，3审核不通过")
    private Integer auditStatus;

    @ApiModelProperty(value = "1:有效,0:无效 本期不可配置，默认全部有效，后续业务扩展用")
    private Integer  enable;

    @ApiModelProperty(value = "审核意见")
    private String auditDesc;

    @ApiModelProperty(value = "创建时间/提交时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "认证时间")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime authenticationTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime auditTime;

    @ApiModelProperty(value = "审核人")
    private String auditBy;

    @ApiModelProperty(value = "最近登陆时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "姓名/负责人姓名")
    private String name;

    @ApiModelProperty(value = "微信用户唯一标识")
    private String unionid;


}
