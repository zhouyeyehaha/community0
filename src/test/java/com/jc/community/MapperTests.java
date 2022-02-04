package com.jc.community;

import com.jc.community.dao.DiscussPostMapper;
import com.jc.community.dao.LoginTicketMapper;
import com.jc.community.dao.UserMapper;
import com.jc.community.entity.DiscussPost;
import com.jc.community.entity.LoginTicket;
import com.jc.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class MapperTests {

    @Autowired
    private UserMapper usermapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Test
    public void test01(){
        User user = usermapper.selectById(101);
        System.out.println(user);
        User liubei = usermapper.selectByName("liubei");
        System.out.println(liubei);
        User user1 = usermapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user1);
    }
    @Test
    public void test02(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("123587");
        user.setSalt("abc");
        user.setHeaderUrl("http//www.nowcoder.com/101.png");
        user.setCreatTime(new Date());

        int rows = usermapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }
    @Test
    public void test03(){
        int i = usermapper.updataStatus(150, 1);
        System.out.println(i);
        int i1 = usermapper.updataHeader(150, "http//www.nowcoder.com/102.png");
        System.out.println(i1);
        int hello = usermapper.updataPassword(150, "hello");
        System.out.println(hello);

    }

    @Autowired
    private DiscussPostMapper discussPost;

    @Test
    public void test04(){
        List<DiscussPost> discussPosts = discussPost.selectDiscussPosts(149, 0, 10);
        for (DiscussPost post : discussPosts) {
            System.out.println(post);
        }

        int i = discussPost.selectDiscussPostRows(149);
        System.out.println(i);
    }

    @Test
    public void test05(){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setTicket("anc");
        loginTicket.setExpired((new Date(System.currentTimeMillis() + 1000 * 60 * 10)));
        loginTicket.setUserId(101);
        loginTicket.setStatus(0);

        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void test06(){
        LoginTicket anc = loginTicketMapper.selectByTicket("anc");
        System.out.println(anc);

        loginTicketMapper.updateStatus("anc",1);

        anc = loginTicketMapper.selectByTicket("anc");
        System.out.println(anc);
    }


}
