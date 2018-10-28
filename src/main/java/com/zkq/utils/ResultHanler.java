package com.zkq.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ResultHanler<T> {
    private  int code;
    private String msg;
    private  T data;
    private  int count;
    public ResultHanler( int code,String msg,int count, T data) {
        this.msg = msg;
        this.data = data;
        this.code = code;
        this.count = count;
    }
    public ResultHanler() {
    }
}
