package com.ludata.luDataTest.customComment;


import java.lang.annotation.*;

/**
 * 强制申明List的泛型<br/>
 * 用于反射获取参数类型
 * @author zengyuanjun
 * 作用：SpringMVC自动封装List对象 —— 自定义参数解析器
 *
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ListParam {
    public Class<?> value();
}