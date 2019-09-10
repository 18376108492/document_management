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


}