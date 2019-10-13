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
    @RequestMapping(value = "/disk/getDiskByName"
                    )
    public String getDiskById(HttpServletRequest request,
                                    HttpServletResponse response) throws  Exception{
        //获取需要文件信息的磁盘名，将其先存储到cookie中
        String diskName=new String(request.getParameter("diskName").getBytes("iso-8859-1"),"UTF-8");
        request.setAttribute("diskName",diskName);
        return "document";
    }


    /**
     * 根据传入的路径，获取该路径下的所有文件
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/disk/init_disk", method = RequestMethod.GET)
    @ResponseBody
    public String initDisk(HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        //获取要查询的磁盘名
       // String diskName=new String(request.getParameter("diskName").getBytes("iso-8859-1"),"UTF-8");
       // String diskName="D:/java/学习资料";//由于遍历磁盘根路径太久，所以将磁盘路径写死，以便迭代数据
        String diskName="D:/ge";//由于遍历磁盘根路径太久，所以将磁盘路径写死，以便迭代数据测试
       if (StringUtils.isNotBlank(diskName)){
           //去数据库中获取已经存储在数据库中数据
           return JsonUtils.objectToJson(documentService.getAllFile(diskName));
       }
       return JsonUtils.objectToJson("400");
    }


}

