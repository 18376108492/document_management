package com.itdan.document.dao;

import com.itdan.document.domain.FancytreeNode;

import java.util.List;

/**
 * 树形节点DAO层
 */
public interface FancytreeNodeMapper {

    /**
     * 添加树形节点到数据库中
     * @param fancytreeNode
     */
    void addTreeNode(FancytreeNode fancytreeNode);

    /**
     * 根据磁盘路径，从数据库中获取该磁盘所有相关文件（树形节点)
     * @param diskName 磁盘名
     * @return
     */
    List<FancytreeNode> getAllFile(String diskName);
}
