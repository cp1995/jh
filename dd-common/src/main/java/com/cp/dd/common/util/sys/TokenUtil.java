package com.cp.dd.common.util.sys;

import com.cp.dd.common.constant.member.SourceEnum;
import com.cp.dd.common.util.json.FastJsonUtil;
import com.cp.dd.common.util.security.AesEncryptUtil;
import com.cp.dd.common.vo.member.MemberVO;
import com.cp.dd.common.vo.sys.SysUserVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 关于token的获取和生成，以及登录用户信息的获取
 * <p>
 * 要在web配置中将该组件加入bean管理，注入redisTemplate
 *
 * @author chengp
 */
@Slf4j
public class TokenUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private final static String TOKEN_HEADER = "Authorization";

    /**
     * 默认后台超时时间14400秒，即4小时
     */
    private final static long DEFAULT_ADMIN_TIMEOUT = 14400L;

    /**
     * 默认门户超时时间14400秒，即4小时
     */
    private final static long DEFAULT_WEB_TIMEOUT = 14400L;

    /**
     * 生成token并存入缓存
     *
     * @param sysUserVO sysUser
     * @return token
     */
    public String generateAdminTokenAndCache(SysUserVO sysUserVO) {
        return generateTokenAndCache("SYSUSER", SourceEnum.ADMIN, sysUserVO.getId().toString(), sysUserVO, DEFAULT_ADMIN_TIMEOUT);
    }

    /**
     * 生成token并存入缓存
     *
     * @param memberVO   member
     * @param sourceEnum 来源
     * @return token
     */
    public String generateWebTokenAndCache(MemberVO memberVO, SourceEnum sourceEnum) {
        return generateTokenAndCache("MEMBER", sourceEnum, memberVO.getId().toString(), memberVO, DEFAULT_WEB_TIMEOUT);
    }

    /**
     * 生成token并存入缓存
     *
     * @param type       类型 SYSUSER/MEMBER
     * @param sourceEnum 来源枚举
     * @param sign       标识 (用户id)
     * @param t          主体 SysUser/Member
     * @param expires    到期时间
     * @return token
     */
    private <T> String generateTokenAndCache(String type, SourceEnum sourceEnum, String sign, T t, long expires) {
        String token = generateToken(type, sourceEnum, sign, expires);
        cacheOrFlush(token, t);
        return token;
    }

    /**
     * 存入/刷新缓存
     *
     * @param token token
     * @param t     存入缓存对象
     * @param <T>   对象类型，如SysUser/Member
     */
    public <T> void cacheOrFlush(String token, T t) {
        final TokenObject tokenObject = getTokenObject(token);
        if (Objects.nonNull(tokenObject)) {
            redisTemplate.opsForValue().set(tokenObject.getKey(), t, tokenObject.expires, TimeUnit.SECONDS);
            log.debug("token信息写入redis缓存[{}],timeout[{}s]", tokenObject.getKey(), tokenObject.expires);
        }
    }

    /**
     * 根据token获取缓存中的值
     *
     * @param token token
     * @param <T>   对象类型
     * @return 对象
     */
    @SuppressWarnings("unchecked")
    public <T> T getObjByToken(String token) {
        final TokenObject tokenObject = getTokenObject(token);
        if (tokenObject == null) {
            return null;
        }

        Object obj = redisTemplate.opsForValue().get(tokenObject.getKey());
        log.debug("读取redis缓存token[{}], key为[{}]", token, tokenObject.getKey());
        if (obj != null) {
            // 更新时间
            redisTemplate.expire(tokenObject.getKey(), tokenObject.expires, TimeUnit.SECONDS);
        }
        return obj == null ? null : (T) obj;
    }

    /**
     * 从请求中获取token
     *
     * @param request 请求
     * @return token
     */
    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(TOKEN_HEADER);
        }
        return token;
    }

    /**
     * 生成token
     *
     * @param type       类型 SYSUSER/MEMBER
     * @param sourceEnum 来源枚举
     * @param sign       标识 (用户id)
     * @param expires    到期时间
     * @return token
     */
    private String generateToken(String type, SourceEnum sourceEnum, String sign, long expires) {
        String token = TokenGenerator.generateValue();
        TokenObject tokenObject = new TokenObject(type, sourceEnum.name(), sign, expires, token);
        // aes 加密
        return AesEncryptUtil.encrypt(FastJsonUtil.toJSON(tokenObject));
    }

    /**
     * 解析token
     *
     * @param token token
     * @return TokenObject
     */
    private TokenObject getTokenObject(String token) {
        // aes 解密，获取获取token里面的信息
        final String decrypt = AesEncryptUtil.decrypt(token);
        return decrypt == null ? null : FastJsonUtil.toBean(decrypt, TokenObject.class);
    }

    /**
     * 从缓存中移除token
     *
     * @param token token
     */
    public void removeToken(String token) {
        final TokenObject tokenObject = getTokenObject(token);
        if (Objects.nonNull(tokenObject)) {
            redisTemplate.delete(tokenObject.getKey());
        }
    }


    @Data
    @AllArgsConstructor
    public class TokenObject {
        private String type;
        private String source;
        private String sign;
        private long expires;
        private String token;

        String getKey() {
            // TODO key暂时不覆盖，同一用户同一端可多处登录
            return type + ":" + source + ":" + sign + ":" + token;
        }
    }
}
