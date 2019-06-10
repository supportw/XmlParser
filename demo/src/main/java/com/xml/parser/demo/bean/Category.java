package com.xml.parser.demo.bean;

import com.xml.parser.annotation.XmlText;

public class Category {
    @XmlText
    String name;

    @Override
    public String toString() {
        return name;
    }
}
