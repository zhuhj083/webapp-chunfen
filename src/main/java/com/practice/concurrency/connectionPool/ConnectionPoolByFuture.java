package com.practice.concurrency.connectionPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by zhj on 2018/6/26.
 */

public class ConnectionPoolByFuture {
    private ConcurrentHashMap<String,FutureTask<Connection>> pool = new ConcurrentHashMap<>();

    public Connection getConnection(String key) throws InterruptedException,ExecutionException {
        FutureTask<Connection> connectionFutureTask = pool.get(key);
        if (connectionFutureTask != null){
            return connectionFutureTask.get();
        }else{
            Callable<Connection> callable = new Callable<Connection>() {
                @Override
                public Connection call() throws Exception {
                    return createConnection();
                }
            };
            FutureTask<Connection> newTask = new FutureTask<Connection>(callable);
            connectionFutureTask = pool.putIfAbsent(key,newTask);
            if (connectionFutureTask == null ){
                connectionFutureTask = newTask;
                connectionFutureTask.run();
            }
            return connectionFutureTask.get();
        }
    }

    public Connection createConnection(){
        return new Connection();
    }

    class Connection{

    }
}