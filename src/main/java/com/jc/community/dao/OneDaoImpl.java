package com.jc.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository("oneimlp")  // 操作数据库的类 建议加这个注解添加到容器中
                        // 括号里是给类起别名 默认名字是类名小写
@Primary //这个注解就是说还有别的接口实现类  优先提供这个类
public class OneDaoImpl implements OneDao{
    @Override
    public String select() {
        return "null+++++";
    }
}
