package com.itdan.document.controller;

import com.itdan.document.domain.Disk;
import com.itdan.document.domain.FancytreeNode;
import com.itdan.document.service.DocumentService;
import com.itdan.document.utils.common.CookieUtils;
import com.itdan.document.utils.common.JsonUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
                    method = RequestMethod.GET,
                    produces = MediaType.TEXT_PLAIN_VALUE+";charset=UTF-8")
    public String getDiskById(HttpServletRequest request,
                              HttpServletResponse response) throws  Exception{//设置默认盘，不要问为什么设这就是喜欢
        //获取需要文件信息的磁盘名，将其先存储到cookie中
        String diskName=new String(request.getParameter("diskName").getBytes("iso-8859-1"),"UTF-8");
        CookieUtils.setCookie(request, response, INIT_DISK_TOKEN, diskName);
        //然后跳转至文件界面
        return "document";
    }


    @RequestMapping(value = "/disk/init_disk", method = RequestMethod.GET)
    @ResponseBody
    public String initDisk(HttpServletRequest request) throws FileNotFoundException {
        //获取要查询的磁盘名
        String diskName = CookieUtils.getCookieValue(request, INIT_DISK_TOKEN);
        System.out.println("diskName:" + diskName);
        List<FancytreeNode> nodeList = documentService.listAllFile(diskName);
        return JsonUtils.objectToJson(nodeList);
    }


}
