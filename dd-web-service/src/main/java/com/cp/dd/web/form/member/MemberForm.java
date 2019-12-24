package com.cp.dd.web.form.member;

import com.cp.dd.common.support.validator.custom.Mobile;
import com.cp.dd.common.support.validator.custom.StatusRange;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * jwt登录参数
 *
 * @author chengp
 * @date 2019/9/20
 */
@Data
public class MemberForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "nickname")
    @NotBlank(message = "昵称不能为空")
    private String nickname;
    
    @ApiModelProperty(value = "手机号", required = true)
    @Mobile
    @Pattern(regexp = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$", message = "手机格式错误")
    @NotBlank(message = "手机号不能为空")
    private String phone;
    
    @ApiModelProperty(value = "密码", required = true)
    @Pattern(regexp = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,16}$", message = "密码必须至少包含数字、大写字母、小写字母、特殊字符中的三种类型,且8~16位")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "头像")
    private String headUrl;
    
    @ApiModelProperty(value = "性别", required = true)
    @NotNull(message = "性别不能为空")
    private Integer sex;

    @ApiModelProperty(value = "用户类型(1个人用户 2团队用户)")
    private Integer type;
    
    @ApiModelProperty(value = "学历(1本科及以上2大专3中专4高中5小学)", required = true)
    @NotNull(message = "学历不能为空")
    private Integer education;
    
    @ApiModelProperty(value = "出生年月")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    
    @ApiModelProperty(value = "注册来源1 web,2 admin,3 android,4 ios,5 mini", required = true)
    @StatusRange(values = {1, 2, 3, 4, 5}, message = "注册来源不正确")
    @NotNull(message = "注册来源不能为空")
    private Integer source;

    @ApiModelProperty(value = "短信验证码(注册时必填)", required = false)
    private String code;
    
}
