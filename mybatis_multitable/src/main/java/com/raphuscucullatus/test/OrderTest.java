package com.raphuscucullatus.test;

import com.raphuscucullatus.mapper.OrderMapper;
import com.raphuscucullatus.mapper.UserMapper;
import com.raphuscucullatus.pojo.Order;
import com.raphuscucullatus.pojo.Role;
import com.raphuscucullatus.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author raphus cucullatus
 * @version 2021/10/10 17:01
 * @since JDK11
 */
public class OrderTest {
    private SqlSession sqlSession;

    @Before
    public void before() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsStream);
        this.sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void orderTest() {
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> orderAndUser = mapper.findOrderAndUser();
        for (Order order : orderAndUser) {
            System.out.println(order);
        }
    }

    @Test
    public void userTest() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userAndOrders = mapper.findAll();
        System.out.println(userAndOrders);
    }

    @Test
    public void userRoleTest() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<Role> allUserAndRole = mapper.findAllUserAndRole();
        System.out.println(allUserAndRole);
    }


    @Test
    public void lazyLoadTest() {
        User user = sqlSession.selectOne("com.raphuscucullatus.mapper.UserMapper.findById", 1);
        System.out.println(user.getId()+":"+user.getUsername());
        System.out.println(user);

    }
}
