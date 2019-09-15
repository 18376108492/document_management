package com.itdan.document.controller;

import com.itdan.document.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 磁盘控制层
 */
@Controller
public class DocumentFileCtroller {

    @Autowired
    private DocumentService documentService;



}
