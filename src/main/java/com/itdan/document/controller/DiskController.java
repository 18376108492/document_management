package com.itdan.document.controller;

import com.itdan.document.domain.Disk;
import com.itdan.document.service.DocumentService;
import com.itdan.document.utils.common.JsonUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 磁盘控制
 */
@Controller
public class DiskController {

    @Autowired
    private DocumentService documentService;

    /**
     * 展示main主页
     */
    @RequestMapping(value = "/disk/main")
    public String  showMain(){
       return "main";
    }

    /**
     * 通过ajax自动加载磁盘信息
     * @return 磁盘列表（json）
     */
    @RequestMapping(value = "/disk/load_disk",method = RequestMethod.GET)
    @ResponseBody
    public String getDiskList(){
      List<Disk> diskList= documentService.getDiskList();
        System.out.println("diskList:"+diskList.toString());
        return JsonUtils.objectToJson(diskList);
    }

    /**
     * 根据磁盘ID获取该磁盘相应的信息
     * @param diskId 磁盘ID
     * @return
     */
    @RequestMapping(value = "/disk/getDiskById",method = RequestMethod.GET)
    public String getDiskById(@RequestParam(value = "diskId",defaultValue = "106001569")
                                          Integer diskId){//设置默认盘为光驱盘，不要问为什么就是喜欢
        Disk disk= documentService.getDiskById(diskId);
        return JsonUtils.objectToJson(disk);
    }




}
