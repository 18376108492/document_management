package com.itdan.document.controller;

import com.itdan.document.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 磁盘控制层
 */
@Controller
public class DocumentFileCtroller {

    @Autowired
    private DocumentService documentService;

    @RequestMapping(value = "/document/add",method = RequestMethod.POST)
    public  String addTreeNode(){

        return "";
    }


}
