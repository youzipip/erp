package com.sxt.sys.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

/**
 * DTree的json对象构造器
 */
public class DTreeNode {

    private Integer id;
    private Integer parentId;
    private String title;
    private String checkArr = "0";
    private Boolean spread;  //是否展开
    private List<DTreeNode> children = new ArrayList<>();  //子节点

    public DTreeNode(Integer id, Integer parentId, String title, Boolean spread) {
        this.id = id;
        this.parentId = parentId;
        this.title = title;
        this.spread = spread;
    }

    public DTreeNode(Integer id, Integer parentId, String title, String checkArr, Boolean spread) {
        this.id = id;
        this.parentId = parentId;
        this.title = title;
        this.checkArr = checkArr;
        this.spread = spread;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCheckArr() {
        return checkArr;
    }

    public void setCheckArr(String checkArr) {
        this.checkArr = checkArr;
    }

    public Boolean getSpread() {
        return spread;
    }

    public void setSpread(Boolean spread) {
        this.spread = spread;
    }

    public List<DTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<DTreeNode> children) {
        this.children = children;
    }
}