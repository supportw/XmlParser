package com.xml.paser.demo.bean;

import com.xml.paser.annotation.XmlText;

public class Category {
    @XmlText
    String name;

    @Override
    public String toString() {
        return name;
    }
}
