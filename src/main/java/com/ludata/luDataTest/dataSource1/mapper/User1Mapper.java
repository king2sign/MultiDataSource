package com.ludata.luDataTest.dataSource1.mapper;

import com.ludata.luDataTest.dataSource1.pojo.User1;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface User1Mapper {

    @Select(" select * from user1 ")
    public List<User1> getUsers();

    @Insert("insert into user1       "
            + "(id, name, age)    "
            + "values                   "
            + "(#{id}, #{name}, ${age}) ")
    public void addUser(User1 user1);

}
