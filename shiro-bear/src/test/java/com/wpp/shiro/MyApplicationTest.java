package com.wpp.shiro;

import com.wpp.shiro.dao.ArticleDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MyApplication.class)
@RunWith(SpringRunner.class)
public class MyApplicationTest {
    @Autowired
    ArticleDao articleDao;

    @Test
    public void t1() {
        System.out.println(1);
        System.out.println(articleDao);
        int i = articleDao.countArticle();
        System.out.println(i);
    }

}