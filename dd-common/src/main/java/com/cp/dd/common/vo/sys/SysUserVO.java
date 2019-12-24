package com.cp.dd.common.vo.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author chengp
 */
@Data
@ApiModel(description = "系统用户信息")
public class SysUserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("账号")
    private String userName;

    @ApiModelProperty("真名")
    private String realName;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机")
    private String phone;

    @ApiModelProperty("岗位")
    private String postName;

    @ApiModelProperty("生日")
    private LocalDateTime birthday;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("单位id")
    private Long deptId;

    @ApiModelProperty("创建单位")
    private Long createDept;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("是否为运营者")
    private Boolean isOperator;

    @ApiModelProperty("是否已删除")
    private Boolean isDeleted;

    @ApiModelProperty("token-header")
    private String Authorization;


}
