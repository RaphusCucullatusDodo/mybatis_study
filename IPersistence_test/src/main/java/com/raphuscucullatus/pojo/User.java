package com.raphuscucullatus.pojo;

/**
 * 用户类实体
 *
 * @author raphus cucullatus
 * @version 2021/9/29 13:49
 * @since JDK11
 */
public class User {
    private Integer id;
    private String username;

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
                ", userName='" + username + '\'' +
                '}';
    }
}
