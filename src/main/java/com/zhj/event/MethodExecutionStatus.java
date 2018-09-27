package com.zhj.event;

public enum MethodExecutionStatus {
    BEGIN("BEGIN"),END("END");

    private String value;

    MethodExecutionStatus(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
