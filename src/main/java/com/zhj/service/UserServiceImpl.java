package com.zhj.service;

import com.zhj.entity.User;
import com.zhj.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * Created by zhj on 2018/8/9.
 */


@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        jdbcTemplate.update("insert into user(`name`,age,sex) VALUES (?,?,?)",
                new Object[]{user.getName(),user.getAge(),user.getSex()},new int[]{Types.VARCHAR, Types.INTEGER,Types.VARCHAR }
                );
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true,timeout = 20)
    public void batchSave(List<User> userList) {
        jdbcTemplate.batchUpdate("insert into user(`name`,age,sex) VALUES (?,?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                User user = userList.get(i);
                ps.setString(1 , user.getName());
                ps.setInt(2 , user.getAge());
                ps.setString(3 , user.getSex());
            }

            @Override
            public int getBatchSize() {
                return userList.size();
            }
        });
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true,timeout = 20)
    public List<User> getUsers() {
        List<User> list = jdbcTemplate.query("select * from user",new UserRowMapper());
        return list ;
    }
}