package com.zkq.utils;

import com.zkq.domain.Blog;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * solr常用方法的封装
 * @author zkq15
 *
 */
@Slf4j
@Component
public class SolrOperator {

    @Autowired
    HttpSolrClient httpSolrClient;
    /**
     * @param doc是solr的索引文档
     */
    SolrInputDocument doc = new SolrInputDocument();

    /**
     * 向solr索引文档添加索引或者更新索引
     * @param blog  更新索引文档参数从blog对象中获取
     * @throws IOException
     * @throws SolrServerException
     */
    public void addOrUpdateDocument(Blog blog) throws IOException, SolrServerException {
        doc.addField("id", String.valueOf(blog.getId()));
        doc.addField("title", blog.getTitle());
        doc.addField("label", blog.getLabel());
        doc.addField("zan", String.valueOf(blog.getZan()));
        doc.addField("article", blog.getArticle());
        doc.addField("writetime", blog.getWritetime().format(DateTimeFormatter.ISO_LOCAL_DATE));
        httpSolrClient.add(doc);
        UpdateResponse rspcommit = httpSolrClient.commit();
        log.debug("add document result is " + rspcommit.getStatus() + "Qtime:" + rspcommit.getQTime());
    }
    /**
     *从索引文档中查找
     * @param key  搜索需要的键
     * @param value  搜索需要的值
     * @return   bloglist
     * @throws IOException
     * @throws SolrServerException
     */
    public List<Blog> searchDocument(String key,String value) throws IOException, SolrServerException {
        SolrQuery solrParams = new SolrQuery();
        solrParams.set("q",key+":"+value);
//        // 开启高亮组件或用query.setParam("hl", "true")
//        solrParams.setHighlight(true);
//        // 高亮字段
//        solrParams.addHighlightField("title");
//        //标记，高亮关键字前缀
//        solrParams.setHighlightSimplePre("<font color='red'>");
//        //后缀
//        solrParams.setHighlightSimplePost("</font>");
        QueryResponse queryResponse = httpSolrClient.query(solrParams);
//        NamedList list = (NamedList) queryResponse.getResponse().get("highlighting");
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        List<Blog> blogList=new ArrayList<>();
        for(SolrDocument document : solrDocumentList) {
            String id = (String) document.getFirstValue("id");
            String title = (String) document.getFirstValue("title");
            String  article=(String) document.getFirstValue("article");
            String  zan=(String) document.getFirstValue("zan");
            String writetime=(String) document.getFirstValue("writetime");
            String label=(String) document.getFirstValue("label");
            Blog blog=new Blog(Integer.valueOf(id),0,0,title,article,LocalDate.parse(writetime),null,Integer.valueOf(zan),label);
            blogList.add(blog);
        }
        return blogList;
    }

    /**
     * 删除索引
     * @param id 根据主键删除索引
     * @throws IOException
     * @throws SolrServerException
     */
    public void delDocument(String id) throws IOException, SolrServerException {
        UpdateResponse rsp = httpSolrClient.deleteById(id);
        httpSolrClient.commit();
        log.debug("delete document result is "+rsp.getStatus()+"Qtime:"+rsp.getQTime());
    }



}
