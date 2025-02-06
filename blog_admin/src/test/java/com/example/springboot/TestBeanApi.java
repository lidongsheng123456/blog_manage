package com.example.springboot;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import org.junit.jupiter.api.Test;

public class TestBeanApi {
    @Test
    void getCaptcha() {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 45, 4, 4);
// 自定义验证码内容为四则运算方式
        captcha.setGenerator(new MathGenerator());
// 重新生成code
        captcha.createCode();
    }
}
