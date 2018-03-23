package com.zhj.controller;

import com.zhj.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhj on 2018/3/23.
 */

@Controller
public class IndexController {

    @Autowired
    public SimpleDateFormat simpleDateFormat ;

    @RequestMapping(value = "index")
    public  String index(HttpServletRequest request , HttpServletResponse response){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
        User user = (User) ac.getBean("user");
        request.setAttribute("time", simpleDateFormat.format(new Date()));
        request.setAttribute("user", user);
        System.out.println("---------hello world----------------");
        return "index";
    }
}