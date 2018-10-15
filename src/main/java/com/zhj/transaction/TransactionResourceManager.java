package com.zhj.transaction;

/**
 * Created by zhj on 2018/10/9.
 */

public class TransactionResourceManager {
    private static ThreadLocal resources = new ThreadLocal();

    public static Object getResource(){
        return resources.get();
    }

    public static void bindResource(Object resource){
        resources.set(resource);
    }

    public static Object unbindResource(){
        Object res = getResource();
        resources.set(null);
        return res;
    }

}