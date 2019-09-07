package com.itdan.document.service;

import com.itdan.document.domain.Disk;
import com.itdan.document.domain.DocumentFile;
import com.itdan.document.utils.result.DocumentReslut;

import java.util.List;

/**
 * 业务逻辑层接口
 */
public interface DocumentService {


    /**
     * 获取本地磁盘
     * @return
     */
    DocumentReslut GetDisk();

    /**
     *  添加磁盘信息到数据库中
     * @param disk 磁盘对象
     */
    DocumentReslut addDisk(Disk disk);

    /**
     * 根据相应的磁盘名获取该磁盘的文件
     * @param diskName 磁盘名
     * @return 该磁盘的全部文档
     */
    DocumentReslut GetDocument(String diskName);

}
