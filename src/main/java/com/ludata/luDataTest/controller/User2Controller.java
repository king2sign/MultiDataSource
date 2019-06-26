package com.ludata.luDataTest.controller;

import com.ludata.luDataTest.dataSource2.pojo.User2;
import com.ludata.luDataTest.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class User2Controller {

    @Autowired
    private User2Service user2Service;

    @RequestMapping("/dataSource2/users")
    public List<User2> getUser(){
        return user2Service.getUsers();
    }

}
