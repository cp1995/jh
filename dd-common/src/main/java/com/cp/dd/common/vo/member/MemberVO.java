package com.cp.dd.common.vo.member;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * <p>
 * 会员信息VO
 * </p>
 *
 * @author chengp
 * @date 2019-09-30
 */
@Data
@ApiModel(value = "MemberVO对象", description = "会员信息")
public class MemberVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("token-header")
    private String Authorization;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户名/账号")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String headUrl;

    @ApiModelProperty(value = "证明材料")
    private String scienceUrl;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "类型（1个人用户，2团队用户）")
    private Integer type;

    @ApiModelProperty(value = "团队类型 1艺术团队 2其它")
    private Integer teamType;

    @ApiModelProperty(value = "状态 1:有效,0:无效 ")
    private Integer  enable;

    @ApiModelProperty(value = "卡类型(0 身份证)")
    private Integer cardType;

    @ApiModelProperty(value = "证件编码")
    private String idCard;

    @ApiModelProperty(value = "是否实名认证(0未实名1已实名)")
    private Integer realVerify;

    @ApiModelProperty(value = "姓名")
    private String name;
    
    @ApiModelProperty(value = "性别0男1女")
    private Integer sex;
    
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @ApiModelProperty(value = "出生日期")
    private LocalDate birthday;
    
    @ApiModelProperty(value = "学历 1本科及以上2大专3中专4高中5小学")
    private Integer education;
    
    @ApiModelProperty(value = "籍贯，精准到城市")
    private String nativePlace;

    @ApiModelProperty(value = "地址/现居城市，精准到区县")
    private String address;
 
    @ApiModelProperty(value = "注册时间")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "认证时间")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime  authenticationTime;

    @ApiModelProperty(value = "注册来源1 web,2 admin,3 android,4 ios,5 mini")
    private Integer source;
    
    @ApiModelProperty(value = "最近登陆时间")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime lastLoginTime;

}
