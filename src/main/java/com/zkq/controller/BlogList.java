package com.zkq.controller;

import com.zkq.domain.Blog;
import com.zkq.domain.Page;
import com.zkq.utils.ResultHanler;
import com.zkq.domain.BlogCustom;
import com.zkq.service.BlogServic;
import com.zkq.utils.SolrOperator;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrServerException;
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
/**
 * @author zkq15
 * */

@Controller
@Slf4j
public class BlogList {
     @Autowired
    BlogServic blogServic;
     @Autowired
    SolrOperator solrOperator;

    /**
     * 博客管理界面分页展示博客
     * @param page page对象
     * @param currentPage 当前页数
     * @param limit     每页行数
     * @return ResultHanler<List<Blog>>
     */
     @RequestMapping("/getBlogByPage")
     @ResponseBody
    public ResultHanler<List<Blog>> getBlogByPage(Page page, @RequestParam("page") int currentPage,@RequestParam("limit") int limit){
        page.setCurrentPage(currentPage);
        page.setPageNumber(limit);
        List<Blog> blogList=blogServic.getBlogByPage(page);
        blogServic.setBlogTotalRows(page);
        return new ResultHanler<>(0,"",page.getTotalRows(),blogList);
    }

    /**
     * 根据关键词查找文档并分页展示查找结果
     * @param page page类对象
     * @param spage   第几页
     * @param limit 每页行数
     * @return ResultHanler<List<Blog>>
     */
    @RequestMapping("/getBlogWithKeyWord")
    @ResponseBody
    public ResultHanler<List<Blog>> getBlogWithKeyWord(Page page,@RequestParam("page") int spage,@RequestParam("limit") int limit){
        page.setCurrentPage(spage);
        System.out.println(spage+"............."+limit);
        page.setPageNumber(limit);
        List<Blog> blogs=blogServic.getBlogWithKeyWord(page);
        blogServic.setBlogTotalWithKeyWord(page);
        return new ResultHanler<>(0,"",page.getTotalRows(),blogs);
    }

    /**
     * 删除博客并删除solr中的索引
     * @param blogCustom Blog类的扩展类对象
     * @return  blogs
     * @throws IOException
     * @throws SolrServerException
     */
     @RequestMapping("/deleteBlog")
    @ResponseBody
    public List<String> deleteBlog(BlogCustom blogCustom) throws IOException, SolrServerException {
         boolean flag= blogServic.deleteBlog(blogCustom);
         List<String> blogs=new ArrayList<>();
         if(flag){
             blogs.add("true");
             solrOperator.delDocument(String.valueOf(blogCustom.getId()));
         }else{
             blogs.add("false");
         }
         return  blogs;
     }

    /**
     * 插入博客并更新索引
     * @param blogCustom Blog类的扩展类对象
     * @return blogs
     * @throws IOException
     * @throws SolrServerException
     */
     @RequestMapping("/insertBlog")
    @ResponseBody
    public  List<String> insertBlog(BlogCustom blogCustom) throws IOException, SolrServerException {
         boolean flag= blogServic.insertBlog(blogCustom);
         List<String> blogs=new ArrayList<>();
         if(flag){
             solrOperator.addOrUpdateDocument(blogCustom);
             blogs.add("true");
         }else{
             blogs.add("false");
         }
         return  blogs;
     }

    /**
     * 根据id查找博客
     * @param blogCustom  log类的扩展类对象
     * @return BlogCustom
     */
     @RequestMapping("/getBlogById")
    @ResponseBody
    public BlogCustom getBlogById(BlogCustom blogCustom){
         return  blogServic.getBlogById(blogCustom);
     }


    @RequestMapping("/getBlogByIdToView")
    public void getBlogByIdToView(BlogCustom blogCustom,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BlogCustom blogCustom1=blogServic.getBlogById(blogCustom);
        request.setAttribute("blog",blogCustom1);
        request.getRequestDispatcher("/BlogView.jsp").forward(request,response);
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

    /**
     * 更新博客并更新索引
     * @param blogCustom
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    @RequestMapping("/updateBlog")
    @ResponseBody
    public List<String> updateBlog(BlogCustom blogCustom) throws IOException, SolrServerException {
        System.out.println(blogCustom.getId());
        boolean flag= blogServic.updateBlog(blogCustom);
        List<String> blogs=new ArrayList<>();
        if(flag){
            solrOperator.addOrUpdateDocument(blogCustom);
            blogs.add("true");
        }else{
            blogs.add("false");
        }
        return  blogs;
    }

    /**
     * 博客首页搜索框
     * @param blog_keywords
     * @return
     */
    @RequestMapping("/searchWithSolr")
    @ResponseBody
    public List<Blog> complexSearchWithSolr(@RequestParam("blog_keywords") String blog_keywords) throws IOException, SolrServerException {
         log.debug("blog_keywords is "+blog_keywords);
        List<Blog> blogs=solrOperator.searchDocument("blog_keywords",blog_keywords);
         return blogs;
    }
}
