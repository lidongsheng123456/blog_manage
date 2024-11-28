package com.zjjhy.common.interceptor;

import cn.hutool.core.util.ObjectUtil;
import com.zjjhy.common.Constants;
import com.zjjhy.common.enums.ResultCodeEnum;
import com.zjjhy.common.exception.BusinessException;
import com.zjjhy.util.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * jwt拦截器
 */

/**
 * JWT拦截器，用于在请求处理前验证JWT令牌的有效性
 * 实现了HandlerInterceptor接口以参与Spring MVC的请求拦截
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    // 日志记录器，用于记录操作日志
    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    /**
     * 在请求处理之前进行拦截处理
     *
     * @param request  HttpServletRequest对象，用于获取请求信息
     * @param response HttpServletResponse对象，用于设置响应信息
     * @param handler  请求处理者，通常是一个Controller方法
     * @return boolean 表示是否继续执行其他拦截器和请求处理方法
     * 在这里总是返回true，如果验证失败会抛出异常中断执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1. 从http请求的header中获取token
        String token = request.getHeader(Constants.TOKEN);
        if (ObjectUtil.isEmpty(token)) {
            // 如果没拿到，从参数里再拿一次
            token = request.getParameter(Constants.TOKEN);
        }
        // 2. 再次从http请求的header中获取token
        if (ObjectUtil.isEmpty(token)) {
            // 如果仍然没有获取到token，抛出异常，提示token无效
            throw new BusinessException(ResultCodeEnum.TOKEN_INVALID_ERROR);
        }

        // 3.开始解析token 返回布尔值
        return TokenUtils.parseToken(token);
    }
}
