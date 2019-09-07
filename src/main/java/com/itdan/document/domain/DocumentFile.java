package com.itdan.document.domain;

import java.util.Date;

/**
 * 文件实体类
 */
public class DocumentFile {
    private Integer id;//文件编号

    private Integer parentPoint;//父类节点

    private Integer childrenPoint;//子类节点

    private String document;//文件内容

    private Double fileSize;//文件大小

    private String fileAddr;//文件地址

    private String fileName;//文件名

    private Date fileDate;//文件日期

    private Integer backups;//是否备份，1表示已经备份，0表示没有备份

    private Date changeDate;//文件修改日期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentPoint() {
        return parentPoint;
    }

    public void setParentPoint(Integer parentPoint) {
        this.parentPoint = parentPoint;
    }

    public Integer getChildrenPoint() {
        return childrenPoint;
    }

    public void setChildrenPoint(Integer childrenPoint) {
        this.childrenPoint = childrenPoint;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document == null ? null : document.trim();
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileAddr() {
        return fileAddr;
    }

    public void setFileAddr(String fileAddr) {
        this.fileAddr = fileAddr == null ? null : fileAddr.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Date getFileDate() {
        return fileDate;
    }

    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }

    public Integer getBackups() {
        return backups;
    }

    public void setBackups(Integer backups) {
        this.backups = backups;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }
}