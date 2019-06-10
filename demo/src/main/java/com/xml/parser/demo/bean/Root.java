package com.xml.parser.demo.bean;

import com.xml.parser.annotation.XmlElements;
import com.xml.parser.annotation.XmlRootElement;
import com.xml.parser.annotation.XmlText;

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
