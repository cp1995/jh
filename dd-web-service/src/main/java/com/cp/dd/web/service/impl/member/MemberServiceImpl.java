package com.cp.dd.web.service.impl.member;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import com.cp.dd.common.exception.ApiException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cp.dd.common.constant.Constants;
import com.cp.dd.common.constant.EnableEnum;
import com.cp.dd.common.constant.member.*;
import com.cp.dd.common.entity.member.Member;
import com.cp.dd.common.mapper.member.MemberMapper;
import com.cp.dd.common.util.PatternUtils;
import com.cp.dd.common.util.security.AesEncryptUtil;
import com.cp.dd.common.util.security.DigestUtil;
import com.cp.dd.common.util.sys.CaptchaUtil;
import com.cp.dd.common.util.sys.SysSmsCaptchaUtil;
import com.cp.dd.common.util.sys.TokenUtil;
import com.cp.dd.common.vo.member.MemberVO;
import com.cp.dd.web.form.member.MemberForm;
import com.cp.dd.web.form.member.MemberLoginForm;
import com.cp.dd.web.service.member.IMemberService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;


/**
 * <p>
 * 会员信息 服务实现类
 * </p>
 *
 * @author chengp
 * @date 2019-09-30
 */
@AllArgsConstructor
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    private MemberMapper memberMapper;
    private TokenUtil tokenUtil;
    private RedisTemplate<String, String> redisTemplate;
    private SysSmsCaptchaUtil sysSmsCaptchaUtil;


    @Override
    public MemberVO login(MemberLoginForm memberForm) {
        Member member;
        int type;
        type = MemberTypeEnum.PERSONAL.getType();
        // 根据用户名获取
        member = baseMapper.selectOne(Wrappers.<Member>lambdaQuery().eq(Member::getUsername, memberForm.getUsername())
                .eq(Member::getType, MemberTypeEnum.PERSONAL.getType()));
        if (Objects.isNull(member)) {
            // 根据手机号获取
            member = baseMapper.selectOne(Wrappers.<Member>lambdaQuery().eq(Member::getPhone, memberForm.getUsername())
                    .eq(Member::getType, MemberTypeEnum.PERSONAL.getType()));
        }
        if (Objects.isNull(member)) {
            throw new ApiException(2, "该账号不存在");
        }
        if (member.getEnable() != EnableEnum.ENABLED.getValue()) {
            throw new ApiException(3, "该账号已禁用");
        }
        if (type == MemberTypeEnum.TEAM.getType() && member.getAuditStatus() != MemberAuditStatusEnum.PASS.getStatus()) {
            throw new ApiException(4, "该账号正在审核中,请审核通过了在使用");
        }
        //获取密码输入错误次数
        String jedis = redisTemplate.opsForValue().get(Constants.LOGIN_ERROR + type + member.getPhone());
        jedis = jedis == null ? "0" : jedis;
        int count = Integer.parseInt(jedis);

        if (count >= 3) {
            if (StringUtils.isBlank(memberForm.getSign()) || StringUtils.isBlank(memberForm.getCode())) {
                throw new ApiException(count, "请输入图形验证码!");
            } else {
                if (CaptchaUtil.isNotValid(memberForm.getSign(), memberForm.getCode())) {
                    throw new ApiException(count, "验证码错误");
                }
            }
        }
        if (!DigestUtil.encrypt(AesEncryptUtil.decrypt(memberForm.getPassword())).equals(member.getPassword())) {
            // 先AES解密，在MD5,SHA1加密与数据库密码对比
            redisTemplate.opsForValue().set(Constants.LOGIN_ERROR + type + member.getPhone(), count + 1 + "");
            throw new ApiException(count + 1, "输入的账号或密码错误");
        }

        member.setLastLoginTime(LocalDateTime.now());
        baseMapper.updateById(member);
        MemberVO memberVO = new MemberVO();
        BeanUtils.copyProperties(member, memberVO);
        String token = tokenUtil.generateWebTokenAndCache(memberVO, SourceEnum.getEnumByType(memberForm.getLoginType()));
        memberVO.setAuthorization(token);
        redisTemplate.delete(Constants.LOGIN_ERROR + type + member.getPhone());
        //保存登陆记录
        return memberVO;
    }

    @Override
    public void logout(HttpServletRequest request) {
        String token = tokenUtil.getToken(request);
        tokenUtil.removeToken(token);
    }

    @Override
    public MemberVO save(MemberForm mFrom) {
        String phoneCode = sysSmsCaptchaUtil.getCaptcha(mFrom.getPhone());
        if (!StringUtils.equals(mFrom.getCode(), phoneCode)) {
            throw new ApiException("短信验证码校验失败");
        }
        if (!PatternUtils.checPasswork(mFrom.getPassword())) {
            throw new ApiException("原密码必须至少包含数字、大写字母、小写字母、特殊字符中的三种类型,且8~16位");
        }
        Member member = new Member();
        BeanUtils.copyProperties(mFrom, member);
        member.setCreateTime(LocalDateTime.now());
        member.setLastLoginTime(LocalDateTime.now());
        // 先AES解密，在MD5,SHA1加密与数据库密码对比
        String password = AesEncryptUtil.decrypt(member.getPassword());
        //密码MD5加密
        member.setPassword(DigestUtil.encrypt(password));
        member.setType(MemberTypeEnum.PERSONAL.getType());//个人用户
        save(member);
        MemberVO memberVO = new MemberVO();
        BeanUtils.copyProperties(member, memberVO);
        String token = tokenUtil.generateWebTokenAndCache(memberVO, SourceEnum.getEnumByType(mFrom.getSource()));
        memberVO.setAuthorization(token);
        return memberVO;
    }



    private MemberForm setMemberForm(String phone, Integer type) {
        MemberForm mFrom = new MemberForm();
        mFrom.setPhone(phone);
        mFrom.setUsername(phone);
        mFrom.setSex(0);
        mFrom.setType(type);
        mFrom.setBirthday(LocalDate.now());
        mFrom.setPassword("123456Aa");
        return mFrom;
    }


   // @Transactional(rollbackFor = Exception.class)



}
