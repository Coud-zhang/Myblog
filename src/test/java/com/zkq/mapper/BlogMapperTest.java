package com.zkq.mapper;

import com.zkq.domain.Blog;
import com.zkq.domain.Page;
import com.zkq.domain.BlogCustom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class BlogMapperTest {
    @Autowired
    BlogMapper blogMapper;
    @Test
    public void getBlogByPage() {
        Page page=new Page();
        page.setStart(0);
        page.setPageNumber(2);
       List<Blog> blogs= blogMapper.getBlogByPage(page);
        System.out.println(blogs.get(1).getWritetime().getClass());
    }

    @Test
    public void getBlogTotalRows() {
        Page page=new Page();
        int a=blogMapper.getBlogTotalRows();
        System.out.println(a);
    }

    @Test
    public void getBlogWithKeyWord() {
        Page page=new Page();
        page.setKeyword("java");
        page.setStart(0);
        page.setPageNumber(4);
        List<Blog> blogs= blogMapper.getBlogWithKeyWord(page);
        System.out.println(blogs);
    }

    @Test
    public void getBlogTotalWithKeyWord() {
        Page page=new Page();
        page.setKeyword("java");
        int a=blogMapper.getBlogTotalWithKeyWord(page);
        System.out.println(a);
    }

    @Test
    public void deleteBlog() {
        BlogCustom BlogCustom =new BlogCustom();
        BlogCustom.setId(2);
        int a=blogMapper.deleteBlog(BlogCustom);
        System.out.println(a);
    }

    @Test
    public void getBlogById() {
        BlogCustom BlogCustom =new BlogCustom();
        BlogCustom.setId(27);
        BlogCustom blogCustom=blogMapper.getBlogById(BlogCustom);
        System.out.println(blogCustom);
    }

    @Test
    public void updateBlog() {
        BlogCustom BlogCustom =new BlogCustom();
        BlogCustom.setId(3);
        BlogCustom.setTitle("3333");
        BlogCustom.setArticle("3");
        int a=blogMapper.updateBlog(BlogCustom);
        System.out.println(a);
    }
}