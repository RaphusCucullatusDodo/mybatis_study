package com.raphuscucullatus.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mp配置类
 *
 * @author raphus cucullatus
 * @version 2021/10/13 19:25
 * @since JDK11
 */
@Configuration
public class MyBatisPlusConfig {

    /**
     * 旧分页插件(拦截器)
     *  添加该插件后可使用分页查询
     * @return
     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor(){
//        return new PaginationInterceptor();
//    }
}
