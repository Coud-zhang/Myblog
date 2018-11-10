package com.zkq.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class Page{
    private int currentPage; //当前页数
    private int totalPage;//总页数
    private int pageNumber;//每页行数
    private int totalRows;//总行数
    private List<Blog> list;//前端页面展示博客需要返回的数据
    private int start;
    private String keyword;
    public Page(){}
    public Page(int currentPage, int pageNumber){
        this.currentPage=currentPage;
        this.pageNumber=pageNumber;
    }
}
