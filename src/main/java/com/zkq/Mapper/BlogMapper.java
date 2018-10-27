package com.zkq.Mapper;

import com.zkq.domain.Blog;
import com.zkq.domain.Page;
import com.zkq.domain.blogCustom;

import java.util.List;

public interface BlogMapper {
public List<Blog> getBlogByPage(Page blogPage);
public int getBlogTotalRows();
public List<Blog> getBlogWithKeyWord(Page page);
public int getBlogTotalWithKeyWord(Page page);
public int deleteBlog(blogCustom blogCustom);
}