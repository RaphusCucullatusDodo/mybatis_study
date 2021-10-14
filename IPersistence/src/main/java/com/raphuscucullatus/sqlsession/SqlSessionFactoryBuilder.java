package com.raphuscucullatus.sqlsession;

import com.raphuscucullatus.config.XmlConfigBuilder;
import com.raphuscucullatus.pojo.Configuration;
import org.dom4j.DocumentException;
import java.io.InputStream;

/**
 * @author raphus cucullatus
 * @version 2021/9/29 14:48
 * @since JDK11
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) throws DocumentException {
        // 1. 使用dom4j解析配置文件, 将解析出来的内容封装到Configuration中
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(inputStream);

        // 2. 创建SqlSessionFactory对象: 生产sqlSession
        SqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);


        return defaultSqlSessionFactory;
    }
}
