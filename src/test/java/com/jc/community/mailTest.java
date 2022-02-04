//package com.jc.community;
//
////import com.jc.community.util.MailClient;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.Thymeleaf;
//import org.thymeleaf.context.Context;
//
//
//@SpringBootTest
//public class mailTest {
//    @Autowired
//    private MailClient mailClient;
//
//    @Autowired
//    private TemplateEngine templateEngine;
//
//    @Test
//    public void test() {
//        mailClient.sendMail("472917655@qq.com", "TEST", "test");
//    }
//
//    @Test
//    public void test2() {
//        Context context = new Context();
//        context.setVariable("username", "???");
//        String process = templateEngine.process("/mail/demo", context);
//        System.out.println(context);
//        mailClient.sendMail("472917655@qq.com", "HTML", process);
//    }
//}
