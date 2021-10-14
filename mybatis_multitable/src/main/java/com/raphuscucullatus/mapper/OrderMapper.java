package com.raphuscucullatus.mapper;

import com.raphuscucullatus.pojo.Order;
import com.raphuscucullatus.pojo.User;

import java.util.List;

/**
 * @author raphus cucullatus
 * @version 2021/10/1016:42
 * @since JDK11
 */
public interface OrderMapper {

    public Order findByUid(Integer uid);
    /**
     * 查询订单以及其所属用户信息(一对一)
     * @return
     */
    public List<Order> findOrderAndUser();

    /**
     * 作为延迟加载订单信息的查询
     * @return
     */
    public Order findOrderByUid(Integer uid);

}
