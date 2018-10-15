package com.zhj.transaction;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.lang.reflect.Method;

/**
 * Created by zhj on 2018/10/10.
 */

public class PrototypeTransactionInterceptor implements MethodInterceptor {

    private PlatformTransactionManager transactionManager;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        TransactionDefinition definition = getTransactionDefinitionByMethod(method);
        TransactionStatus txStatus = transactionManager.getTransaction(definition);
        Object result = null ;
        try{
            result = invocation.proceed();
        }catch (Throwable t){
            if (needRollbackOn(t)){
                transactionManager.rollback(txStatus);
            }else{
                transactionManager.commit(txStatus);
            }
            throw  t ;
        }
        transactionManager.commit(txStatus);
        return result ;
    }

    protected boolean needRollbackOn(Throwable t){
        //TODO ...更多实现细节
        return false ;
    }

    protected TransactionDefinition getTransactionDefinitionByMethod(Method method){
        //TODO ...更多实现细节
        return null ;
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}