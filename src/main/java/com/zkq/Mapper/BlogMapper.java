package com.zkq.Mapper;

import com.zkq.domain.Blog;
import com.zkq.domain.Page;
import com.zkq.domain.BlogCustom;

import java.util.List;

public interface BlogMapper {
    //分页查询博客列表和博客总数
public List<Blog> getBlogByPage(Page blogPage);
public int getBlogTotalRows();
    //带关键字的分页查询博客列表和博客总数
public List<Blog> getBlogWithKeyWord(Page page);
public int getBlogTotalWithKeyWord(Page page);
    //删除博客
public int deleteBlog(BlogCustom BlogCustom);
    //新增博客
public int insertBlog(BlogCustom blogCustom);
//根据id查找blog
public BlogCustom getBlogById(BlogCustom blogCustom);
//修改博客
public int updateBlog(BlogCustom blogCustom);
}