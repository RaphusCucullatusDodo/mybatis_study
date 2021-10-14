package com.raphuscucullatus.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * user表实体类
 * @author raphus cucullatus
 * @version 2021/10/1 19:50
 * @since JDK11
 */
@Table(name="user")
public class User implements Serializable {
    @Id//表示该字段为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //设置主键生成策略(主键自增)
    private Integer id = 0;
    //@Column 若字段名与列名不同可通过该注解进行配置映射关系
    private String username;
    public User() {    }
    public User(Integer id) {
        this.id=id;
    }

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
