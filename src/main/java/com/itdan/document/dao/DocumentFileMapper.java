package com.itdan.document.dao;


import com.itdan.document.domain.DocumentFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文件DAO层
 */
public interface DocumentFileMapper {

    /**
     * 添加文档对象
     * @param documentFile
     */
    void addDocumentFile(DocumentFile documentFile);


    /**
     * 根据父类文件名获取父类ID
     * @param parentName
     * @return
     */
    Integer getParentId(String parentName);


    /**
     * 根据父类ID获取相应的文件对象
     * @param parentId
     * @return
     */
    List<DocumentFile> getListByParentId(@Param(value = "parentId") Integer parentId);

    /**
     * 根据ID获取相应文档信息
     * @param id
     * @return
     */
    DocumentFile getFileById(@Param(value ="id")Integer id);

    /**
     * 更新文件对象
     * @param documentFile
     */
    void updataFile(DocumentFile documentFile);

    /**
     * 根据parent_point获取该节点下所有子节点总数
     * @param parent_point
     * @return
     */
    Long countNode(@Param(value ="parent_point")Integer parent_point);

    /**
     * 根据ID删除相应的文档信息
     * @param id
     */
    void removeFile(@Param(value ="id")Integer id);
}