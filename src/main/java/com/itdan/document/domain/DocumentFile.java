package com.itdan.document.domain;

import com.itdan.document.utils.common.IDUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件实体类
 */
public class DocumentFile implements Serializable {
    private Integer id;//文件编号

    private Integer parentPoint;//父类节点

    private Integer isParent;//是否为父节点,0表示不是父类节点,1表示为父类节点

    private String document;//文件内容

    private String fileSize;//文件大小

    private String fileAddr;//文件地址

    private String fileName;//文件名

    private Date fileDate;//文件日期

    private Integer backups;//是否备份，1表示已经备份，0表示没有备份

    private Date changeDate;//文件修改日期

    private String diskName;//该文件属于某磁盘的名

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

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document == null ? null : document.trim();
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
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

    public String getDiskName() {
        return diskName;
    }

    public void setDiskName(String diskName) {
        this.diskName = diskName;
    }

    @Override
    public String toString() {
        return "DocumentFile{" +
                "id=" + id +
                ", parentPoint=" + parentPoint +
                ", isParent=" + isParent +
                ", document='" + document + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileAddr='" + fileAddr + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileDate=" + fileDate +
                ", backups=" + backups +
                ", changeDate=" + changeDate +
                ", diskName='" + diskName + '\'' +
                '}';
    }
}