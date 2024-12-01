package com.zjjhy.common.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.zjjhy.common.context.BaseContext;
import com.zjjhy.common.enums.ResultCodeEnum;
import com.zjjhy.common.enums.RoleEnum;
import com.zjjhy.common.exception.BusinessException;
import com.zjjhy.common.pojo.entity.User;
import com.zjjhy.service.LoginService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Token工具类
 */
@Component
public class TokenUtils {

    //使用 LoggerFactory.getLogger(TokenUtils.class) 初始化 log 变量，TokenUtils.class 是传入的参数，用于指定日志记录器的名称。
    private static final Logger log = LoggerFactory.getLogger(TokenUtils.class);

    // 登录服务的静态实例，用于在类的静态方法中访问登录服务
    private static LoginService staticLoginService;

    // 通过资源注入获取登录服务的实例
    @Resource
    private LoginService loginService;

    /**
     * 生成token
     */
    public static String createToken(String data, String sign) {
        return JWT.create().withAudience(data) // 将 userId-role 保存到 token 里面,作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // 2小时后token过期
                .sign(Algorithm.HMAC256(sign)); // 以 password 作为 token 的密钥
    }

    /**
     * 解析token
     */
    public static boolean parseToken(String token) {
        log.info("jwt校验：{}", token);
        User user = null;
        try {
            //解析token获取存储的载荷 id和role
            String userRole = JWT.decode(token).getAudience().get(0);
            String userId = userRole.split("-")[0];  // 获取用户id --> 1
            log.info("当前用户id：{}", userId);
            BaseContext.setCurrentId(Long.parseLong(userId));
            String role = userRole.split("-")[1];    // 获取角色 --> 管理员

            //token的载荷默认是管理员
            if (RoleEnum.ADMIN.getRole().equals(role)) {
                //根据id查询数据库
                user = staticLoginService.selectById(Integer.valueOf(userId));
            }
        } catch (Exception e) {
            //如果解析token发生异常，抛出异常，提示token验证失败
            throw new BusinessException(ResultCodeEnum.TOKEN_CHECK_ERROR);
        }

        if (ObjectUtil.isNull(user)) {
            //未根据id查询到当前用户抛出异常，提示用户不存在
            throw new BusinessException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        try {
            // 用户密码加签验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPwd())).build();
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            // 如果token验证失败，抛出异常，提示token验证失败
            throw new BusinessException(ResultCodeEnum.TOKEN_CHECK_ERROR);
        }

        return true;
    }

    // @PostConstruct 注解用于标记一个方法，该方法在所有依赖注入完成后、
    // 但在该 bean 被任何其他 bean 使用之前执行。这个注解通常用于执行一些初始化操作。
    // 确保loginService被依赖注入完成之后再进行初始化staticLoginService
    @PostConstruct
    public void setUserService() {
        staticLoginService = loginService;
    }
}

