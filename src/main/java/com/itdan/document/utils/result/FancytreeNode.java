package com.itdan.document.utils.result;

import java.io.Serializable;

/**
 * 树形节点封装类
 */
public class FancytreeNode implements Serializable {

    private Integer key;//节点ID
    private String title;//标题
    private boolean folder;//是否为文件夹
    private String path;//全路径
    private Integer parentId;//父类ID

    public FancytreeNode(Integer key, String title, boolean folder, String path, Integer parentId) {
        this.key = key;
        this.title = title;
        this.folder = folder;
        this.path = path;
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFolder() {
        return folder;
    }

    public void setFolder(boolean folder) {
        this.folder = folder;
    }
}
