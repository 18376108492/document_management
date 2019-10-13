package com.itdan.document.service.impl;

import com.itdan.document.dao.FancytreeNodeMapper;
import com.itdan.document.domain.FancytreeNode;
import com.itdan.document.service.SolrSerevice;
import com.itdan.document.utils.result.DocumentReslut;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * solr功能实现逻辑层实现类
 */
@Service
public class SolrServiceImpl implements SolrSerevice {

    @Autowired
    private FancytreeNodeMapper fancytreeNodeMapper;
    @Autowired
    private SolrServer solrServer;

    @Override
    public DocumentReslut importAllItem() {
            //查询所有的节点
            List<FancytreeNode> itemList = fancytreeNodeMapper.getAllNode();
             try {
                //将节点遍历
                for (FancytreeNode node : itemList) {
                    //将遍历的商品添加到文档中
                    //创建文档对象
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
                return DocumentReslut.ok();
                //返回成功
            } catch (Exception e) {
                e.printStackTrace();
                return DocumentReslut.build(400,"索引库数据添加失败");
            }
    }




}
