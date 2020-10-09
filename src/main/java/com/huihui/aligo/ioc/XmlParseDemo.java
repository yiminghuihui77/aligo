package com.huihui.aligo.ioc;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * 使用dom4j解析XML文件Demo
 *
 * @author minghui.y BG358486
 * @create 2020-10-07 16:09
 **/
public class XmlParseDemo {


    public static void main(String[] args) throws DocumentException {
        InputStream is = XmlParseDemo.class.getClassLoader().getResourceAsStream("MySpring.xml");

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(is);

        Element root = document.getRootElement();

        parseXml(root);

    }

    /**
     *
     * @param element
     */
    public static void parseXml(Element element) {



        System.out.println("getName：" + element.getName());
        System.out.println("getTextTrim：" + element.getTextTrim());
        System.out.println("getStringValue：" + element.getStringValue());

        List<Attribute> attributes = element.attributes();
        for (Attribute attribute : attributes) {
            System.out.println(attribute.getName() + "--" + attribute.getText());
        }

        Iterator<Element> iterable = element.elementIterator();

        while (iterable.hasNext()) {
            Element e = iterable.next();
            parseXml(e);
        }

    }


}
