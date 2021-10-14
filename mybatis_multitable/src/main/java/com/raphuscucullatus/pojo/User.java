package com.raphuscucullatus.pojo;

import java.sql.Date;
import java.util.List;

/**
 * 用户实体类
 *
 * @author raphus cucullatus
 * @version 2021/10/10 16:41
 * @since JDK11
 */
public class User {
    private int id;
    private String username;
    private String password;
    private Date birthday;

    /**
     * 代表当前⽤户具有的订单信息
     */
    private List<Order> orderList;
    /**
     * 代表当前⽤户具有的角色
     */
    private List<Role> roleList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", orderList=" + orderList +
                ", roleList=" + roleList +
                '}';
    }
}
