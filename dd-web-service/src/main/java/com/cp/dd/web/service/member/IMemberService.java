package com.cp.dd.web.service.member;



import com.baomidou.mybatisplus.extension.service.IService;
import com.cp.dd.common.entity.member.Member;
import com.cp.dd.common.vo.member.MemberVO;
import com.cp.dd.web.form.member.MemberForm;
import com.cp.dd.web.form.member.MemberLoginForm;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员信息 服务类
 * </p>
 *
 * @author chengp
 * @date 2019-09-30
 */
public interface IMemberService extends IService<Member> {

    /**
     * 账号密码登录
     *
     * @param memberForm 登录信息
     * @return MemberVO
     */
    MemberVO login(MemberLoginForm memberForm);


    /**
     * 注销
     *
     * @param request 请求
     */
    void logout(HttpServletRequest request);

    /**
     * 用户注册
     *
     * @param mFrom 会员表单
     */
    MemberVO save(MemberForm mFrom);



}


