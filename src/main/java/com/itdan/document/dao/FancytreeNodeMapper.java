package com.itdan.document.dao;

import com.itdan.document.domain.FancytreeNode;

/**
 * 树形节点DAO层
 */
public interface FancytreeNodeMapper {

    /**
     * 添加树形节点到数据库中
     * @param fancytreeNode
     */
    void addTreeNode(FancytreeNode fancytreeNode);
}
