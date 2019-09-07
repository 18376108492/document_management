package com.itdan.test.disk;

import com.itdan.document.domain.Disk;
import com.itdan.document.service.DocumentService;
import com.itdan.document.utils.common.DocumentUtils;
import com.itdan.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.List;

/**
 * 获取本体所有文档测试代码
 */


public class DiskTest extends BaseTest {


    @Autowired
    private DocumentService documentService;

    public static void main(String [] args){

//        FileSystemView 是 JFileChooser 的文件系统网关。
//        由于 JDK1.1 File API 不允许对诸如根分区、文件类型信息或隐藏文件位之类的信息进行访问，
//        此类被设计成能够直接获得尽可能多的特定于 OS 的文件系统信息。
//        Java Licensees 可能想要提供 FileSystemView 的不同实现来更好地处理给定操作系统。

        FileSystemView fileSystemView=FileSystemView.getFileSystemView();//获取当前文件系统类型

        // 获取此系统的所有根分区
        File[] files= fileSystemView.getRoots();
        System.out.println(" 返回此系统的所有根分区:"+files.toString());
        for (File f:files){
            System.out.println("分区:"+f);
        }

        //列出可用的文件系统根
        File [] file1=File.listRoots();
        for (File file2:file1){
            System.out.println("可用的文件系统根为："+file2);
        }

        //getSystemDisplayName(File f)
        //在系统文件浏览器中显示的文件、目录或文件夹名。
       //fileSystemView.getSystemDisplayName()
        //获取系统所有盘符
          for (File f:File.listRoots()){
              System.out.println("获取"+f.getName()+"系统盘名称："+fileSystemView.getSystemDisplayName(f));
              System.out.println("获取"+f.getName()+"系统盘类型："+fileSystemView.getSystemTypeDescription(f));
              System.out.println("获取"+f.getName()+"磁盘的总空间"+f.getTotalSpace());
              System.out.println("获取"+f.getName()+"磁盘的可用空间"+f.getFreeSpace());

          }
        /**
         * 执行结果为：
         *
         *  返回此系统的所有根分区:[Ljava.io.File;@277050dc
         * 分区:C:\Users\Administrator\Desktop
         *
         * 可用的文件系统根为：C:\
         * 可用的文件系统根为：D:\
         * 可用的文件系统根为：E:\
         * 可用的文件系统根为：F:\
         * 可用的文件系统根为：G:\
         *
         * 获取系统盘名称：本地磁盘 (C:)
         * 获取系统盘类型：本地磁盘
         * 获取磁盘的总空间64427651072
         * 获取磁盘的可用空间40785723392
         * 获取系统盘名称：本地磁盘 (D:)
         * 获取系统盘类型：本地磁盘
         * 获取磁盘的总空间139460603904
         * 获取磁盘的可用空间13791928320
         * 获取系统盘名称：本地磁盘 (E:)
         * 获取系统盘类型：本地磁盘
         * 获取磁盘的总空间139460603904
         * 获取磁盘的可用空间19176902656
         * 获取系统盘名称：本地磁盘 (F:)
         * 获取系统盘类型：本地磁盘
         * 获取磁盘的总空间134088749056
         * 获取磁盘的可用空间16658743296
         * 获取系统盘名称：
         * 获取系统盘类型：CD 驱动器
         * 获取磁盘的总空间0
         * 获取磁盘的可用空间0
         */

    }



    @Test
    public void testGetDisk() throws Exception{
        //测试long强转int
        File[] files=File.listRoots();
        for(File file:files){
            //int b,c;
           // b=Integer.valueOf(readableFileSize(file.getFreeSpace()));
            //c=Integer.valueOf(readableFileSize(file.getTotalSpace()));
            // (_file.getFreeSpace()/(1024*1024))/1024+"G")
           // System.out.println(file.getFreeSpace());
            System.out.println(file.getName()+"磁盘可用大小为："+ DocumentUtils.readableFileSize(file.getFreeSpace()));
            System.out.println(file.getName()+"磁盘总大小为："+ DocumentUtils.readableFileSize(file.getTotalSpace()));
//            磁盘可用大小为：37.9 GB
//            磁盘总大小为：60 GB
//            磁盘可用大小为：12.8 GB
//            磁盘总大小为：129.9 GB
//            磁盘可用大小为：17.9 GB
//            磁盘总大小为：129.9 GB
//            磁盘可用大小为：15.5 GB
//            磁盘总大小为：124.9 GB
//            磁盘可用大小为：0
//            磁盘总大小为：0

        }
        //测试失败，使用String类型来接受磁盘大小
    }


    @Test
    public void testDiskDemo() throws Exception{

        //测试DiskService
      List<Disk> diskList= documentService.GetDisk();

      for (Disk disk:diskList){
          System.out.println(disk);
      }

    }
}
