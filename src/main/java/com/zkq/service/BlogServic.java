package com.zkq.service;

import com.zkq.domain.Blog;
import com.zkq.domain.Page;
import com.zkq.domain.BlogCustom;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author zkq15
 * */
public interface BlogServic {
    public List<Blog> getBlogByPage(Page blogPage);
    public void setBlogTotalRows(Page blogPage);
    public List<Blog> getBlogWithKeyWord(Page page);
    public void setBlogTotalWithKeyWord(Page page);
    public boolean deleteBlog(BlogCustom blogCustom);
    public boolean insertBlog(BlogCustom blogCustom);
    public BlogCustom getBlogById(BlogCustom blogCustom);
    public boolean updateBlog(BlogCustom blogCustom);
}
