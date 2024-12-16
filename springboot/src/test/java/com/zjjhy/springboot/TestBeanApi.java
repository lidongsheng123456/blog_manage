package com.zjjhy.springboot;

import com.zjjhy.common.pojo.entity.Docs;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestBeanApi {
    @Test
    public void test1() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(1);
        list.add(45);
        list.add(35);
        list.add(37);
        list.add(8);
        deleteUser(list);
    }

    public void deleteUser(List<Integer> ids) {
        List<Docs> docsVos = new ArrayList<>();
        //假如这是从数据库查出来的文章数据
        docsVos.add(new Docs(5, null, null, null, null, null));
        docsVos.add(new Docs(3, null, null, null, null, null));
        docsVos.add(new Docs(8, null, null, null, null, null));
        docsVos.add(new Docs(12, null, null, null, null, null));
        docsVos.add(new Docs(15, null, null, null, null, null));
        docsVos.add(new Docs(1, null, null, null, null, null));

        List<Integer> docsIds = new ArrayList<>();

        for (Integer idss : ids) {
            for (Docs docsVo : docsVos) {
                if (idss.equals(docsVo.getId())) {
                    docsIds.add(docsVo.getId());
                }
            }
        }

        docsIds.forEach(System.out::println);
    }
}
