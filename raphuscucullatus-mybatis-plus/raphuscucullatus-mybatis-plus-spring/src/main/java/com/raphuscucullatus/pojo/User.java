package com.raphuscucullatus.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user表对用实体类(使用lombok进行简化)
 * @author raphus cucullatus
 * @version 2021/10/12 20:20
 * @since JDK11
 */
@Data//get set 等方法
@NoArgsConstructor//无参构造
@AllArgsConstructor//全残构造
@TableName("user")//若表名与实体类名不同 可用此标签添加映射关系
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
