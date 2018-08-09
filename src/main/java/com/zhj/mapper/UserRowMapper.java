package com.zhj.mapper;

import com.zhj.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhj on 2018/8/9.
 */

public class UserRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet set, int index) throws SQLException {
        User person = new User(set.getInt("id"),set.getString("name"),set.getInt("age"),set.getString("sex"));
        return person;
    }
}