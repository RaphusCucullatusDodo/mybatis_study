package com.raphuscucullatus.test;

import com.raphuscucullatus.mapper.UserMapper;
import com.raphuscucullatus.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试用例
 *
 * @author raphus cucullatus
 * @version 2021/10/12 22:23
 * @since JDK11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringMPTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList(){
        for (User user : userMapper.selectList(null)) {
            System.out.println(user);
        }
//        for (User user : userMapper.findAll()) {
//            System.out.println("自定义结构");
//        }


    }
}
