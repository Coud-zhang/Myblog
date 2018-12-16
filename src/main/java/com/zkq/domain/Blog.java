package com.zkq.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author zkq15
 * */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
   @Field(value = "id")
    private Integer id;
    private Integer  preId;
    private Integer nextId;
    @Field(value = "title")
    private String title;
    @Field(value = "article")
    private String article;
    private LocalDate writetime;
    @Field(value = "time")
    private String time;
    @Field(value = "zan")
    private Integer zan;
    @Field(value = "label")
    private String label;
}