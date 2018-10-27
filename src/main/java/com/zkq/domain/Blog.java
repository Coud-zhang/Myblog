package com.zkq.domain;

import lombok.Data;
import org.springframework.stereotype.Component;
@Component
@Data
public class Blog {
    private Integer id;
    private String title;
    private String article;
    private String data;
    private Integer zan;
    private String label;
}