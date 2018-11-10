package com.zkq.Controller;

import com.zkq.domain.Blog;
import com.zkq.domain.Page;
import com.zkq.utils.ResultHanler;
import com.zkq.domain.BlogCustom;
import com.zkq.service.BlogServic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogList {
     @Autowired
    BlogServic blogServic;
     @RequestMapping("/getBlogByPage")
     @ResponseBody
    public ResultHanler<List<Blog>> getBlogByPage(Page page, @RequestParam("page") int currentPage,@RequestParam("limit") int limit){
        page.setCurrentPage(currentPage);
        page.setPageNumber(limit);
        List<Blog> blogList=blogServic.getBlogByPage(page);
        blogServic.setBlogTotalRows(page);
        return new ResultHanler<>(0,"",page.getTotalRows(),blogList);
    }
    @RequestMapping("/getBlogWithKeyWord")
    @ResponseBody
    public ResultHanler<List<Blog>> getBlogWithKeyWord(Page page,@RequestParam("page") int spage,@RequestParam("limit") int limit){
        page.setCurrentPage(spage);
        page.setPageNumber(limit);
        List<Blog> blogs=blogServic.getBlogWithKeyWord(page);
        blogServic.setBlogTotalWithKeyWord(page);
        return new ResultHanler<>(0,"",page.getTotalRows(),blogs);
    }
     @RequestMapping("/deleteBlog")
    @ResponseBody
    public List<String> deleteBlog(BlogCustom BlogCustom){
         boolean flag= blogServic.deleteBlog(BlogCustom);
         List<String> blogs=new ArrayList<>();
         if(flag){
             blogs.add("true");
         }else{
             blogs.add("false");
         }
         return  blogs;
     }

     @RequestMapping("/insertBlog")
    @ResponseBody
    public  List<String> insertBlog(BlogCustom blogCustom){
         boolean flag= blogServic.insertBlog(blogCustom);
         List<String> blogs=new ArrayList<>();
         if(flag){
             blogs.add("true");
         }else{
             blogs.add("false");
         }
         return  blogs;
     }
     @RequestMapping("/getBlogById")
    @ResponseBody
    public BlogCustom getBlogById(BlogCustom blogCustom){
         return  blogServic.getBlogById(blogCustom);
     }

     @RequestMapping("/getBlogByPageToView")
    public void getBlogByPageToView(Page page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //设置当前页数
         int currentPage=Integer.valueOf(request.getParameter("currentPage"));
         if(currentPage==0){
             currentPage=1;
         }
        page.setCurrentPage(currentPage);
        //设置每页行数
        page.setPageNumber(5);
        //需要返回的数据
         List<Blog> blogList=blogServic.getBlogByPage(page);
         page.setList(blogList);
         //设置总行数
        blogServic.setBlogTotalRows(page);
        int totalPage=page.getTotalRows()%page.getPageNumber()==0?page.getTotalRows()/page.getPageNumber():page.getTotalRows()/page.getPageNumber()+1;
        page.setTotalPage(totalPage);
        request.setAttribute("bloglist",page);
         request.getRequestDispatcher("/BlogView.jsp").forward(request,response);

    }

}
