package com.ludata.luDataTest.dataSource1.pojo;


import lombok.Data;

@Data
public class User1 {

    private Integer id;
    private String  name;
    private Integer age;

    @Override
    public String toString() {
        return "User1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }





}
