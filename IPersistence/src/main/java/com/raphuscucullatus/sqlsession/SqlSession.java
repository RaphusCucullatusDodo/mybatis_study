package com.raphuscucullatus.sqlsession;

import java.util.List;

/**
 * @author raphus cucullatus
 * @version 2021/9/2918:36
 * @since JDK11
 */
public interface SqlSession {
    /**
     * 查询所有
     * @param statementId
     * @param param
     * @param <T>
     * @return
     */
    <T> List<T> selectList(String statementId,Object... param) throws Exception;

    /**
     * 查询单个
     * @param statementId
     * @param param
     * @param <T>
     * @return
     */
    <T> T selectOne(String statementId,Object... param) throws Exception;

    /**
     * 为Mapper接口生成实现类
     * @param mapperClass
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<?> mapperClass);

}
