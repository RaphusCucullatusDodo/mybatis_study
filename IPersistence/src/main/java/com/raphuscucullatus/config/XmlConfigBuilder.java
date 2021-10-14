package com.raphuscucullatus.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.raphuscucullatus.io.Resources;
import com.raphuscucullatus.pojo.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 用于解析核心配置文件
 * @author raphus cucullatus
 * @version 2021/9/29 14:53
 * @since JDK11
 */
public class XmlConfigBuilder {
    /**
     * 用于存放核心配置文件信息
     */
    private Configuration configuration;

    public XmlConfigBuilder(){
        this.configuration = new Configuration();
    }

    /**
     * 使用dom4j解析配置信息 封装到Configuration中
     * @return
     */
    public Configuration parseConfig(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        //获取核心配置文件跟标签configuration
        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//properties");
        Properties properties = new Properties();
        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name,value);
        }
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(properties.getProperty("jdbcUrl"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        dataSource.setDriverClassName(properties.getProperty("driverClass"));
        configuration.setDataSource(dataSource);
        //mapper.xml解析: 获取路径 加载字节流 dom4j解析
        List<Element> mapperList = rootElement.selectNodes("//mapper");
        for (Element element : mapperList) {
            String mapperPath = element.attributeValue("resource");
            InputStream resourceAsStream = Resources.getResourceAsStream(mapperPath);
            XmlMappedBuilder xmlMappedBuilder = new XmlMappedBuilder(configuration);
            xmlMappedBuilder.parse(resourceAsStream);
        }

        return configuration;
    }
}
