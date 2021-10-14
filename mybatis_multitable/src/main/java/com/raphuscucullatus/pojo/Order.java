package com.raphuscucullatus.pojo;

import java.sql.Date;

/**
 * 订单实体类
 *
 * @author raphus cucullatus
 * @version 2021/10/10 16:38
 * @since JDK11
 */
public class Order {
    private Integer id;
    private Date orderTime;
    private Double total;
    /**
     * 该订单所属用户
     * 从表Oder 主表User 一对一
     */
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderTime=" + orderTime +
                ", total=" + total +
                ", user=" + user +
                '}';
    }
}
