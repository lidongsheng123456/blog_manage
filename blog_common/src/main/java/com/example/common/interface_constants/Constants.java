package com.example.common.interface_constants;

public interface Constants {
    String TOKEN = "token";
    String USER_DEFAULT_PASSWORD = "123456";
    String REDIS_KEY = "docs_list";//redis存储文章列表的键
    Integer CAPTCHA_WIDTH = 100;//验证码宽度
    Integer CAPTCHA_HEIGHT = 40;//验证码高度
    long EXPIRATION_TIME = 60 * 1000;//过期时间
    String CAPTCHA_KEY = "captcha";//验证码键
    String CAPTCHA_DATE = "date";//验证码日期
    String ROLE = "管理员";//验证码日期
}
