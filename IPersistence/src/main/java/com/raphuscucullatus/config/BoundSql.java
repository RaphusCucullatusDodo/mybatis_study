package com.raphuscucullatus.config;

import com.raphuscucullatus.Utils.ParameterMapping;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装解析出的sql以及用于替换占位符的参数名
 * @author raphus cucullatus
 * @version 2021/9/29 21:06
 * @since JDK11
 */
public class BoundSql {
    private String parseSql;
    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

    public BoundSql(String parseSql, List<ParameterMapping> parameterMappingList) {
        this.parseSql = parseSql;
        this.parameterMappingList = parameterMappingList;
    }

    public String getParseSql() {
        return parseSql;
    }

    public void setParseSql(String parseSql) {
        this.parseSql = parseSql;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
