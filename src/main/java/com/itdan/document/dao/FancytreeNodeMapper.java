package com.itdan.document.dao;

import com.itdan.document.domain.FancytreeNode;
import org.apache.ibatis.annotations.Param;

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
    List<FancytreeNode> getAllFile(@Param(value = "diskName") String diskName);

    /**
     * 根据id删除节点
     * @param id
     */
    void removeNode(@Param(value = "id") Integer id);

    /**
     * 清空指定磁盘下的树形节点
     * @param diskName
     */
    void deleteAllFile(@Param(value = "diskName") String diskName);

    /**
     * 根据ID获取相应的节点信息
     * @param id
     * @return
     */
    FancytreeNode getNodeById(@Param(value = "id") Integer id);

    /**
     * 更新节点信息
     * @param fancytreeNode
     */
    void updateNode(FancytreeNode fancytreeNode);

    /**
     * 获取所有节点
     */
    List<FancytreeNode> getAllNode();
}
