package com.ludata.luDataTest.controller;

import com.ludata.luDataTest.customComment.ListParam;
import com.ludata.luDataTest.dataSource1.pojo.User1;
import com.ludata.luDataTest.service.User1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class User1Controller {

    @Autowired
    private User1Service user1Service;

    @RequestMapping("/dataSource1/users")
    public List<User1> getUser(){
        return user1Service.getUsers();
    }

    @RequestMapping("/dataSource1/addUser")
    public void addUser(User1 user1){
        user1Service.addUser(user1);
    }

    @RequestMapping("/dataSource1/getUsers")
    public List<User1> getUsers(@ListParam(User1.class) List<User1> users){
        for(User1 u:users){
            System.out.println(u);
        }
        return users;
    }


}
