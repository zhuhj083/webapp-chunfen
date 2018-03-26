package com.zhj.entity;

import javax.validation.constraints.Min;

/**
 * Created by zhj on 2018/3/26.
 */

public class Salary {
    @Min(value = 0,message = "请输入一个大于0的整数")
    private long shouru;
    private double gongjijin;
    private double  yanglao;
    private double yiliao;
    private double shiye;
    private double xiaoji;
    private double geshui;
    private double shuihou;

    public long getShouru() {
        return shouru;
    }

    public void setShouru(long shouru) {
        this.shouru = shouru;
    }

    public double getGongjijin() {
        return gongjijin;
    }

    public void setGongjijin(double gongjijin) {
        this.gongjijin = gongjijin;
    }

    public double getYanglao() {
        return yanglao;
    }

    public void setYanglao(double yanglao) {
        this.yanglao = yanglao;
    }

    public double getYiliao() {
        return yiliao;
    }

    public void setYiliao(double yiliao) {
        this.yiliao = yiliao;
    }

    public double getShiye() {
        return shiye;
    }

    public void setShiye(double shiye) {
        this.shiye = shiye;
    }

    public double getXiaoji() {
        return xiaoji;
    }

    public void setXiaoji(double xiaoji) {
        this.xiaoji = xiaoji;
    }

    public double getGeshui() {
        return geshui;
    }

    public void setGeshui(double geshui) {
        this.geshui = geshui;
    }

    public double getShuihou() {
        return shuihou;
    }

    public void setShuihou(double shuihou) {
        this.shuihou = shuihou;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "shouru=" + shouru +
                ", gongjijin=" + gongjijin +
                ", yanglao=" + yanglao +
                ", yiliao=" + yiliao +
                ", shiye=" + shiye +
                ", xiaoji=" + xiaoji +
                ", geshui=" + geshui +
                ", shuihou=" + shuihou +
                '}';
    }
}