package com.zhj.jdbctemplateDemo;

import org.springframework.dao.DataAccessException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by zhj on 2018/9/28.
 */

public class JdbcTemplate {
    public final Object execute(StatementCallback callback){
        Connection con = null ;
        Statement stmt = null ;

        try{
            con = getConnection();
            stmt = con.createStatement();

            Object retValue = callback.doWithStatement(stmt);
            return retValue;

        }catch (SQLException e){
            DataAccessException ex = translateSQLException(e);
            throw ex;
        }finally {
            cloneStatement(stmt);
            releaseConnection(con);
        }



    }

    private Connection getConnection(){
        //TODO
        return null ;
    }

    private DataAccessException translateSQLException(SQLException e){
        //TODO
        return  null;
    }

    private void cloneStatement(Statement stmt){
        //TODO
    }

    private void releaseConnection(Connection connection){
        //TODO
    }

}