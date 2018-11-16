package com.zkq.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @author zkq15
 * @param
 * */
@Component
@Data
public class Page{
    //当前页数
    private int currentPage;
    //总页数
    private int totalPage;
    //每页行数
    private int pageNumber;
    //总行数
    private int totalRows;
    //前端页面展示博客需要返回的数据
    private List<Blog> list;
    private int start;
    private String keyword;
    public Page(){}
    public Page(int currentPage, int pageNumber){
        this.currentPage=currentPage;
        this.pageNumber=pageNumber;
    }
}
