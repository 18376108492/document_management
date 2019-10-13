package com.itdan.document.dao;

import com.itdan.document.domain.FancytreeNode;
import com.itdan.document.utils.result.SearchResult;
import com.itdan.document.utils.result.SolrResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 搜索功能实现层
 */
@Repository
public class SearchMapper {
    @Autowired
    private SolrServer solrServer;

    /**
     * 根据查询条件，查询数据库
     * query 查询条件
     * @return
     */
    public SearchResult search(SolrQuery query) throws SolrServerException {
        //根据查询条件，查询索引库
        QueryResponse response= solrServer.query(query);
        //获取查询结果
        SolrDocumentList documentList=response.getResults();

        //遍历商品和显示高亮
        List<SolrResult> resultList =new ArrayList<>();
        //获取高亮
        Map<String,Map<String,List<String>>> highlightMap=response.getHighlighting();
        for (SolrDocument document:documentList){
            //创建查询后的商品对象
            SolrResult node=new SolrResult();

            //为商品添加信息
            node.setId((String) document.get("id"));
            node.setDisk_name((String) document.get("disk_name"));
            node.setNode_title((String) document.get("node_title"));
            node.setNode_name((String) document.get("node_name"));
            node.setNode_path((String) document.get("node_path"));
            node.setNode_pId((Integer) document.get("node_pId"));
            //添加高亮
            if(highlightMap!=null){
                List<String> highlightList =highlightMap.
                        get(document.get("id")).get("node_name");
                String title="";
                if(highlightList!=null&&highlightList.size()>0){
                    title=highlightList.get(0);
                }else {
                    title=(String) document.get("node_name");
                }
                //设置高亮后的标题
                node.setNode_title(title);
            }
            //添加对象
            resultList.add(node);
        }

        //创建返回对象
        SearchResult searchResult=new SearchResult();
        //获取查询结果总数
        searchResult.setRecourdCount(documentList.getNumFound());
        searchResult.setNodeList(resultList);
        //返回查询结果
        return searchResult;

    }
}
