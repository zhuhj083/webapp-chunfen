package com.zhj.transaction;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhj on 2018/10/10.
 */

public class QuoteServiceImpl implements QuoteService {
    private JdbcTemplate jdbcTemplate ;

    @Override
    public Quote getQuote() {
        return (Quote)getJdbcTemplate().queryForObject("", new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                Quote quote = new Quote();
                return quote;
            }
        });
    }

    @Override
    public Quote getQuoteByDateTime(DateTime dateTime) {
        return null;
    }

    @Override
    public void saveQuote(Quote quote) {
        throw new NotImplementedException();
    }

    @Override
    public void updateQuote(Quote quote) {
        throw new NotImplementedException();
    }

    @Override
    public void deteleQuote(Quote quote) {
        throw new NotImplementedException();
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}