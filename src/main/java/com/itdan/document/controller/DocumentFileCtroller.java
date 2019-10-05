package com.itdan.document.controller;

import com.itdan.document.dao.FancytreeNodeMapper;
import com.itdan.document.domain.Date;
import com.itdan.document.service.DocumentService;
import com.itdan.document.utils.common.JsonUtils;
import com.itdan.document.utils.result.DocumentReslut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 磁盘控制层
 */
@Controller
public class DocumentFileCtroller {

    @Autowired
    private DocumentService documentService;


    /**
     * 新增子节点操作
     * @param list
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/document/add",method = RequestMethod.POST)
    @ResponseBody
    public  String addTreeNode(@RequestBody String[] list) {
            //获取传入新子节点的父类ID
            String pId=list[0];
            //获取传入节点的名称
            String name=list[1];
            DocumentReslut documentReslut= documentService.addNode(pId,name);
            return JsonUtils.objectToJson(documentReslut);

    }

    /**
     * 更新节点信息
     * @param date
     * @return
     */
    @RequestMapping(value = "/document/update",method = RequestMethod.POST)
    @ResponseBody
    public  String updateTreeNodeName(@RequestBody Date date){
          //更新节点信息
           DocumentReslut documentReslut= documentService.updateTreeNodeName(date);
           return JsonUtils.objectToJson(documentReslut);
    }

    /**
     * 删除节点
     * @param date
     * @return
     */
    @RequestMapping(value = "/document/remove",method = RequestMethod.POST)
    @ResponseBody
    public String removeZtreeNode(@RequestBody Date date){
           //删除节点
          DocumentReslut documentReslut= documentService.removeZtreeNode(date);
          return JsonUtils.objectToJson(documentReslut);
    }

    /**
     * 拖拽树形节点
     * @param list
     * @return
     */
    @RequestMapping(value = "/document/drop",method = RequestMethod.POST)
    @ResponseBody
    public String dropZtreeNode(@RequestBody String[] list){
       DocumentReslut documentReslut=documentService.dropZtreeNode(list);
        return JsonUtils.objectToJson(documentReslut);
    }


}
