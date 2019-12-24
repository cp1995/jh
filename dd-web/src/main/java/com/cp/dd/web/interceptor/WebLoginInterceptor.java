package com.cp.dd.web.interceptor;


import com.cp.dd.common.annotation.IgnoreLogin;
import com.cp.dd.common.support.Result;
import com.cp.dd.common.util.sys.SessionCache;
import com.cp.dd.common.util.sys.TokenUtil;
import com.cp.dd.common.vo.member.MemberVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 自定义登录拦截器
 *
 * @author chengp
 * @date 2019/9/25
 */
@Slf4j
public class WebLoginInterceptor implements HandlerInterceptor {

    @Resource
    private TokenUtil tokenUtil;

    private static final String OPTIONS = "OPTIONS";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
         * 验证方法为预处理放行
         */
        if (OPTIONS.equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        log.info("requestUrl:{}", request.getServletPath());

        /*
         * 过滤无需验证的请求
         */
        // 获取注解
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Class<?> clazz = method.getDeclaringClass();

        IgnoreLogin ignoreLogin = clazz.getAnnotation(IgnoreLogin.class);
        ignoreLogin = Objects.isNull(ignoreLogin) ? method.getAnnotation(IgnoreLogin.class) : ignoreLogin;
        if (!Objects.isNull(ignoreLogin)) {
            // 无需验证
            return true;
        }

        /*
         * 验证token
         */
        String token = tokenUtil.getToken(request);
        log.info("token:{}", token);

        // 验证有无token
        if (StringUtils.isBlank(token)) {
            msg(response, HttpStatus.UNAUTHORIZED, "无访问权限");
            return false;
        }

        // 从缓存中获取用户
        MemberVO memberVO = tokenUtil.getObjByToken(token);
        if (memberVO == null) {
            msg(response, HttpStatus.FORBIDDEN, "token无效");
            return false;
        }
        log.info("当前用户：{}", memberVO.toString());

        // 存入线程本地变量
        SessionCache.put(memberVO);
        return true;
    }

    /**
     * 错误信息
     *
     * @param response response
     * @throws IOException IOException
     */
    private void msg(HttpServletResponse response, HttpStatus httpStatus, String msg) throws IOException {
        response.setStatus(httpStatus.value());
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(new ObjectMapper().writeValueAsString(Result.build(httpStatus.value(), msg)));
        writer.close();
        response.flushBuffer();
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // 请求结束后，移除线程本地变量
        SessionCache.remove();
    }
}
