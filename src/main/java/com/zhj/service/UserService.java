package com.zhj.service;

import com.zhj.entity.User;

import java.util.List;

/**
 * Created by zhj on 2018/8/9.
 */

public interface UserService {
     void save(User user);
     void batchSave(List<User> userList);
     List<User> getUsers();
}