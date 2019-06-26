package com.ludata.luDataTest.service.impl;

import com.ludata.luDataTest.dataSource2.mapper.User2Mapper;
import com.ludata.luDataTest.dataSource2.pojo.User2;
import com.ludata.luDataTest.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class User2ServiceImpl implements User2Service {

    @Autowired
    private User2Mapper  user2Mapper;

    @Override
    public List<User2> getUsers() {
        return user2Mapper.getUsers();
    }
}
