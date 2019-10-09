package com.itdan.document.utils.result;

import java.io.Serializable;

/**
 * 搜索对象
 */
public class SolrResult implements Serializable {

    private String id;
    private String node_title;
    private String node_pId;
    private String node_path;
    private String node_name;
    private String disk_name;

    public SolrResult(){};

    public SolrResult(String id, String node_title, String node_pId, String node_path, String node_name, String disk_name) {
        this.id = id;
        this.node_title = node_title;
        this.node_pId = node_pId;
        this.node_path = node_path;
        this.node_name = node_name;
        this.disk_name = disk_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNode_title() {
        return node_title;
    }

    public void setNode_title(String node_title) {
        this.node_title = node_title;
    }

    public String getNode_pId() {
        return node_pId;
    }

    public void setNode_pId(String node_pId) {
        this.node_pId = node_pId;
    }

    public String getNode_path() {
        return node_path;
    }

    public void setNode_path(String node_path) {
        this.node_path = node_path;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public String getDisk_name() {
        return disk_name;
    }

    public void setDisk_name(String disk_name) {
        this.disk_name = disk_name;
    }

    @Override
    public String toString() {
        return "SolrResult{" +
                "id='" + id + '\'' +
                ", node_title='" + node_title + '\'' +
                ", node_pId='" + node_pId + '\'' +
                ", node_path='" + node_path + '\'' +
                ", node_name='" + node_name + '\'' +
                ", disk_name='" + disk_name + '\'' +
                '}';
    }
}
