package com.zkq.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
public class uploadResultHandler<T> {
    private int code;
    private String msg;
    private Map<T,T> data;
    public uploadResultHandler(){}

    public uploadResultHandler(int code, String msg, Map<T, T> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}



