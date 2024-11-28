package com.zjjhy.springboot;

import com.zjjhy.mapper.AdminSystemHomeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class SpringbootApplicationTests {
//    @Autowired
//    AdminSystemHomeMapper adminSystemUserMapper;


    @Test
    void contextLoads() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("LiDong");
        new Thread(()->{
//            threadLocal.set("Bob");
            System.out.println("Bob--->" + threadLocal.get());
        }).start();
        new Thread(()->{
//            threadLocal.set("Job");
            System.out.println("Job--->" + threadLocal.get());
        }).start();
        System.out.println("LiDong--->" + threadLocal.get());
    }

}
