package com.ludata.luDataTest.service;

import com.ludata.luDataTest.dataSource1.pojo.User1;

import java.util.List;

public interface User1Service {

    public List<User1> getUsers();

    public void addUser(User1 user1);
}
