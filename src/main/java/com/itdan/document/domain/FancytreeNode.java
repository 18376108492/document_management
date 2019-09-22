package com.itdan.document.domain;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;

/**
 * 树形节点封装类
 */
public class FancytreeNode implements Serializable {

    private Integer id;//节点ID
    private String title;//标题
    private String name;//节点名
    private String path;//全路径
    private Integer pId;//父类ID
    private Integer isHidden;//是否隐藏节点

    public FancytreeNode(){};

    public FancytreeNode(Integer id, String title, String name, String path, Integer pId, Integer isHidden) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.path = path;
        this.pId = pId;
        this.isHidden = isHidden;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Integer isHidden) {
        this.isHidden = isHidden;
    }
}
