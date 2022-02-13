package com.jc.community;

import com.jc.community.util.SensitiveFiler;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SensitiveTest {

    @Autowired
    private SensitiveFiler sensitiveFiler;

    @Test
    public void test(){
        String string = "这里可以赌☆博,哈哈哈";
        String filter = sensitiveFiler.filter(string);
        System.out.println(filter);
    }
}
