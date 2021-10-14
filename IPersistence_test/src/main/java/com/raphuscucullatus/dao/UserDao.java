package com.raphuscucullatus.dao;

import com.raphuscucullatus.pojo.User;

import java.util.List;

/**
 * @author raphus cucullatus
 * @version 2021/10/1 7:48
 * @since JDK11
 */
public interface UserDao {
    /**
     * 查找所有用户
     * @return
     */
    public List<User> findAll() throws Exception;

    /**
     * 按条件查找用户
     * @return
     */
    public User findByNameAndId(User userCondition) throws Exception;

}
