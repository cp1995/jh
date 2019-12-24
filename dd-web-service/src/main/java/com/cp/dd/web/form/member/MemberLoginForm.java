package com.cp.dd.web.form.member;

import com.cp.dd.common.support.validator.custom.StatusRange;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * jwt登录参数
 *
 * @author chengp
 * @date 2019/9/20
 */
@Data
public class MemberLoginForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "账号/手机不能为空")
    @ApiModelProperty(value = "账号/手机", required = true)
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @NotNull(message = "类型不能为空")
    @ApiModelProperty(value = "类型（1个人用户，2团队用户）,小程序只需要设置1", required = true)
    private Integer type;

    @ApiModelProperty(value = "图片验证码", required = false)
    private String code;

    @ApiModelProperty(value = "图片验证码标识", required = false)
    private String sign;

    @NotNull(message = "登录来源不能为空")
    @ApiModelProperty(value = "登录来源1 web,2 admin,3 android,4 ios,5 mini")
    @StatusRange(values = {1, 2, 3, 4, 5}, message = "登录来源不正确")
    private Integer loginType;
}
