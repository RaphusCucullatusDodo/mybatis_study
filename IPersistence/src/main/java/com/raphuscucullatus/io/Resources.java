package com.raphuscucullatus.io;

import java.io.InputStream;

/**
 * @author raphus cucullatus
 * @version 2021/9/29 14:07
 * @since JDK11
 */
public class Resources {
    /**
     * 通过配置文件路径 将其以为字节流的方式加载进内存
     * @param path 配置文件路径
     * @return 配置文件字节流
     */
    public static InputStream getResourceAsStream(String path){
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }
}
