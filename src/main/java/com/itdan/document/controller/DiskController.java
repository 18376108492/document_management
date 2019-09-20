package com.itdan.document.controller;

import com.itdan.document.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
