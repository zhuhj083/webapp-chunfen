package com.zhj.entity;

/**
 * Created by zhj on 2018/8/8.
 */

public class TestBeanImpl implements TestBean {

    private int num = 15 ;
    private double price = 8.0 ;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double test(){
        System.out.println("test");
        return price * num;
    }
}