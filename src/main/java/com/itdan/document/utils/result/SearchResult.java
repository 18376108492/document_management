package com.itdan.document.utils.result;

import java.util.List;

/**
 * 文档搜索结果集
 */
public class SearchResult {

    private long recourdCount;//查询的文件总数
    private int totalPages;//总页数
    private List<SolrResult> nodeList;

    public long getRecourdCount() {
        return recourdCount;
    }

    public void setRecourdCount(long recourdCount) {
        this.recourdCount = recourdCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<SolrResult> getItemList() {
        return nodeList;
    }

    public void setItemList(List<SolrResult> itemList) {
        this.nodeList = itemList;
    }
}
