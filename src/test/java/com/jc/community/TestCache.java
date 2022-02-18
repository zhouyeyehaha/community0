package com.jc.community;

import com.jc.community.service.DiscussPostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCache {

    @Autowired
    private DiscussPostService discussPostService;

    @Test
    public void testCache() {
        System.out.println(discussPostService.findDiscussPosts(0,0,10,1));
        System.out.println(discussPostService.findDiscussPosts(0,0,10,1));
        System.out.println(discussPostService.findDiscussPosts(0,0,10,1));
        System.out.println(discussPostService.findDiscussPosts(0,0,10,0));
    }

    @Test
    public void testCache02() {
        System.out.println(discussPostService.findDiscussPostRows(0));
        System.out.println(discussPostService.findDiscussPostRows(0));
        System.out.println(discussPostService.findDiscussPostRows(1));
    }

}
