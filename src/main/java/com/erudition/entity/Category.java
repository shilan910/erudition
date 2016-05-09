package com.erudition.entity;

import java.util.List;

/**
 * Created by sl on 16-5-9.
 */
public class Category {

    private int id;
    private String name;
    private List<Category> children;


    public Category(int id , String name , List<Category> children){
        this.id = id;
        this.name = name;
        this.children = children;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }
}
