package com.ludata.luDataTest.dataSource2.mapper;

import com.ludata.luDataTest.dataSource2.pojo.User2;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface User2Mapper {

    @Select(" select * from user2 ")
    public List<User2> getUsers();

    @Insert("insert into user2       "
            + "(id, name, age)    "
            + "values                   "
            + "(#{id}, #{name}, ${age}) ")
    public void addUser(User2 user2);
}
