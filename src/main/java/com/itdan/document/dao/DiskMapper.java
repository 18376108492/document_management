package com.itdan.document.dao;


import com.itdan.document.domain.Disk;

/**
 * 磁盘持久化接口
 */
public interface DiskMapper {

    /**
     * 添加磁盘信息
     * @param disk
     */
    void addDisk(Disk disk);




}