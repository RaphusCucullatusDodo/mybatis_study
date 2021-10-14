package com.raphuscucullatus.mapper;

import com.raphuscucullatus.pojo.User;
import java.util.List;
/**
 * 使用原生MyBatis进行查询
 * @author raphus cucullatus
 * @version 2021/10/1220:25
 * @since JDK11
 */
public interface UserMapper {
    /**
     * 查找所有用户
     * @return 用户列表
     */
    public List<User> findAll();
}
