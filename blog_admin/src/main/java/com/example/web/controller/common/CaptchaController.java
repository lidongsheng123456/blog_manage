package com.example.web.controller.common;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.example.common.interface_constants.Constants;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RequestMapping("/common/captcha")
@RestController
@Slf4j
public class CaptchaController {
    /**
     * 生成验证码
     */
    @GetMapping("/getCaptcha")
    public void getCaptcha(HttpSession session, HttpServletResponse response) {
        // 定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(Constants.CAPTCHA_WIDTH, Constants.CAPTCHA_HEIGHT);
        // 设置返回数据类型
        response.setContentType("image/jpeg");
        // 禁止使用缓存
        response.setHeader("Pragma", "No-cache");
        try {
            // 输出到页面
            lineCaptcha.write(response.getOutputStream());
            // 将 生成的验证码 和 验证码生成时间 存储到session中
            session.setAttribute(Constants.CAPTCHA_KEY, lineCaptcha.getCode());
            session.setAttribute(Constants.CAPTCHA_DATE, new Date());
            // 关闭流
            response.getOutputStream().close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
