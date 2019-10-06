package com.itdan.document.utils.common;

import com.itdan.document.domain.FancytreeNode;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文档工具类
 */
public class DocumentUtils {


    /**
     * 格式化文件大小
     * 参考：https://stackoverflow.com/a/5599842/1253611
     *
     * @param size byte
     * @return readable file size
     */
    public static String readableFileSize(long size) {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }


    /**
     * 改变从redis中获取出的文档数据
     * @param json
     * @return
     */
    public static String chageValueType(String json){
        StringBuilder sb=new StringBuilder(json);
        String str02=sb.reverse().toString();
        //截取字符串
        int index = DocumentUtils.getCharacterPosition(str02, "]", 3);
        //System.out.println("str02:"+str02);
        String str03= str02.substring(index);
       // System.out.println(str03);
        sb=new StringBuilder(str03);
        sb.deleteCharAt(str03.length()-1);
        String str04= sb.reverse().toString();
       return str04;
    }

    /**
     * 获取某个符号出现在字符串中的指定次数的下标位置
     * @param string
     * @param tagerString
     * @param index
     * @return
     */
    public static int getCharacterPosition(String string,String tagerString,int index){
        //这里是获取"/"符号的位置
        Matcher slashMatcher = Pattern.compile(tagerString).matcher(string);
        int mIdx = 0;
        while(slashMatcher.find()) {
            mIdx++;
            //当"/"符号第三次出现的位置
            if(mIdx == index){
                break;
            }
        }
        return slashMatcher.start();
    }

    /**
     * json的替换
     * @param json
     * @return
     */
    public static String replaceJson(String json){
        //将json字符串中的\全替换成空格，将前后数据的["删掉
        json.replace('\'',' ');
        StringBuilder stringBuilder=new StringBuilder(json);
        json.trim();
        stringBuilder.deleteCharAt(0);
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.deleteCharAt(stringBuilder.length()-2);
        //stringBuilder.delete(0,1);
        //stringBuilder.delete(stringBuilder.length()-2,stringBuilder.length()-1);
        json=stringBuilder.toString();
        return json;
    }

    /**
     * 从磁盘数据库中查询出磁盘信息，并且拼接为，文件对象可以调用的根目录
     * @param diskName 磁盘名
     * @return 磁盘根目录
     */
    public static  String getDiskRoot(String diskName){
        if(StringUtils.isBlank(diskName)){
         return "";//如果磁盘名为空就返回空字符串
        }
        //截取字符串,和拼接字符串
        // 变成  //C:\
        String newName="//";
        char[]name=diskName.toCharArray();
        System.out.println("char_name:"+Arrays.toString(name));
        for (int i = 0; i <name.length-1 ; i++) {
            System.out.println("name[i]"+name[i]);
            if(Character.isLowerCase( name[i]) || Character.isUpperCase( name[i])){
                //判断字符是否为字母，仅对win系统下的磁盘适用，在Linux下还要修改算法
                newName+=name[i];
                System.out.println("name:"+name[i]);
                for(int j=1;j<name.length-i;j++){
                    if(name[i+j]!=')'){
                        newName+=name[i+j];
                    }
                    System.out.println("name+1:"+name[i+j]);
                }
            }
        }
        System.out.println("newName:"+newName);//newName://C
        newName+="\\";//如果要输出“\”要使用转义字符"\\"
        return newName;
    }

    /**
     * long格式的时间转化为String格式
     * @param time
     * @return
     */
    public static String readableTimeSize(long time){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String dateTime=df.format(new Date(time));
        return dateTime;
    }

    /**
     * 获取文件，并组成树形结构
     * @param list 数据存储集合
     * @param key  节点ID
     * @param filepath 文件路径
     * @param parentid 父类节点ID
     * @throws FileNotFoundException
     */
    public static void getFile(List<FancytreeNode>list,Integer key,String filepath,int parentid) throws FileNotFoundException {
        File file = new File(filepath);
        //1.判断文件
        if(!file.exists()){
            throw new FileNotFoundException("文件不存在");
        }
        //2.是文件该怎么执行
        if(file.isFile()){
            String name = file.getName();
            String path = file.getAbsolutePath();
            String nodeName=name+key;
            FancytreeNode tree = new FancytreeNode(key++,name,nodeName,path,parentid,1);
            list.add(tree);
            return ;//跳至for循环处
        }
        //3.获取文件夹路径下面的所有文件递归调用；
        if(file.isDirectory()){
            String name = file.getName();
            String path = file.getAbsolutePath();
            Integer id=key+parentid;
            String nodeName=name+key;
            FancytreeNode tree = new FancytreeNode(key++,name,nodeName,path,parentid,1);
            list.add(tree);
            String[] str = file.list();
            String parent = file.getParent();
            for (int i = 0;i<str.length;i++){
                String s = str[i];
                String newFilePath = path+"\\"+s;//根据当前文件夹，拼接其下文文件形成新的路径
                getFile(list,key,newFilePath,tree.getId());
            }
        }
    }

    /**
     * 获取文档路径
     * @param oldPath
     * @param tagerPath
     * @return
     */
    public static String getNodePath(String oldPath,String tagerPath){
        StringBuilder stringBuilder=new StringBuilder(oldPath);
        String addr02=stringBuilder.reverse().toString();
        int index=DocumentUtils.getCharacterPosition(addr02,"/",1);
        String addr03=addr02.substring(index);
        stringBuilder=new StringBuilder(addr03);
        String addr04=stringBuilder.reverse().toString();
        return addr04+tagerPath;
    }

}
