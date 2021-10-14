package com.raphuscucullatus.pojo;

/**
 * 用于存放映射配置文件信息
 * @author raphus cucullatus
 * @version 2021/9/29 14:28
 * @since JDK11
 */
public class MappedStatement {
    /**
     * Id标识
     */
    private String id;
    /**
     * sql语句
     */
    private String sql;
    /**
     * 参数类型
     */
    private String paramType;
    /**
     * 返回值类型
     */
    private String returnType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
