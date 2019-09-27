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


}