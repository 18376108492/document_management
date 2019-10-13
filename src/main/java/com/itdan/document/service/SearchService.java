package com.itdan.document.service;

import com.itdan.document.utils.result.SearchResult;

/**
 * 文档搜索逻辑层接口
 */
public interface SearchService {


    /**
     * 搜索操作
     * @param keyWord
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    SearchResult seachFile(String keyWord, int page, int rows) throws Exception;

}
