package com.itdan.document.service.impl;

import com.itdan.document.dao.DiskMapper;
import com.itdan.document.domain.Disk;
import com.itdan.document.domain.DocumentFile;
import com.itdan.document.service.DocumentService;
import com.itdan.document.utils.common.DocumentUtils;
import com.itdan.document.utils.common.IDUtils;
import com.itdan.document.utils.result.DocumentReslut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务逻辑实现类
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DiskMapper diskMapper;

    @Override
    public DocumentReslut GetDisk() {

        //获取当前系统文件类型
        FileSystemView fileSystemView=FileSystemView.getFileSystemView();
        //根目录信息集合
        List<Disk> diskList=new ArrayList<>();
        //迭代根目录
        for (File f: File.listRoots()){//获取个盘根目录名
             //创建磁盘对象
            Disk disk=new Disk();
            disk.setDiskId((int)IDUtils.genItemId());
            disk.setDiskName(fileSystemView.getSystemDisplayName(f));
            disk.setDiskType(fileSystemView.getSystemTypeDescription(f));
            disk.setFreeSpace(DocumentUtils.readableFileSize(f.getFreeSpace()));
            disk.setTotalSpace(DocumentUtils.readableFileSize(f.getTotalSpace()));
            diskList.add(disk);
        }
      return   DocumentReslut.ok(diskList);
    }


    @Override
    public DocumentReslut addDisk(Disk disk) {
        //调用getDisk方法获取磁盘信息，再一一将其存入数据库中
        if(disk!=null){
            diskMapper.addDisk(disk);
            return DocumentReslut.ok();
        }else {
            return DocumentReslut.build(400,"添加对象操作失败");
        }
    }

    @Override
    public DocumentReslut GetDocument(String diskName) {
        return null;
    }


}
