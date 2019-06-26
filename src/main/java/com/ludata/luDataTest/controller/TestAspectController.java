package com.ludata.luDataTest.controller;


import com.ludata.luDataTest.utils.MACUtil;
import com.ludata.luDataTest.utils.MainBordUtil;
import com.ludata.luDataTest.utils.NetworkUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestAspectController {


    @RequestMapping("Aspect/testAspact1")
    @ResponseBody
    public void testAspact1() throws  Exception{

        System.out.println("我正在执行这个内部方法开始 ");
        System.out.println("本机操作系统:"+ NetworkUtil.getOSName());
        System.out.println("本机(windows)的MAC地址："+ NetworkUtil.getWindowsMacAddress());
        System.out.println("本机主板序列号  :"+ MainBordUtil.getMainBordId());
        System.out.println("本机MAC地址  :"+ MACUtil.getMAC());

        System.out.println("我正在执行这个内部方法结束 ");

    }
}
