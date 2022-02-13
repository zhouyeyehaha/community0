package com.jc.community;

import com.jc.community.dao.OneDao;
import com.jc.community.dao.OneDaoImpl;
import com.jc.community.service.OneService;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommunityApplicationTests {

    @Autowired //自动从容器里拿这个类
                // 他是按类拿的  不是按名字拿的
    @Qualifier("two") // 这个注解指定名字拿哪个bean
    private OneDao O;

    @Autowired
    private OneDao T; //面向接口的思想

    @Test
    void contextLoads() {
        System.out.println(O.select());
        System.out.println(T.select());
    }

}
