package com.zhj.transaction;

import org.springframework.transaction.*;
import org.springframework.transaction.support.DefaultTransactionStatus;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by zhj on 2018/10/9.
 */

public class JdbcTransactionManager implements PlatformTransactionManager {

    private DataSource dataSource;

    public JdbcTransactionManager(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
        Connection connection ;
        try{
            connection = dataSource.getConnection();
            TransactionResourceManager.bindResource(connection);
            return new DefaultTransactionStatus(connection,true,true,false,true,null);
        }catch (SQLException e ){
            throw  new CannotCreateTransactionException("can't get connection for tx",e);
        }
    }

    @Override
    public void commit(TransactionStatus transactionStatus) throws TransactionException {
        Connection connection = (Connection) TransactionResourceManager.getResource();
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new TransactionSystemException("commit failed with SQLException ",e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                //记录异常信息，但通常不会有进一步有效的处理
            }
        }
    }

    @Override
    public void rollback(TransactionStatus transactionStatus) throws TransactionException {
        Connection connection = (Connection) TransactionResourceManager.getResource();
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw  new UnexpectedRollbackException("rollback failed with SQLException ",e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                //记录异常信息，但通常不会有进一步有效的处理
            }
        }
    }
}