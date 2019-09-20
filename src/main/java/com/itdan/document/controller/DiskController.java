package com.itdan.document.controller;

import com.itdan.document.domain.Disk;
import com.itdan.document.service.DocumentService;
import com.itdan.document.utils.common.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
       return  JsonUtils.objectToJson(diskList);
    }

}
