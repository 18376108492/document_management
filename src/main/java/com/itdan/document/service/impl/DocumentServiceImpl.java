package com.itdan.document.service.impl;

import com.itdan.document.dao.DiskMapper;
import com.itdan.document.dao.DocumentFileMapper;
import com.itdan.document.domain.Disk;
import com.itdan.document.domain.DocumentFile;
import com.itdan.document.service.DocumentService;
import com.itdan.document.utils.common.DocumentUtils;
import com.itdan.document.utils.common.IDUtils;
import com.itdan.document.utils.common.JsonUtils;
import com.itdan.document.utils.jedis.JedisClient;
import com.itdan.document.utils.result.DocumentReslut;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 业务逻辑实现类
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DiskMapper diskMapper;
    @Autowired
    private DocumentFileMapper documentFileMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${DISK_LIST_EXPIRE}")
    private Integer DISK_LIST_EXPIRE;//磁盘信息存储在redis中的时间期限

    @Override
    public List<Disk> getDisk() {
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
            addDisk(disk);
            diskList.add(disk);
        }
      return   diskList;
    }

    @Override
    public List<Disk> getDiskList() {
        //需要确定好个磁盘的ID
        //先判断redis中是否存在磁盘信息，如果不存在就从数据库中查询。
        try{
            //从redis中获取数据
            String json=jedisClient.get("DISK_LIST");
            List<Disk> diskList=JsonUtils.jsonToList(json,Disk.class);
            if(StringUtils.isNotBlank(json)){
               return diskList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //从数据中查询磁盘缓存数据
        List<Disk>diskList= diskMapper.getDiskList();

        //将从数据中查询的磁盘信息存储到redis中
        //查询数据库,把结果添加到redis缓存中
        try {
            //设置缓存的key和value值
            jedisClient.set("DISK_LIST",JsonUtils.objectToJson(diskList));
            //设置缓存过期时间
            jedisClient.expire("DISK_LIST",DISK_LIST_EXPIRE);
        }catch (Exception e){
            e.printStackTrace();
        }
        return diskList;
    }

    @Override
    public DocumentReslut updateDisk(Integer diskId) {
        //更新磁盘的数据不是我们自己输入的，我们得知更新磁盘的ID
        //去获取该磁盘的根目录文件，在通过根目录去获取该磁盘的其他信息
        //再将更新后的数据保存到数据库中。并且同步redis中的磁盘相关信息

        if (diskId==null) {
        return  DocumentReslut.build(400,"更新失败");
        }

        //根据ID获取要更新磁盘的信息，主要获取根目录
        Disk disk=diskMapper.getDiskById(diskId);
        if(disk!=null){
            String diskName=disk.getDiskName();//获取根目录
            String rootName=DocumentUtils.getDiskRoot(diskName);//拼接根目录名
            if(StringUtils.isNotBlank(rootName)){
                //获取磁盘的可用空间
                File file=new File(rootName);
                String freeSpace= DocumentUtils.readableFileSize(file.getFreeSpace());
                //获取磁盘的总空间
                String totalSpace=DocumentUtils.readableFileSize(file.getTotalSpace());
                disk.setFreeSpace(freeSpace);
                disk.setTotalSpace(totalSpace);
                diskMapper.addDisk(disk);
                return DocumentReslut.ok("更新"+diskName+"成功。");
            }
            return DocumentReslut.build(400,"更新"+diskName+"失败");
        }
        return DocumentReslut.build(400,"更新失败");
    }

    @Override
    public Disk getDiskById(Integer diskId) {
        //根据ID获取相应磁盘信息
      if(diskId==null) {
          return new Disk();
      }
          Disk disk= diskMapper.getDiskById(diskId);
          return disk;
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
    public DocumentReslut addDocumentFile(DocumentFile documentFile) {
        if (documentFile!=null) {
            documentFileMapper.addDocumentFile(documentFile);
          return DocumentReslut.ok();
        }
        return DocumentReslut.build(400,"文档添加失败");
    }

    @Override
    public DocumentFile GetDocument(String diskName) {

        if (diskName!=null) {
            //获取磁盘根目录
            String rootName = DocumentUtils.getDiskRoot(diskName);
            if (StringUtils.isNotBlank(rootName)) {
               return null;
            }
        }
        return new DocumentFile();
    }




    /**
     * 输入指定文件夹名，将该文件夹下的所有文件遍历出来
     * @param rootName 文件夹名
     */
    public void listAllFile(File rootName){
        if (rootName.exists()) {//判断文件是否存在
            //获取该文件路径下的 所有文件
            //获取所有文件的同时，我们需要将文件的相关内容存储s进数据库中。
            // 根节点没有父类节点，我们需要将其父节点设置为0
            //存储根目录
            DocumentFile documentFile= addDocument(rootName);
            documentFile.setId(10000);//设置父类节点ID为10000
            documentFile.setIsParent(1);//表示是为父节点
            documentFile.setParentPoint(0); //根目录代表最上层，父节节点设置为0
            documentFileMapper.addDocumentFile(documentFile);
            int parentId=10000;//设置第二层目录的父类节点ID
            //获取所有文件
            getAllDocument(rootName,parentId);
        }
    }

    @Override
    public List<DocumentFile> getListByParentId(Integer parentId) {
       List<DocumentFile> list=new ArrayList<>();
       if (parentId!=null) {
           list=documentFileMapper.getListByParentId(parentId);
           return list;
       }
        return list;
    }

    /**
     * 存文件
     * @param f
     */
    public DocumentFile addDocument(File f){
        //存储根目录
        DocumentFile documentFile=new DocumentFile();
        documentFile.setId(Math.abs((int)IDUtils.genItemId()));
        documentFile.setFileName(f.getName());
        documentFile.setFileAddr(f.toString());
        //子节点的不用设置了，我表设计错误啦，懒得改了。
        //根目录代表最上层，父节节点设置为0
        // documentFile.setParentPoint(0);
        documentFile.setDocument(f.getName());//内容就像保存名字吧
        documentFile.setFileDate(new Date(f.lastModified()));//获取文件最后一次更改的时间
        documentFile.setBackups(0);//0表示没有备份，1表示已经备份
        documentFile.setFileSize(DocumentUtils.readableFileSize(f.length()));
        documentFile.setChangeDate(new Date());
        return documentFile;
    }

    /**
     * 获取文件夹下的所有文件
     * @param fileName
     * @param parentId
     */
    public void getAllDocument(File fileName,Integer parentId){
        //遍历根目录下的文件
        File[]fs= fileName.listFiles();
        if(fs!=null) {
            for (File f : fs) {
                System.out.println(f);
                //将各节点分别保存至数据库中
                //保存文件信息
                DocumentFile document= addDocument(f);
                //判断文件是否为文件夹
                //保存父类节点
                document.setParentPoint(parentId);

                if (f.isDirectory()){
                    //如果文件是文件夹，表示该文件为父类节点
                    document.setIsParent(1);//表示是为父节点
                    documentFileMapper.addDocumentFile(document);
                    //继续遍历文件
                    //获取父类节点ID
                   // parentId=document.getId();
                    getAllDocument(f,parentId);
                }

            }
        }
    }

}
