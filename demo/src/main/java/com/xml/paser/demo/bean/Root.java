package com.xml.paser.demo.bean;

import com.xml.paser.annotation.XmlElements;
import com.xml.paser.annotation.XmlRootElement;
import com.xml.paser.annotation.XmlText;

import java.util.List;

@XmlRootElement(namespace = "", name = "Root")
public class Root {
    @XmlElements(generic = Category.class, name = "category")
    List<Category> categoryList;
    @XmlText
    String name;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
