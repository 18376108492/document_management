package com.itdan.test.slor;

import com.itdan.document.dao.FancytreeNodeMapper;
import com.itdan.document.domain.FancytreeNode;
import com.itdan.test.BaseTest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * solr搜索测试
 */
public class SlorTest extends BaseTest {

    @Autowired
    private FancytreeNodeMapper fancytreeNodeMapper;

    @Test
    public void testDemo01() throws Exception{
        //添加文本
            //创建一个SolrServer对象创建一个连接,参数solr服务的url
            SolrServer solrServer = new HttpSolrServer("http://192.168.203.128:8080/solr");
            //创建一个文档对象,SolrInputDocument对象
            SolrInputDocument document = new SolrInputDocument();
            //获取测试节点对象
           FancytreeNode  node=fancytreeNodeMapper.getNodeById(1853359929);
            //向文档中添加域，文档中必须包含一个ID域，所有的域的名称必须在schema.xml中定义
            document.addField("id", node.getId());
            document.addField("node_title", node.getTitle());
            document.addField("node_pId", node.getpId());
            document.addField("node_path", node.getPath());
            document.addField("node_name", node.getName());
            document.addField("disk_name", node.getDiskName());
            //把文档写入索引库
            solrServer.add(document);
            //提交
            solrServer.commit();
    }

    @Test
    public void testDemo() throws Exception{
        //删除索引
        SolrServer solrServer = new HttpSolrServer("http://192.168.203.128:8080/solr");
        //创建一个文档对象,SolrInputDocument对象
        SolrInputDocument document = new SolrInputDocument();
        solrServer.deleteById("1853359929");
        solrServer.deleteByQuery("id:1853359929");
        solrServer.commit();
    }
}

