package com.jc.community.service;


import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service // 业务组件 建议加这个注解把类放到容器中

public class OneService {

    @PostConstruct
    public void init(){
        System.out.println("oooooo");
    }
}
