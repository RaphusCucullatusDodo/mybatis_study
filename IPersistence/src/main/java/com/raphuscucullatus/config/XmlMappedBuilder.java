package com.raphuscucullatus.config;

import com.raphuscucullatus.pojo.Configuration;
import com.raphuscucullatus.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.InputStream;
import java.util.List;


/**
 * 用于解析映射配置文件
 * @author raphus cucullatus
 * @version 2021/9/29 15:55
 * @since JDK11
 */
public class XmlMappedBuilder {

    private Configuration configuration;

    public XmlMappedBuilder() {
    }
    public XmlMappedBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 解析映射配置文件
     * @param inputStream
     * @throws DocumentException
     */
    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//select");
        String namespace = rootElement.attributeValue("namespace");
        for (Element element : list) {
            String id = element.attributeValue("id");
            String paramType = element.attributeValue("paramType");
            String resultType = element.attributeValue("resultType");
            String sql = element.getText();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setParamType(paramType);
            mappedStatement.setReturnType(resultType);
            mappedStatement.setSql(sql);
            String key = namespace+"."+id;
            //将sql语句以 statementid , mappedStatement 的形式存入 核心配置文件实体对象中
            configuration.getMappedStatementMap().put(key,mappedStatement);
        }
    }
}
