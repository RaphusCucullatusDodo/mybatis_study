package com.raphuscucullatus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.raphuscucullatus.mapper")
@SpringBootApplication
public class RaphuscucullatusMybatisPlusSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RaphuscucullatusMybatisPlusSpringbootApplication.class, args);
    }

}
