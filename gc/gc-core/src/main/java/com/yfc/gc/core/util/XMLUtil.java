package com.yfc.gc.core.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.yfc.gc.core.consts.SystemConst;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class XMLUtil implements SystemConst {

    public static String toXml(Object obj){
        try{
            XStream xStream = new XStream(new DomDriver(PROJECT_CHARSET, new XmlFriendlyNameCoder("-_", "_")));
            xStream.autodetectAnnotations(true);
            xStream.ignoreUnknownElements();
            xStream.alias("xml", obj.getClass());
            return xStream.toXML(obj);
        }catch (Exception e) {
            log.error("参数:{}", obj);
            log.error(e.getMessage());
            return null;
        }
    }

    public static <T> T toObj(String xml, Class<T> res){
        try {
            XStream xStream = new XStream();
//            xStream.processAnnotations(res);
//            return (T) xStream.fromXML(xml);
            xStream.alias("xml", res);
            xStream.ignoreUnknownElements();//暂时忽略掉一些新增的字段
            return (T) xStream.fromXML(xml);
        }catch (Exception e){
            log.error("参数:{} , 类型:{}", xml, res);
            log.error(e.getMessage());
            return null;
        }
    }

    public static Map<String, Object> getMapFromXML(String xmlString) {
        Map<String, Object> map = new HashMap<String, Object>();
        try (InputStream is = getStringStream(xmlString)){

            //这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(is);

            //获取到document里面的全部结点
            NodeList allNodes = document.getFirstChild().getChildNodes();
            Node node;

            int i = 0;
            while (i < allNodes.getLength()) {
                node = allNodes.item(i);
                if (node instanceof Element) {
                    map.put(node.getNodeName(), node.getTextContent());
                }
                i++;
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            return map;
        }
        return map;

    }

    public static InputStream getStringStream(String sInputString) {
        ByteArrayInputStream tInputStringStream = null;
        if (sInputString != null && !sInputString.trim().equals("")) {
            tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
        }
        return tInputStringStream;
    }

}
