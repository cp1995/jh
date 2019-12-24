package com.cp.dd.web.controller.member;

import com.cp.dd.common.annotation.IgnoreLogin;
import com.cp.dd.common.support.Result;
import com.cp.dd.common.util.sys.CaptchaUtil;
import com.cp.dd.common.vo.member.MemberVO;
import com.cp.dd.web.form.member.MemberForm;
import com.cp.dd.web.form.member.MemberLoginForm;
import com.cp.dd.web.service.member.IMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 登录接口
 *
 * @author chengp
 * @date 2019-09-23
 */
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/api/member")
@Api(value = "/member", tags = "登录接口")
public class LoginController {

    private IMemberService memberService;

    @IgnoreLogin
    @GetMapping("/captcha")
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    public void captcha(@RequestParam @NotBlank(message = "标识不能为空") @ApiParam(value = "唯一标识", required = true) String sign,
                        HttpServletResponse response) {
        //获取图片验证码
        CaptchaUtil.outCaptcha(sign, response);
    }

    @IgnoreLogin
    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "通过账号密码登录")
    public Result<MemberVO> login(@Valid MemberLoginForm memberLoginForm) {
        MemberVO memberVO = memberService.login(memberLoginForm);
        return Result.success(memberVO);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "注销", notes = "注销")
    public Result logout(HttpServletRequest request) {
        memberService.logout(request);
        return Result.success();
    }
    @PostMapping("/save")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public Result<MemberVO> save(@Valid MemberForm memberForm) {
        return Result.success(memberService.save(memberForm));
    }


    

}
