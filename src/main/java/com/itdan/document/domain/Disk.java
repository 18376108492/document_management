package com.itdan.document.domain;

import java.io.Serializable;

/**
 * 磁盘实体类
 */
public class Disk implements Serializable {
    private Integer diskId;

    private String diskName; //磁盘名

    private String diskType;//磁盘类型

    private String freeSpace;//磁盘可用空间

    private String totalSpace;//磁盘总空间数

    public Integer getDiskId() {
        return diskId;
    }

    public void setDiskId(Integer diskId) {
        this.diskId = diskId;
    }

    public String getDiskName() {
        return diskName;
    }

    public void setDiskName(String diskName) {
        this.diskName = diskName == null ? null : diskName.trim();
    }

    public String getDiskType() {
        return diskType;
    }

    public void setDiskType(String diskType) {
        this.diskType = diskType == null ? null : diskType.trim();
    }

    public String getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(String freeSpace) {
        this.freeSpace = freeSpace == null ? null : freeSpace.trim();
    }

    public String getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(String totalSpace) {
        this.totalSpace = totalSpace == null ? null : totalSpace.trim();
    }

    @Override
    public String toString() {
        return "Disk{" +
                "diskId=" + diskId +
                ", diskName='" + diskName + '\'' +
                ", diskType='" + diskType + '\'' +
                ", freeSpace='" + freeSpace + '\'' +
                ", totalSpace='" + totalSpace + '\'' +
                '}';
    }
}