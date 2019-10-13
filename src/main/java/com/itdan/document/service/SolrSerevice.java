package com.itdan.document.service;

import com.itdan.document.utils.result.DocumentReslut;

/**
 * solr功能实现逻辑层接口
 */
public interface SolrSerevice {

    /**
     * 初始化索引库
     * @return
     */
     DocumentReslut importAllItem();
}
