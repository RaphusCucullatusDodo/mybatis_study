package com.raphuscucullatus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.raphuscucullatus.pojo.User;

import java.util.List;

/**
 * springboot集成MP进行查询
 * @author raphus cucullatus
 * @version 2021/10/1220:25
 * @since JDK11
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查找所有用户
     * @return 用户列表
     */
    public List<User> findAll();
}
