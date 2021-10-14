package com.raphuscucullatus.mapper;

import com.raphuscucullatus.pojo.User;

import java.util.List;

/**
 * @author raphus cucullatus
 * @version 2021/10/8 17:07
 * @since JDK11
 */
public interface IUserMapper {
    User findById(Integer id);
}
