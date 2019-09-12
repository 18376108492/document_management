package com.itdan.document.utils.common;

import com.itdan.document.domain.DocumentFile;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
            if('C'==name[i]){
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



}
