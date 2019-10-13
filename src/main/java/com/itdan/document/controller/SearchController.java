package com.itdan.document.controller;

import com.itdan.document.service.SearchService;
import com.itdan.document.utils.result.SearchResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 搜索控制层
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Value("${SEARCH_PAGE}")
    private Integer SEARCH_PAGE;
    @Value("${SEARCH_ROW}")
    private Integer SEARCH_ROW;

    /**
     * 搜索功能
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/node/search",method = RequestMethod.GET)
    public String nodeSearch(HttpServletRequest request) throws Exception{
        String keyword=new String(request.getParameter("keyword").getBytes("iso-8859-1"),"UTF-8");
          if(StringUtils.isBlank(keyword)){
              keyword="";
          }
              SearchResult searchResult= searchService.seachFile(keyword,SEARCH_PAGE,SEARCH_ROW);
              request.setAttribute("SEARCH_RESULT",searchResult);
              return "search";
    }



}
