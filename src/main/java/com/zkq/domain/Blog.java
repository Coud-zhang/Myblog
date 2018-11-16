package com.zkq.domain;

import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
/**
 * @author zkq15
 * */
@Component
@Data
public class Blog {
    private Integer id;
    private Integer  preId;
    private Integer nextId;
    private String title;
    private String article;
    private String data;
    private Integer zan;
    private String label;
}