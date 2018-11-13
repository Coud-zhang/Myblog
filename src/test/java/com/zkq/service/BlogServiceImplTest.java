package com.zkq.service;

import com.zkq.domain.Blog;
import com.zkq.domain.BlogCustom;
import com.zkq.domain.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class BlogServiceImplTest {
   @Autowired
    BlogServic blogServic;
    @Test
    public void getBlogByPage() {
        Page page=new Page();
        page.setCurrentPage(2);
        page.setPageNumber(2);
        List<Blog> blogList=blogServic.getBlogByPage(page);
        System.out.println(blogList);
    }

    @Test
    public void getBlogTotalRows() {
        Page page=new Page();
        page.setPageNumber(2);
       blogServic.setBlogTotalRows(page);
        System.out.println(page.getTotalPage());
    }

    @Test
    public void getBlogWithKeyWord() {

    }

    @Test
    public void updateBlog() {
        BlogCustom BlogCustom =new BlogCustom();
        BlogCustom.setId(3);
        BlogCustom.setTitle("300");
        BlogCustom.setArticle("300");
        boolean a=blogServic.updateBlog(BlogCustom);
        System.out.println(a);
    }
}