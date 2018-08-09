package com.test;

import com.zhj.controller.SpringContextHolder;
import com.zhj.entity.User;
import com.zhj.service.UserService;

import java.util.List;

/**
 * Created by zhj on 2018/8/9.
 */

public class SpringJdbcTest {

    public static void main(String[] args) {
        UserService userService = SpringContextHolder.getBean("userService");
        User user = new User();
        user.setAge(20);
        user.setName("张三");
        user.setSex("男");

        userService.save(user);

        List<User> persons = userService.getUsers();
        System.out.println("-----------得到所有的user "+ persons.size());
        for (User u : persons) {
            System.out.println(u);
        }

    }
    
}