package com.xml.parser;

import com.xml.parser.annotation.XmlAttribute;
import com.xml.parser.annotation.XmlElement;
import com.xml.parser.annotation.XmlElements;
import com.xml.parser.annotation.XmlRootElement;
import com.xml.parser.annotation.XmlText;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {
    private static final String TAG = "XmlParser";

    private static XmlParser instance;

    private XmlParser() {
    }

    public static XmlParser getInstance() {
        if (instance == null) {
            synchronized (XmlParser.class) {
                if (instance == null) {
                    instance = new XmlParser();
                }
            }
        }
        return instance;
    }


    public <T> T fromXml(Class<T> clazz, InputStream source) throws Exception {
        T result = null;
        if (clazz.isAnnotationPresent(XmlRootElement.class)) {
            XmlRootElement xmlRootElement = clazz.getAnnotation(XmlRootElement.class);
            String name = xmlRootElement.name();
            SAXReader reader = new SAXReader();
            Document document = reader.read(source);
            Element rootElement = document.getRootElement();
            if (name.equals(rootElement.getName())) {
                result = paser(clazz, rootElement);
            } else {
                return null;
            }
        }
        return result;
    }

    public <T> T paser(Class clazz, Element element) throws Exception {
        if (element == null || clazz == null) {
            return null;
        }
        T result = (T) clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            Field field;
            for (int i = 0; i < fields.length; i++) {
                field = fields[i];
                field.setAccessible(true);
                if (field.isAnnotationPresent(XmlElements.class)) {
                    XmlElements xmlElements = field.getAnnotation(XmlElements.class);
                    String elementsName = xmlElements.name();
                    String name = elementsName.equals("") ? field.getName() : elementsName;
                    List<Element> elements = element.elements(name);
                    if (elements != null && elements.size() > 0) {
                        List list = new ArrayList(elements.size());
                        Class generic = xmlElements.generic();
                        for (int j = 0; j < elements.size(); j++) {
                            list.add(paser(generic, elements.get(j)));
                        }
                        field.set(result, list);
                    }

                } else if (field.isAnnotationPresent(XmlElement.class)) {
                    XmlElement xmlElement = field.getAnnotation(XmlElement.class);
                    String name = xmlElement.name();
                    String xmlElementsName = name.equals("") ? field.getName() : name;
                    field.set(result, paser(field.getType(), element.element(xmlElementsName)));
                } else if (field.isAnnotationPresent(XmlAttribute.class)) {
                    XmlAttribute xmlAttribute = field.getAnnotation(XmlAttribute.class);
                    String attrName = xmlAttribute.name();
                    String name = attrName.equals("") ? field.getName() : attrName;
                    Attribute attribute = element.attribute(name);
                    if (attribute != null) {
                        String value = attribute.getValue();
                        field.set(result, value);
                    }
                } else if (field.isAnnotationPresent(XmlText.class)) {
                    field.set(result, element.getText());
                }
            }
        }
        return result;
    }
}
