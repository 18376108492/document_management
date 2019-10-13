package com.itdan.test.slor;

import com.itdan.document.dao.FancytreeNodeMapper;
import com.itdan.document.dao.SearchMapper;
import com.itdan.document.domain.FancytreeNode;
import com.itdan.document.utils.result.SearchResult;
import com.itdan.test.BaseTest;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * solr搜索测试
 */
public class SlorTest extends BaseTest {

    @Autowired
    private FancytreeNodeMapper fancytreeNodeMapper;

    @Autowired
    private SearchMapper searchMapper;

    @Test
    public void testDemo01() throws Exception {
        //添加文本
        //创建一个SolrServer对象创建一个连接,参数solr服务的url
        SolrServer solrServer = new HttpSolrServer("http://192.168.13.128:8080/solr");
        //创建一个文档对象,SolrInputDocument对象
        //获取测试节点对象
        List<FancytreeNode> nodeList = fancytreeNodeMapper.getAllFile("D:/ge");
        //向文档中添加域，文档中必须包含一个ID域，所有的域的名称必须在schema.xml中定义
        for (FancytreeNode node:nodeList) {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", node.getId());
            document.addField("node_title", node.getTitle());
            document.addField("node_pId", node.getpId());
            document.addField("node_path", node.getPath());
            document.addField("node_name", node.getName());
            document.addField("disk_name", node.getDiskName());
            //把文档写入索引库
            solrServer.add(document);
        }
        //提交
        solrServer.commit();

    }

    @Test
    public void testDemo02() throws Exception {
        //删除索引
        SolrServer solrServer = new HttpSolrServer("http://192.168.13.128:8080/solr");
        //创建一个文档对象,SolrInputDocument对象
        SolrInputDocument document = new SolrInputDocument();
        solrServer.deleteById("1853359929");
        solrServer.deleteByQuery("id:1853359929");
        solrServer.commit();
    }

    @Test
    public void testDemo03() throws Exception {
        //查询测试
        //删除索引
        SolrServer solrServer = new HttpSolrServer("http://192.168.13.128:8080/solr/collection1");
        //创建查询对象
        SolrQuery solrQuery = new SolrQuery();
        //设置查询条件
        //solrQuery.set("q","*:*");
        solrQuery.set("q","id:1853359929");
        // solrQuery.set("q","disk_name:身边");
        // solrQuery.set("*:*");//查询所有
        //执行查询
        QueryResponse solrResponse = solrServer.query(solrQuery);
        //获取结果集
        SolrDocumentList solrDocuments = solrResponse.getResults();
        System.out.println("查询总数："+solrDocuments.getNumFound());
        System.out.println(solrDocuments.toString());
    }

    @Test
    public void testDemo04() throws Exception{
     //高级查询测试
        SolrServer solrServer = new HttpSolrServer("http://192.168.13.128:8080/solr/collection1");
        //创建查询对象
        SolrQuery solrQuery = new SolrQuery();
        //设置查询条件
        solrQuery.set("q","node_name:身边");
        //设置分页
        solrQuery.setStart(0);
        solrQuery.setRows(20);
        //设置默认查询域
        solrQuery.set("df","node_name");

        //设置高亮
        solrQuery.setHighlight(true);//开启高亮
        solrQuery.addHighlightField("node_name");//设置高亮字段
        //设置高亮前后缀
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
        solrQuery.setHighlightSimplePost("</em>");
        //执行查询
        QueryResponse response = solrServer.query(solrQuery);
        //取文档列表和总记录数
        SolrDocumentList documentList = response.getResults();
        System.out.println("查询总条数:"+documentList.getNumFound());//获取总计录数
        //遍历文档
        Map<String,Map<String,List<String>>>  highlightingMap= response.getHighlighting();
        for (SolrDocument document : documentList) {
            System.out.println("id:"+document.get("id"));
            //取高亮的结果
            List<String>highlightingList=highlightingMap.get(document.get("id")).get("node_name");//获取高亮集合

            //遍历高亮集合
            String title="";
            if(highlightingList!=null&&highlightingList.size()>0){
                title=highlightingList.get(0);
            }else {
                title=(String) document.get("node_name");
            }
            System.out.println("node_name:"+title);
            System.out.println("node_title:"+document.get("node_title"));
            System.out.println("node_pId:"+document.get("node_pId"));
            System.out.println("node_path:"+document.get("node_path"));
            System.out.println("disk_name:"+document.get("disk_name"));
            System.out.println("highlightingList:"+highlightingList);
        }
    }

    @Test
    public void testDemo05() throws Exception{
        //测试SearchMapper
        SolrServer solrServer = new HttpSolrServer("http://192.168.13.128:8080/solr/collection1");
        //创建查询对象
        SolrQuery solrQuery = new SolrQuery();
        //设置查询条件
        solrQuery.set("q","id:1853359929");
        //设置分页
        solrQuery.setStart(0);
        solrQuery.setRows(20);
        //设置默认查询域
        solrQuery.set("df","node_name");
        SearchResult searchResult= searchMapper.search(solrQuery);
        System.out.println(searchResult.toString());
     }


}

