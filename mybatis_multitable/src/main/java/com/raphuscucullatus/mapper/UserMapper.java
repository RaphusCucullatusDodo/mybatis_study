package com.raphuscucullatus.mapper;

import com.raphuscucullatus.pojo.Order;
import com.raphuscucullatus.pojo.Role;
import com.raphuscucullatus.pojo.User;

import java.util.List;

/**
 * @author raphus cucullatus
 * @version 2021/10/1017:52
 * @since JDK11
 */
public interface UserMapper {


    public User findById(Integer id);


    /**
     * 查询用户以及其订单信息(一对多)
     * @return
     */
    public List<User> findAll();

    /**
     * 查询用户以及其订单信息(多对多)
     * @return
     */
    public List<Role> findAllUserAndRole();
}
