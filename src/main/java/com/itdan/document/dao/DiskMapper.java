package com.itdan.document.dao;


import com.itdan.document.domain.Disk;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 磁盘持久化接口
 */
public interface DiskMapper {

    /**
     * 添加磁盘信息
     * @param disk
     */
    void addDisk(Disk disk);

    /**
     * 从数据库中获取磁盘列表
     * @return
     */
    List<Disk> getDiskList();

    /**
     * 根据ID获取相应的磁盘信息
     * @param diskId 磁盘ID
     * @return
     */
   Disk getDiskById(@Param(value = "diskId") Integer diskId);

}