package com.zkq.service;

import com.zkq.Mapper.BlogMapper;
import com.zkq.domain.Blog;
import com.zkq.domain.BlogCustom;
import com.zkq.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class BlogServiceImpl implements  BlogServic {
   @Autowired
    BlogMapper blogMapper;
    @Override
    public List<Blog> getBlogByPage(Page blogPage) {
        int start= (blogPage.getCurrentPage()-1)*blogPage.getPageNumber();
        blogPage.setStart(start);
        List<Blog> bloglist=blogMapper.getBlogByPage(blogPage); //得到分页查询的list
        return bloglist;
    }

    @Override
    public void setBlogTotalRows(Page blogPage) {
        int count=blogMapper.getBlogTotalRows();
        blogPage.setTotalRows(count); //设置总行数
    }

    @Override
    public List<Blog> getBlogWithKeyWord(Page page) {
        int start= (page.getCurrentPage()-1)*page.getPageNumber();
        page.setStart(start);
         List<Blog> blogs=blogMapper.getBlogWithKeyWord(page);
        return blogs;
    }

    @Override
    public void setBlogTotalWithKeyWord(Page page) {
        int count=blogMapper.getBlogTotalWithKeyWord(page);
        page.setTotalRows(count); //设置总行数
        System.out.println(count);
    }

    @Override
    public boolean deleteBlog(BlogCustom blogCustom) {
        return blogMapper.deleteBlog(blogCustom)==1?true:false;
    }

    @Override
    public boolean insertBlog(BlogCustom blogCustom) {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        blogCustom.setData(sdf.format(date));
        return blogMapper.insertBlog(blogCustom)==1?true:false;
    }

    @Override
    public BlogCustom getBlogById(BlogCustom blogCustom) {
        return blogMapper.getBlogById(blogCustom);
    }

    @Override
    public boolean updateBlog(BlogCustom BlogCustom) {
        return blogMapper.updateBlog(BlogCustom)==1?true:false;
    }

}
