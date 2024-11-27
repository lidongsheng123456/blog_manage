package com.zjjhy.springboot;

import com.zjjhy.mapper.AdminSystemHomeMapper;
import com.zjjhy.pojo.entity.Docs;
import com.zjjhy.pojo.vo.DocsVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringbootApplicationTests {
    @Autowired
    AdminSystemHomeMapper adminSystemUserMapper;

    @Test
    void contextLoads() {
        Integer i = 10;
        System.out.println(i + "dd");
    }

}
