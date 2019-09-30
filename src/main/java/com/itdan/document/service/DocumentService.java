package com.itdan.document.service;

import com.itdan.document.domain.Disk;
import com.itdan.document.domain.DocumentFile;
import com.itdan.document.utils.result.DocumentReslut;
import com.itdan.document.domain.FancytreeNode;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * 业务逻辑层接口
 */
public interface DocumentService  {


    /**
     * 获取本地磁盘
     *
     * @return
     */
    List<Disk> getDisk();

    /**
     * 从数据中读取磁盘信息
     *
     * @return
     */
    List<Disk> getDiskList();

    /**
     * 根据ID更新相应的磁盘信息
     *
     * @param diskId 磁盘ID
     * @return
     */
    DocumentReslut updateDisk(Integer diskId);

    /**
     * 根据ID获取相应的磁盘信息
     *
     * @param diskId 磁盘ID
     * @return
     */
    Disk getDiskById(Integer diskId);

    /**
     * 添加磁盘信息到数据库中
     *
     * @param disk 磁盘对象
     */
    DocumentReslut addDisk(Disk disk);


    /**
     * 添加磁盘根目录下的所有文件到数据库中
     *
     * @param documentFile 文档对象
     * @return
     */
    DocumentReslut addDocumentFile(DocumentFile documentFile);

    /**
     * 根据相应的磁盘名获取该磁盘的文件
     *
     * @param diskName 磁盘名
     * @return 该磁盘的全部文档
     */
    DocumentFile GetDocument(String diskName);

    /**
     * 输入指定文件夹名，将该文件夹下的所有文件遍历出来
     * @param rootName 文件路径
     * @return  树形节点集合
     * @throws FileNotFoundException
     */
    List<FancytreeNode> listAllFile(String  rootName) throws FileNotFoundException;

    /**
     * 根据父类ID获取相应的文档对象
     * @param parentId
     * @return
     */
    List<DocumentFile> getListByParentId(Integer parentId);

    /**
     * 根据磁盘路径，从数据库中获取该磁盘所有相关文件（树形节点)
     * @param diskName 磁盘名
     * @return
     */
    List getAllFile(String diskName);

    /**
     *
     * @param pId
     * @param name
     * @return
     */
    DocumentReslut  addNode(String pId,String name);
}