package com.jc.community.dao;

import org.springframework.stereotype.Repository;

@Repository("two")
public class TwoDaoImpl implements OneDao {
    @Override
    public String select() {
        return "22222222";
    }
}
