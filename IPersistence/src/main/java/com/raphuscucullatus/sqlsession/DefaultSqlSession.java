package com.raphuscucullatus.sqlsession;

import com.raphuscucullatus.pojo.Configuration;
import com.raphuscucullatus.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;

/**
 * @author raphus cucullatus
 * @version 2021/9/29 18:37
 * @since JDK11
 */
public class DefaultSqlSession implements SqlSession{
    /**
     * 配置文件数据
     */
    private final Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration=configuration;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object... params) throws Exception {
        //通过调用 simpleExecutor的query方法 实现查询
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        List<T> list = simpleExecutor.query(configuration, mappedStatement, params);
        return list;
    }

    @Override
    public <T> T selectOne(String statementId, Object... param) throws Exception {
        List<T> list = selectList(statementId, param);
        if (list.size()==1){
            return list.get(0);
        }else{
            throw new RuntimeException("查询结果为空,或者结果不止一个");
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        // 使用动态代理为生成Dao层接口实现类(也就是myBatis中的Mapper的实现类)   形参: 类加载器 被代理类接口[数组] handler
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[] {mapperClass}, new InvocationHandler() {
            /* @param proxy 代理对象
             * @param method 被代理对象的方法
             * @param args 被代理对象的方法的形参 */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //底层还是JDBC代码 //事项根据不同情况选择执行不同方法的功能
                // 准备参数 1.statementId SQL语句的唯一标识: namespace.id = 接口全限定名.方法名(按这种方式命名就可以不用在此处在去读取一遍配置文件来获取statementId)
                String methodName = method.getName();//方法名
                String className = method.getDeclaringClass().getName();//类全限定名
                String statementId = className + "." + methodName;
                //获取被调用方法的返回值类型 Type:所有类型的通用超级接口。 这些包括原始类型、参数化类型、数组类型、类型变量和原始类型。
                Type genericReturnType = method.getGenericReturnType();
                //是否进行了 泛型类型参数化
                if(genericReturnType instanceof ParameterizedType){
                    return selectList(statementId);
                }
                return selectOne(statementId,args);
            }
        });
        return (T) proxyInstance;
    }
}
