package com.ludata.luDataTest.service.impl;

import com.ludata.luDataTest.dataSource1.mapper.User1Mapper;
import com.ludata.luDataTest.dataSource1.pojo.User1;
import com.ludata.luDataTest.dataSource2.mapper.User2Mapper;
import com.ludata.luDataTest.dataSource2.pojo.User2;
import com.ludata.luDataTest.service.User1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class User1ServiceImpl  implements User1Service {

    @Autowired
    private User1Mapper user1Mapper;

    @Autowired
    private User2Mapper user2Mapper;


    public List<User1> getUsers() {
        return user1Mapper.getUsers();
    }

    @Transactional(rollbackFor = Exception.class)
    public void addUser(User1 user1) {
        user1Mapper.addUser(user1);

        //操作数据源2中的表中的数据
        User2  user2=new User2();
        user2.setName("777");
        user2.setAge(666);
        user2Mapper.addUser(user2);


    }
}
