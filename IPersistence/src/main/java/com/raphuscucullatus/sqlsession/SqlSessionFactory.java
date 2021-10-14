package com.raphuscucullatus.sqlsession;

/**
 * @author raphus cucullatus
 * @version 2021/9/2914:52
 * @since JDK11
 */
public interface SqlSessionFactory {
    SqlSession openSession();
}
