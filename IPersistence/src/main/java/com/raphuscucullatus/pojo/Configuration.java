package com.raphuscucullatus.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于存放核心配置文件信息
 * @author raphus cucullatus
 * @version 2021/9/29 14:33
 * @since JDK11
 */
public class Configuration {
    /**
     * 数据源
     */
    private DataSource dataSource;

    /**
     * key: statementid  value: MappedStatement对象
     */
    Map<String,MappedStatement> mappedStatementMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }
    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }
}
