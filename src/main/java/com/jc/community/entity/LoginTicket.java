package com.jc.community.entity;

import lombok.Data;

import java.util.Date;
@Data
//实体类
public class LoginTicket {

    private int id;
    private int userId;
    private String ticket;
    private int status;
    private Date expired;

    @Override
    public String toString() {
        return "LoginTicket{" +
                "id=" + id +
                ", userId=" + userId +
                ", ticket='" + ticket + '\'' +
                ", status=" + status +
                ", date=" + expired +
                '}';
    }
}
