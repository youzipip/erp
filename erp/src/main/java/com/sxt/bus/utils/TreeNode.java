package com.sxt.bus.utils;

public class TreeNode {

    private Integer id;
    private String title;
    private Integer parentId;

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

    public Integer getParentId() {
        return parentId;
    }

    public TreeNode(Integer id, String title, Integer parentId) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
