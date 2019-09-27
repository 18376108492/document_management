package com.itdan.document.service.impl;

import com.itdan.document.dao.DiskMapper;
import com.itdan.document.dao.DocumentFileMapper;
import com.itdan.document.dao.FancytreeNodeMapper;
import com.itdan.document.domain.Disk;
import com.itdan.document.domain.DocumentFile;
import com.itdan.document.domain.FancytreeNode;
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
import java.io.FileNotFoundException;
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
    private FancytreeNodeMapper fancytreeNodeMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${DISK_LIST_EXPIRE}")
    private Integer DISK_LIST_EXPIRE;//磁盘信息存储在redis中的时间期限

    @Value("${FILE_KEY}")
    private Integer FILE_KEY;//设置根目录id为1000

    @Value("${KEY_PARENT_ID}")
    private Integer KEY_PARENT_ID;//设置根目录父类ID为0

    @Value("${KEY_ZTREE}")
    private Integer KEY_ZTREE;//设置磁盘根目录key

    @Value("${KEY_ZTREE}")
    private Integer PARENT_ID;//设置根目录文件的ID

    @Value("${ZTREE_NODE}")
    private String ZTREE_NODE;//树形节点数据在redis中的名称

    @Value("${ZTREE_NODE_TIME}")
    private Integer ZTREE_NODE_TIME;//树形节点在redis中的缓存时间(1天)


    @Override
    public List<Disk> getDisk() {
        //获取当前系统文件类型
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        //根目录信息集合
        List<Disk> diskList = new ArrayList<>();
        //迭代根目录
        for (File f : File.listRoots()) {//获取个盘根目录名
            //创建磁盘对象
            Disk disk = new Disk();
            disk.setDiskId((int) IDUtils.genItemId());
            disk.setDiskName(fileSystemView.getSystemDisplayName(f));
            disk.setDiskType(fileSystemView.getSystemTypeDescription(f));
            disk.setFreeSpace(DocumentUtils.readableFileSize(f.getFreeSpace()));
            disk.setTotalSpace(DocumentUtils.readableFileSize(f.getTotalSpace()));
            addDisk(disk);
            diskList.add(disk);
        }
        return diskList;
    }

    @Override
    public List<Disk> getDiskList() {
        // 需要确定好个磁盘的ID
        // 先判断redis中是否存在磁盘信息，如果不存在就从数据库中查询。
        try {
            //从redis中获取数据
            String json = jedisClient.get("DISK_LIST");
            List<Disk> diskList = JsonUtils.jsonToList(json, Disk.class);
            if (StringUtils.isNotBlank(json)) {
                return diskList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //从数据中查询磁盘缓存数据
        List<Disk> diskList = diskMapper.getDiskList();

        //将从数据中查询的磁盘信息存储到redis中
        //查询数据库,把结果添加到redis缓存中
        try {
            //设置缓存的key和value值
            jedisClient.set("DISK_LIST", JsonUtils.objectToJson(diskList));
            //设置缓存过期时间
            jedisClient.expire("DISK_LIST", DISK_LIST_EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diskList;
    }

    @Override
    public DocumentReslut updateDisk(Integer diskId) {
        //更新磁盘的数据不是我们自己输入的，我们得知更新磁盘的ID
        //去获取该磁盘的根目录文件，在通过根目录去获取该磁盘的其他信息
        //再将更新后的数据保存到数据库中。并且同步redis中的磁盘相关信息

        if (diskId == null) {
            return DocumentReslut.build(400, "更新失败");
        }

        //根据ID获取要更新磁盘的信息，主要获取根目录
        Disk disk = diskMapper.getDiskById(diskId);
        if (disk != null) {
            String diskName = disk.getDiskName();//获取根目录
            String rootName = DocumentUtils.getDiskRoot(diskName);//拼接根目录名
            if (StringUtils.isNotBlank(rootName)) {
                //获取磁盘的可用空间
                File file = new File(rootName);
                String freeSpace = DocumentUtils.readableFileSize(file.getFreeSpace());
                //获取磁盘的总空间
                String totalSpace = DocumentUtils.readableFileSize(file.getTotalSpace());
                disk.setFreeSpace(freeSpace);
                disk.setTotalSpace(totalSpace);
                diskMapper.addDisk(disk);
                return DocumentReslut.ok("更新" + diskName + "成功。");
            }
            return DocumentReslut.build(400, "更新" + diskName + "失败");
        }
        return DocumentReslut.build(400, "更新失败");
    }

    @Override
    public Disk getDiskById(Integer diskId) {
        //根据ID获取相应磁盘信息
        if (diskId == null) {
            return new Disk();
        }
        Disk disk = diskMapper.getDiskById(diskId);
        return disk;
    }

    @Override
    public DocumentReslut addDisk(Disk disk) {
        //调用getDisk方法获取磁盘信息，再一一将其存入数据库中
        if (disk != null) {
            diskMapper.addDisk(disk);
            return DocumentReslut.ok();
        } else {
            return DocumentReslut.build(400, "添加对象操作失败");
        }
    }

    @Override
    public DocumentReslut addDocumentFile(DocumentFile documentFile) {
        if (documentFile != null) {
            documentFileMapper.addDocumentFile(documentFile);
            return DocumentReslut.ok();
        }
        return DocumentReslut.build(400, "文档添加失败");
    }

    @Override
    public DocumentFile GetDocument(String diskName) {

        if (diskName != null) {
            //获取磁盘根目录
            return null;
        }
        return new DocumentFile();
    }


    public List<FancytreeNode> listAllFile(String rootName) throws FileNotFoundException {
        if (StringUtils.isNotBlank(rootName)) {
            //只使用于遍历根目录
            //String  newName=DocumentUtils.getDiskRoot(rootName);
            //树形节点集合
            List<FancytreeNode> nodeList = new ArrayList<>();
            //设置根目录节点ID
            Integer key = FILE_KEY;
            //设置根目录父类ID为0，因为根目录没有父类
            Integer parentId = KEY_PARENT_ID;
            try {
                getAllFile(nodeList, key, rootName, parentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return nodeList;
        }
        return new ArrayList<>();
    }

    @Override
    public List<DocumentFile> getListByParentId(Integer parentId) {
        List<DocumentFile> list = new ArrayList<>();
        if (parentId != null) {
            list = documentFileMapper.getListByParentId(parentId);
            return list;
        }
        return list;
    }


    /**
     * 将遍历出的文件存储进数据库中
     *
     * @param f        文件
     * @param key      文件ID
     * @param parentId 文件父类ID
     * @return
     */
    public DocumentFile addDocument(File f, Integer key, Integer parentId) {
        //存储根目录
        DocumentFile documentFile = new DocumentFile();
        //设置文件ID
        documentFile.setId(key);
        //设置文件父类ID
        documentFile.setParentPoint(parentId);
        //设置文件是否为文件夹
        if (f.isDirectory()) {
            documentFile.setIsParent(1);
        } else {
            documentFile.setIsParent(0);
        }
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
     * 将树形节点存储到数据库中
     *
     * @param key      节点ID
     * @param file     节点文件
     * @param parentId 父类节点ID
     * @return
     */
    public FancytreeNode addTreeNode(Integer key, File file, Integer parentId) {
        String nodeName = file.getName();
        FancytreeNode node = new FancytreeNode(key, file.getName(), nodeName, file.getPath(), parentId, 0);
        fancytreeNodeMapper.addTreeNode(node);
        return node;
    }

    /**
     * 获取所有文件，存入数据库，并组成树形结构
     *
     * @param nodeList 树形节点集合
     * @param key      文件ID
     * @param fileNam  文件路径
     * @param parentId 父类节点ID
     */
    public void getAllFile(List<FancytreeNode> nodeList,
                           Integer key,
                           String fileNam,
                           Integer parentId) throws FileNotFoundException {

        File file = new File(fileNam);
        //使用树形节点集合来存储对象
        //判断文件是否存在
        if (!file.exists()) {
            throw new FileNotFoundException("该文件不存在");
        }
        //获取所有文件的同时，我们需要将文件的相关内容存储s进数据库中。

        //获取该文件路径下的 所有文件
        // File [] fs=file.listFiles();
        //遍历文件
        // for (File f:fs){

        //当为文件时
        if (file.isFile()) {
            String fileName = file.getName();//获取文件名
            String path = file.getAbsolutePath();//获取路径
            key = Math.abs((int) IDUtils.genItemId());//设置新ID
            //添加树形节点
            FancytreeNode tree = addTreeNode(key, file, parentId);
            nodeList.add(tree);
            //调用添加文件的方法
            DocumentFile document = addDocument(file, key, parentId);
            documentFileMapper.addDocumentFile(document);
            //返回
            return;
        }

        //当为文件夹时
        if (file.isDirectory()) {
            String name = file.getName();
            String path = file.getAbsolutePath();
            key = Math.abs((int) IDUtils.genItemId());//设置新ID
            //添加树形节点
            FancytreeNode tree = addTreeNode(key, file, parentId);
            nodeList.add(tree);
            //添加文件
            DocumentFile document = addDocument(file, key, parentId);
            documentFileMapper.addDocumentFile(document);
            String[] str = file.list();
            String parent = file.getParent();
            for (int i = 0; i < str.length; i++) {
                String s = str[i];
                String newFilePath = path + "\\" + s;//根据当前文件夹，拼接其下文文件形成新的路径
                getAllFile(nodeList, key, newFilePath, tree.getId());
            }
            // }
        }
    }

    @Override
    public List<FancytreeNode> getAllFile(String diskName)  {
        List<FancytreeNode> nodeList = new ArrayList<>();
        //判断传入的参数是否为空
        if (StringUtils.isNotBlank(diskName)) {
            //因为文件数据较多，为了缓降数据库压力，我们使用redis
            //先判断redis中是否存在磁盘信息，如果不存在就从数据库中查询。
            try {
                //先获取redis中缓存,存储到redis时磁盘名要与数据后缀名对上，表示某盘的数据
                String json= jedisClient.get(ZTREE_NODE+":"+diskName);
                if(StringUtils.isNotBlank(json)){
                    nodeList=JsonUtils.jsonToList(json,FancytreeNode.class);
                    return nodeList;
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            //根据路径名从数据库中获取节点数据
            nodeList = fancytreeNodeMapper.getAllFile(diskName);

            //先从数据库中获取数据信息，如果数据库为空就调用初始化方法，遍历磁盘获取数据
            //该方法是从电脑磁盘中获取信息，并将数据插入到数据中
            // 所以执行时间较长，所以一般只在我们做初始化时才调用
            if (nodeList == null || nodeList.size() <= 0) {
                try {
                    nodeList = listAllFile(diskName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //如果redis中节点数据为空，将查询出的树形节点，存储一份到redis中
            try {
                //向redis中添加缓存
                jedisClient.set(ZTREE_NODE+":"+diskName,JsonUtils.objectToJson(nodeList));
                 //设置过期时间
                jedisClient.expire(ZTREE_NODE+":"+diskName,ZTREE_NODE_TIME);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return nodeList;
    }
}
