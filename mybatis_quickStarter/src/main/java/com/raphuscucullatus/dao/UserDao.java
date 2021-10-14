package com.raphuscucullatus.dao;


import com.raphuscucullatus.pojo.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.mybatis.caches.redis.RedisCache;

import java.io.IOException;
import java.util.List;

//@CacheNamespace(implementation = RedisCache.class)

public interface UserDao {

    List<User> findAll() throws IOException;

    /**
     * 动态Sql <if> </if> and <where> </where>
     * @param user
     * @return
     * @throws IOException
     */
    List<User> findByCondition(User user) throws IOException;
    /**
     * 多值查询
     * 动态Sql <foreach></foreach>
     * @param ids
     * @return
     * @throws IOException
     */
    List<User> findByIds(int[] ids) throws IOException;

    void insertUser(User user) throws IOException;
    void deleteUser(Integer id) throws IOException;
    void updateUser(User user) throws IOException;

}
