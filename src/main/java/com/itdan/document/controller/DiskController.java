package com.itdan.document.controller;

import com.itdan.document.domain.Disk;
import com.itdan.document.domain.FancytreeNode;
import com.itdan.document.service.DocumentService;
import com.itdan.document.utils.common.CookieUtils;
import com.itdan.document.utils.common.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * 磁盘控制
 */
@Controller
public class DiskController {

    @Autowired
    private DocumentService documentService;

    @Value("${INIT_DISK_TOKEN}")
    private String INIT_DISK_TOKEN;

    /**
     * 展示main主页
     */
    @RequestMapping(value = "/disk/main")
    public String showMain() {
        return "main";
    }

    /**
     * 通过ajax自动加载磁盘信息
     *
     * @return 磁盘列表（json）
     */
    @RequestMapping(value = "/disk/load_disk", method = RequestMethod.GET)
    @ResponseBody
    public String getDiskList() {
        List<Disk> diskList = documentService.getDiskList();
        System.out.println("diskList:" + diskList.toString());
        return JsonUtils.objectToJson(diskList);
    }


    /**
     * 根据磁盘ID获取该磁盘相应的信息
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/disk/getDiskByName",
                    method = RequestMethod.GET
                    )
    public ModelAndView getDiskById(HttpServletRequest request,
                                    HttpServletResponse response) throws  Exception{
        //获取需要文件信息的磁盘名，将其先存储到cookie中
        String diskName=new String(request.getParameter("diskName").getBytes("iso-8859-1"),"UTF-8");
       ModelAndView view=new ModelAndView();
       view.addObject(INIT_DISK_TOKEN,diskName);
        //然后跳转至文件界面
        view.setViewName("document");
        return view;
    }


    @RequestMapping(value = "/disk/init_disk", method = RequestMethod.GET)
    @ResponseBody
    public String initDisk(HttpServletRequest request,
                           HttpServletResponse response) throws FileNotFoundException {
        //获取要查询的磁盘名
       String diskName=request.getParameter(INIT_DISK_TOKEN);
        System.out.println("diskName:"+diskName);
       if (StringUtils.isNotBlank(diskName)){
           List<FancytreeNode> nodeList = documentService.listAllFile(diskName);
           return JsonUtils.objectToJson(nodeList);
       }
       return JsonUtils.objectToJson("");
    }


}

