package com.itdan.document.dao;


import com.itdan.document.domain.DocumentFile;

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


}