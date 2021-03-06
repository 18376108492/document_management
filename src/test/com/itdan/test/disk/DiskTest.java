package com.itdan.test.disk;

import com.itdan.document.dao.DiskMapper;
import com.itdan.document.domain.Disk;
import com.itdan.document.service.DocumentService;
import com.itdan.document.utils.common.DocumentUtils;
import com.itdan.document.utils.common.JsonUtils;
import com.itdan.document.domain.FancytreeNode;
import com.itdan.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 获取本体所有文档测试代码
 */


public class DiskTest extends BaseTest {


    @Autowired
    private DocumentService documentService;
    @Autowired
    private DiskMapper diskMapper;

    public static void main(String[] args) {

//        FileSystemView 是 JFileChooser 的文件系统网关。
//        由于 JDK1.1 File API 不允许对诸如根分区、文件类型信息或隐藏文件位之类的信息进行访问，
//        此类被设计成能够直接获得尽可能多的特定于 OS 的文件系统信息。
//        Java Licensees 可能想要提供 FileSystemView 的不同实现来更好地处理给定操作系统。

        FileSystemView fileSystemView = FileSystemView.getFileSystemView();//获取当前文件系统类型

        // 获取此系统的所有根分区
        File[] files = fileSystemView.getRoots();
        System.out.println(" 返回此系统的所有根分区:" + files.toString());
        for (File f : files) {
            System.out.println("分区:" + f);
        }

        //列出可用的文件系统根
        File[] file1 = File.listRoots();
        for (File file2 : file1) {
            System.out.println("可用的文件系统根为：" + file2);
        }

        //getSystemDisplayName(File f)
        //在系统文件浏览器中显示的文件、目录或文件夹名。
        //fileSystemView.getSystemDisplayName()
        //获取系统所有盘符
        for (File f : File.listRoots()) {
            System.out.println("获取" + f.getName() + "系统盘名称：" + fileSystemView.getSystemDisplayName(f));
            System.out.println("获取" + f.getName() + "系统盘类型：" + fileSystemView.getSystemTypeDescription(f));
            System.out.println("获取" + f.getName() + "磁盘的总空间" + f.getTotalSpace());
            System.out.println("获取" + f.getName() + "磁盘的可用空间" + f.getFreeSpace());

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
    public void testGetDisk() throws Exception {
        //测试long强转int
        File[] files = File.listRoots();
        for (File file : files) {
            //int b,c;
            // b=Integer.valueOf(readableFileSize(file.getFreeSpace()));
            //c=Integer.valueOf(readableFileSize(file.getTotalSpace()));
            // (_file.getFreeSpace()/(1024*1024))/1024+"G")
            // System.out.println(file.getFreeSpace());
            System.out.println(file.getName() + "磁盘可用大小为：" + DocumentUtils.readableFileSize(file.getFreeSpace()));
            System.out.println(file.getName() + "磁盘总大小为：" + DocumentUtils.readableFileSize(file.getTotalSpace()));
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
    public void testDiskDemo01() throws Exception {
        //测试DiskDAO

    }

    @Test
    public void testDiskDemo02() throws Exception {
        //测试DiskService
        List<Disk> diskList = documentService.getDisk();
        //List<Disk> diskList= JsonUtils.jsonToList(o.toString(),Disk.class);
        System.out.println("diskList:" + diskList.toString());

        for (Disk disk : diskList) {
            System.out.println(disk.toString());
        }
    }

    @Test
    public void testDiskDemo03() throws Exception {
        //测试getDiskById
        Disk disk = documentService.getDiskById(105838662);
        System.out.println(disk.toString());
    }

    @Test
    public void testDiskDemo04() throws Exception {
        //测试获取根目录文件后，怎么去获取其相应的信息
        //现根据ID获取根目录信息
        Disk disk = documentService.getDiskById(105838662);
        String diskName = disk.getDiskName();
        System.out.println(diskName);//本地磁盘 (C:)
        File[] files = File.listRoots();
        System.out.println("Files:" + files);
        for (File file : files) {
            System.out.println("file:" + file);//C:\
        }
        //经过上面的测试我们可以得知，我们要截取字符串

    }

    @Test
    public void testDiskDemo05() throws Exception {
        //截取字符串,和拼接字符串
        Disk disk = documentService.getDiskById(105838662);
        String diskName = disk.getDiskName();//本地磁盘 (C:)
        // 变成  //C:\
        String newName = DocumentUtils.getDiskRoot(diskName);
        System.out.println("newName:" + newName);//newName://C
    }


    @Test
    public void testDiskDemo06() throws Exception {
        //测试，当知道磁盘的根目录文件后去获取该系统盘下的全部文件。

        //截取字符串,和拼接字符串
        Disk disk = documentService.getDiskById(105838662);
        String diskName = disk.getDiskName();//本地磁盘 (C:)
        // 变成  //C:\
        String newName = DocumentUtils.getDiskRoot(diskName);
        System.out.println("newName:" + newName);//newName://C
        File file = new File(newName);
        System.out.println("最后更改的时间：" + file.lastModified());
        //DocumentUtils.listAllFile(file);
        //将遍历出来的所有文件进行存储，并将父子关系区分好。
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String dateTime = df.format(new Date(file.lastModified()));
        System.out.println("dateTime:" + dateTime);
    }


    @Test
    public void testDiskDemo07() throws Exception {
        //获取父类文件的测试
        File file = new File("C:/Users/Administrator/Desktop/基础笔记PDF");
        System.out.println(file.getParentFile());
        System.out.println(file.getParentFile().getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        //  C:\Users\Administrator\Desktop
        //   Desktop
        //C:\Users\Administrator\Desktop\基础笔记PDF
        //C:\Users\Administrator\Desktop\基础笔记PDF
    }

    @Test
    public void testDiskDemo08() throws Exception {
        // 测试遍历节点
        File file = new File("C:/Users/Administrator/Desktop");
        //documentService.listAllFile(file);
        System.out.println(file.getAbsolutePath());//C:\Users\Administrator\Desktop
//        System.out.println((int)IDUtils.genItemId());
//        System.out.println(Math.abs((int)IDUtils.genItemId()));
//        System.out.println(IDUtils.genItemId());

    }

    @Test
    public void testDiskDemo09() throws Exception {
        //FSDirectory.open();
        String strPath = "C:/Users/Administrator/Desktop";
        //得到当前路径下的所有文件和文件夹
        // String[] dirs = Directory.GetDirectories(strPath);
        //String[] files = Directory.GetFiles(strPath);

        List<FancytreeNode> list = new ArrayList<>();
        Integer key = 0;
        String filePath = "C:/Users/Administrator/Desktop";
        Integer parentId = 0;
        DocumentUtils.getFile(list, key, filePath, parentId);

        for (int i = 0; i < list.size(); i++) {
            FancytreeNode tree = list.get(i);
            System.out.println("id:" + tree.getId() + "  parentId:" + tree.getpId() + "==" + tree.getPath());
        }

    }

    @Test
    public void testDiskDemo10() throws Exception {
        String strPath = "D:/java/学习资料";
        List<FancytreeNode> list = documentService.listAllFile(strPath);
        System.out.println(list);
    }


    @Test
    public void getFileTest() throws Exception {
        //树形控件测试
        String realPath = "C:/Users/Administrator/Desktop";
        int tempid = 1;
        int tempidd = 1;
        int temppid = 0;//id初始值
        int tempobjpid = 1;
        int tempobjid = 1;
        int tempobjppid = 1;
        String result = "[";

        File dir = new File(realPath);
        File[] fs = dir.listFiles();//获取该目录下所有文件

        for (int i = 0; i < fs.length; i++) {

            result += "{id:" + String.valueOf(temppid) +
                     ",pId:" + String.valueOf(temppid) +
                     ",name:\"" + fs[i].getName() + "\"},";//定义结果格式

            if (fs[i].isDirectory()) {//判断文件是否为目录
                String tempStr = "";
                File[] fb = fs[i].listFiles();//获取f[i]文件下的所有子文件
                for (int j = 0; j < fb.length; j++) {
                    tempStr = String.valueOf(tempid) + String.valueOf(tempobjid);
                    System.out.println("tempStr:"+tempStr);
                    result += "{id:" + tempStr + ",pId:" + tempobjpid + ",name:\"" + fb[j].getName() + "\"},";

                    if (fb[j].isDirectory()) {
                        String tempstrs = "";
                        String tempstrids = "";
                        File[] fd = fb[j].listFiles();
                        for (int k = 0; k < fd.length; k++) {
                            tempstrids = String.valueOf(tempobjpid) + String.valueOf(tempobjppid);
                            tempstrs = String.valueOf(tempid) + String.valueOf(tempobjid) + String.valueOf(tempid);
                            result += "{id:" + tempstrs + ",pId:" + tempstrids + ",name:\"" + fd[k].getName() + "\"},";
                            tempidd++;
                        }
                        tempidd = 1;
                        tempobjid++;
                        tempobjppid++;
                    }
                }
                tempobjid = 1;
            }
            tempid++;
            tempobjpid++;
        }
        result = result.substring(0, result.length() - 1);
        result += "]";
        System.out.println(result);
        System.out.println(result.length());
       /*String sstr=JSON.toJSON(result).toString();
       System.out.println("---"+sstr+"----");*/
      //String realPath = request.getSession().getServletContext().getRealPath("/Storageresources");
      //Test.readFileToJsp(realPath);

    }

    @Test
    public void testDiskDemo11() throws Exception{
     //测试磁盘查询控制

         List<Disk> diskList=documentService.getDiskList();
        System.out.println(JsonUtils.objectToJson("json"+diskList));
    }

    @Test
    public void testDiskDemo12() throws Exception{
        String diskName="D:/ge";
       //String json= documentService.getAllFile(diskName);
    }

}
