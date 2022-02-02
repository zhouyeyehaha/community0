package com.jc.community.dao;

import com.jc.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper //也可以用@Repository
public interface UserMapper {

    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updataStatus(int id,int status);

    int updataHeader(int id,String headerUrl);

    int updataPassword(int id,String password);

}
