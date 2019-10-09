package com.itdan.document.service.impl;


import com.itdan.document.dao.SolrMapper;
import com.itdan.document.service.SolrService;
import com.itdan.document.utils.result.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 文档搜索逻辑层实现类
 */
public class SolrServiceImpl implements SolrService {


    @Autowired
    private SolrMapper solrMapper;

    @Override
    public SearchResult seachFile(String keyWord, int page, int rows) throws Exception {
        SolrQuery query = new SolrQuery();
        //创建查询条件
        query.set("q", keyWord);
        if (page <= 0) {
            page = 1;
        }
        //分页设置
        query.setStart((page - 1) * rows);
        query.setRows(rows);
        //默认搜索域
        query.set("df", "node_name");
        //开启高亮显示
        query.setHighlight(true);
        query.addHighlightField("node_name");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");

        //查询
        SearchResult searchResult = solrMapper.search(query);
        long recourCount = searchResult.getRecourdCount();
        //计算总页数
        int totalPage = (int) recourCount / rows;
        if (recourCount % rows > 0) {
            totalPage++;
        }
        //添加返回结果
        searchResult.setTotalPages(totalPage);
        return searchResult;
    }
}
