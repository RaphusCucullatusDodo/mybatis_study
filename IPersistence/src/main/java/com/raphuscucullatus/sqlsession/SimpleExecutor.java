package com.raphuscucullatus.sqlsession;

import com.raphuscucullatus.Utils.GenericTokenParser;
import com.raphuscucullatus.Utils.ParameterMapping;
import com.raphuscucullatus.Utils.ParameterMappingTokenHandler;
import com.raphuscucullatus.config.BoundSql;
import com.raphuscucullatus.pojo.Configuration;
import com.raphuscucullatus.pojo.MappedStatement;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * 简单执行器
 * @author raphus cucullatus
 * @version 2021/9/29 19:54
 * @since JDK11
 */
public class SimpleExecutor implements Executor{

    /**
     *
     * @param configuration 核心配置文件
     * @param mappedStatement 映射配置文件
     * @param param userCondition
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object... param) throws Exception {
        //1.注册驱动,获取连接
        Connection connection = configuration.getDataSource().getConnection();

        //2.获取Sql 即:select id,username form user where id = #{id} and username = #{username}
        //解析后Sql 即:select id,username form user where id = ? and username = ?
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);

        //3.获取预处理对象: preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getParseSql());

        //4设置参数
        //通过映射配置文件获取参数类型全路径名,进而获取其运行时类
        String paramType = mappedStatement.getParamType();
        Class<?> paramTypeClass = getClassType(paramType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();
            //反射获取运行时类与content同名字段
            Field declaredField = paramTypeClass.getDeclaredField(content);
            declaredField.setAccessible(true);
            //获取param[0]对象中与content同名字段的值
            Object o = declaredField.get(param[0]);
            //设置占位符的值
            preparedStatement.setObject(i+1,o);
        }

        //5执行sql
        ResultSet resultSet = preparedStatement.executeQuery();

        //6封装结果集
        //通过反射获取结果集运行时类 并创建该类型对象
        String returnType = mappedStatement.getReturnType();
        Class<?> returnTypeClass = Class.forName(returnType);
        ArrayList<Object> list = new ArrayList<>();
        while (resultSet.next()){
            Object o = returnTypeClass.newInstance();
            //结果集元数据(列数,列名,等)
            ResultSetMetaData metaData = resultSet.getMetaData();
            //遍历所有列 (数据库列数从1开始)
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                //获取列名
                String columnName = metaData.getColumnName(i + 1);
                //获取该列的值
                Object columnValue = resultSet.getObject(columnName);
                //获取该列所在表表和实体类对应关系(反射或内省)
                //反射
                //内省
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, returnTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                //将字段值封装至返回类型对象o中
                writeMethod.invoke(o,columnValue);
            }
            list.add(o);
        }
        return (List<T>) list;
    }

    private Class<?> getClassType(String paramType) throws ClassNotFoundException {
        if (paramType!=null) {
            return Class.forName(paramType);
        }
        return null;
    }

    /**
     * 对#{}进行解析: 1将#{}替换为? 2解析出#{}内的值
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql){
        //标记处理类: 配合标记解析器来完成对占位符的处理工作
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        //解析后的sql
        String parseSql = genericTokenParser.parse(sql);
        //#{}中解析出来的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(parseSql, parameterMappings);
        return boundSql;
    }
}
