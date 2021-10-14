package com.raphuscucullatus.sqlsession;

import com.raphuscucullatus.pojo.Configuration;
import com.raphuscucullatus.pojo.MappedStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * sql执行器
 *
 * @author raphus cucullatus
 * @version 2021/9/2919:47
 * @since JDK11
 */
public interface Executor {

    /**
     * 执行器
     * @param configuration 核心配置文件
     * @param mappedStatement 映射配置文件
     * @param params sql执行参数
     * @param <T> 泛型
     * @return 泛型集合
     */
    public <T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;
}
