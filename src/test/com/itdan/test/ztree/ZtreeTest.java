package com.itdan.test.ztree;


import com.itdan.document.dao.DocumentFileMapper;
import com.itdan.document.dao.FancytreeNodeMapper;
import com.itdan.document.domain.FancytreeNode;
import com.itdan.document.service.DocumentService;
import com.itdan.document.utils.common.DocumentUtils;
import com.itdan.document.utils.common.JsonUtils;
import com.itdan.document.utils.jedis.JedisClient;
import com.itdan.document.utils.result.DocumentReslut;
import com.itdan.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

//树形节点测试类
public class ZtreeTest extends BaseTest {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private FancytreeNodeMapper fancytreeNodeMapper;

    @Autowired
    private DocumentFileMapper documentFileMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${ZTREE_NODE}")
    private String ZTREE_NODE;

    @Test
    public void testDemo01() throws Exception {
        //测试遍历树形节点返回结果
        String diskName = "D:/ge";
        List<FancytreeNode> nodeList = fancytreeNodeMapper.getAllFile(diskName);
        //System.out.println(nodeList);
        System.out.println(JsonUtils.objectToJson(nodeList));
        //        for (FancytreeNode node:nodeList){
//            System.out.print(node.toString());
//        }
    }

    @Test
    public void testDemo02() throws Exception {
        //加载树形节点如数据库测试
        String diskName = "D:/ge";
        List<FancytreeNode> list = documentService.getAllFile(diskName);
        String json=JsonUtils.objectToJson(list);
        //["[{\"id\":596440901,\"title\":\"0\",\"name\":\"ge\",
        //将json字符串中的\全替换成空格，将前后数据的["删掉
//        json.replace('\'', ' ');
//        StringBuilder stringBuilder = new StringBuilder(json);
//        json.trim();
//        stringBuilder.deleteCharAt(0);
//        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        //stringBuilder.delete(0,1);
        //stringBuilder.delete(stringBuilder.length()-2,stringBuilder.length()-1);
//        json = stringBuilder.toString();
        List<FancytreeNode> nodeList = fancytreeNodeMapper.getAllFile(diskName);
        String str = JsonUtils.objectToJson(nodeList);
       System.out.println("json:" + json);
        System.out.println("str:" + str);
        System.out.println(str.equals(json));
    }

    @Test
    public void testDemo03() throws Exception {
        String diskName = "D:/ge";
        //redis中的数据获取
        List<String> json = jedisClient.hvals(ZTREE_NODE + ":" + diskName);
        String s = JsonUtils.objectToJson(json);
//        s.replaceAll("\\\'", "");
//        StringBuilder stringBuilder = new StringBuilder(s);
//        stringBuilder.deleteCharAt(0);
//        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//        String newJson = stringBuilder.toString();
        List<FancytreeNode> nodeList = fancytreeNodeMapper.getAllFile(diskName);
        String str = JsonUtils.objectToJson(nodeList);

        System.out.println();
        //System.out.println("newJson:" + newJson);
        System.out.println("newJson:"+s);
        System.out.println("str:" + str);
        System.out.println(str.equals(json));
    }

    @Test
    public void testDemo04() throws Exception {
        //String替换测试
        String json = "[{\"id\":596440901,\"title\":\"0\",\"name\":\"ge\",\"path\":\"D:\\ge\",\"pId\":0,\"isHidden\":null,\"d";
        String str = json.replaceAll("\\\'", "");
        System.out.println(str);
    }

    @Test
    public void testDemo05() throws Exception {
        String diskName = "D:/ge";
        List<String> json = jedisClient.hvals(ZTREE_NODE + ":" + diskName);
        String str01= json.toString();
        StringBuilder sb=new StringBuilder(str01);
        String str02=sb.reverse().toString();
        //截取字符串
        int index = DocumentUtils.getCharacterPosition(str02, "]", 2);
        //System.out.println("str02:"+str02);
        String str03= str02.substring(index);
        //System.out.println(str03);
        StringBuilder stringBuilder=new StringBuilder(str03);
        stringBuilder.deleteCharAt(str03.length()-1);
        String str04= stringBuilder.reverse().toString();

        List<FancytreeNode> nodeList = fancytreeNodeMapper.getAllFile(diskName);
        String str = JsonUtils.objectToJson(nodeList);
        System.out.println("str04:"+str04);
        System.out.println("str:"+str);
        System.out.println(str04.equals(str));
//             String s = JsonUtils.objectToJson(json);
//             s.replaceAll("\\\'","");
//             System.out.println(json.toString());
             //System.out.println(s);
    }

    @Test
    public void testDemo06() throws Exception {
        //测试符号出现指定次数的下标位置
        String str = "aaaa]ada]sdef]aaa";
        String t = "]";
        int index = DocumentUtils.getCharacterPosition(str, t, 2);
        int index1 = DocumentUtils.getCharacterPosition(str, t, 3);
        System.out.println(index);//8
        System.out.println(index1);//13
        int a=str.length();
        String s= str.substring(index+1,a);
        System.out.println(s);
        StringBuilder stringBuilder=new StringBuilder(str);
        System.out.println(stringBuilder.reverse().toString());

    }

    @Test
    public void testDemo07() throws Exception{
        //添加节点测试
        String pid="596525363";
        String name="新节点";
        DocumentReslut documentReslut= documentService.addNode(pid,name);
        System.out.println(documentReslut.getMsg());
    }

    @Test
    public void testDemo08() throws Exception{
     //测试某节点的子节点总数
        Integer pid=596525363;
        Long num= documentFileMapper.countNode(pid);
        System.out.println("num:"+num);
        System.out.println(num>0);
    }

}
