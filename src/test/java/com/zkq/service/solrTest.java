package com.zkq.service;

import com.sun.net.httpserver.HttpsServer;
import com.zkq.domain.Blog;
import com.zkq.utils.SolrOperator;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class solrTest {

    @Autowired
    HttpSolrClient solrClient;
    @Autowired
    SolrOperator solrOperator;

    @Test
    public  void complexSerachWithSolr() throws IOException, SolrServerException {
        //得到文档对象
        final SolrInputDocument doc = new SolrInputDocument();
//        doc.addField("id", "11");
//        doc.addField("name", "Amazon Kindle Paperwhite");
//        solrClient.add(doc);
        // 查询条件
        SolrQuery solrParams = new SolrQuery();
        solrParams.setQuery("title:java");
        // 开启高亮
//        solrParams.setHighlight(true);
//        solrParams.setHighlightSimplePre("<font color='red'>");
//        solrParams.setHighlightSimplePost("</font>");

        // 设置高亮的字段
//        solrParams.setParam("hl.fl", "title,article");
//        solrParams.addField("id");
//        solrParams.addField("name");
//        solrParams.setSort("id", ORDER.asc);
//        solrParams.setRows(numResultsToReturn);
        // SolrParams是SolrQuery的子类，
        //QueryResponse包装器，该包装器可用于访问结果文档和其他相关元数据
        QueryResponse queryResponse = solrClient.query(solrParams);
        // 获取查询的结果集合
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        System.out.println("Found " + solrDocumentList.getNumFound() + " documents");
        //遍历输出查询到的结果
        List<Blog> blogList=new ArrayList<>();
        for(SolrDocument document : solrDocumentList) {
            Blog blog=new Blog();
             String id = (String) document.getFirstValue("id");
             String title = (String) document.getFirstValue("title");
             String  article=(String) document.getFirstValue("article");
            String  zan=(String) document.getFirstValue("zan");
            String writetime=(String) document.getFirstValue("writetime");
            String label=(String) document.getFirstValue("label");
             blog.setId(Integer.valueOf(id));
             blog.setTitle(title);
             blog.setArticle(article);
             blogList.add(blog);
             blog.setZan(Integer.valueOf(zan));
             blog.setWritetime(LocalDate.parse(writetime));

        }
        System.out.println(blogList.toString());
        // (二)获取高亮的结果集
        // 第一个Map的键是文档的ID，第二个Map的键是高亮显示的字段
//        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
//        for (SolrDocument solrDocument : solrDocumentList) {
//            System.out.println("=====" + solrDocument.toString());
//            Map<String, List<String>> fieldsMap = highlighting.get(solrDocument.get("id"));
//            List<String> highName = fieldsMap.get("title");
//            List<String> highDesc = fieldsMap.get("article");
//            System.out.println("highname======" + highName);
//            System.out.println("highdesc=====" + highDesc);
//        }

        // (三) 将响应结果封装到Bean
//        List<Blog> products = queryResponse.getBeans(Blog.class);
//
//        System.out.println(products + "+++++++++++");
//        for (Blog blog : products) {
//            System.out.println(blog);
//        }
    }
    @Test
    public void addField() throws IOException, SolrServerException {
        //和solr服务器创建连接
        //参数：solr服务器的地址
        final String solrUrl = "http://127.0.0.1:8080/solr/collection2";
        HttpSolrClient solrClient=new HttpSolrClient.Builder(solrUrl).withConnectionTimeout(10000).withSocketTimeout(60000).build();
        //得到文档对象
        final SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", "11");
        doc.addField("title", "java学习笔记");
        doc.addField("label","java");
        solrClient.add(doc);
        solrClient.commit();

    }
    @Test
    public void search() throws IOException, SolrServerException {
      List<Blog> blogs=solrOperator.searchDocument("blog_keywords","java");
        System.out.println(blogs.toString());
    }

    @Test
    public void deletedocument() throws IOException, SolrServerException {
        solrOperator.delDocument("1");
    }

    @Test
    public void adddocument() throws IOException, SolrServerException {
        Blog blog=new Blog();
        blog.setId(1);
        blog.setArticle("ahahhahahhaha");
        blog.setTitle("java");
        blog.setLabel("java");
        blog.setZan(17);
        blog.setWritetime(LocalDate.parse("2018-06-06"));
        solrOperator.addOrUpdateDocument(blog);
    }

    @Test
    public void updatedocument() throws IOException, SolrServerException {
        Blog blog=new Blog();
        blog.setId(1);
        blog.setArticle("ahahhahahhaha");
        blog.setTitle("java");
        blog.setLabel("java");
        blog.setZan(17);
        blog.setWritetime(LocalDate.parse("2018-06-06"));
        solrOperator.addOrUpdateDocument(blog);
    }

}

