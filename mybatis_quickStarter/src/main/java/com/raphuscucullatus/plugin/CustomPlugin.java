package com.raphuscucullatus.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;


import java.sql.Connection;
import java.util.Properties;

/**
 * 自定义插件
 * @author raphus cucullatus
 * @version 2021/10/4 7:43
 * @since JDK11
 */
@Intercepts({
        @Signature(type= StatementHandler.class,
                method= "prepare",
                args= {Connection.class,Integer.class})
})
public class CustomPlugin implements Interceptor {
    /**
     * 拦截方法: 没当被拦截目标对象的方法执行时,都要执行一次该方法
     * @param invocation 用于调用被拦截方法的类,包装了被拦截的对象 方法 参数
     * @return invocation执行原方法
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("增强方法");
        return invocation.proceed();
    }

    /**
     * 包装⽬标对象 为被拦截对象创建代理对象并将其 存入 拦截器链中
     * @param target 被拦截的目标对象
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }

    /**
     * 获取核心配置文件定义测插件参数
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("获取配置文件"+properties);
    }
}
