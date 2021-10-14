package com.raphuscucullatus.sqlsession;

import com.raphuscucullatus.pojo.Configuration;

/**
 * @author raphus cucullatus
 * @version 2021/9/29 18:04
 * @since JDK11
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{
    private Configuration configuration;
    public DefaultSqlSessionFactory(Configuration configuration){
        this.configuration=configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }

}
