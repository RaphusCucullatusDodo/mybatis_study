package com.raphuscucullatus.test;

import com.raphuscucullatus.dao.UserDao;
import com.raphuscucullatus.io.Resources;
import com.raphuscucullatus.pojo.User;
import com.raphuscucullatus.sqlsession.SqlSession;
import com.raphuscucullatus.sqlsession.SqlSessionFactory;
import com.raphuscucullatus.sqlsession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author raphus cucullatus
 * @version 2021/9/29 14:16
 * @since JDK11
 */
public class IPersistenceTest {

    @Test
    public void testGetResourcesAsStream()throws Exception{
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User userCondition = new User();
        userCondition.setId(1);
        userCondition.setUsername("lucy");
//        User user = sqlSession.selectOne("user.selectOne", userCondition);
//        List<User> list = sqlSession.selectList("user.selectList");

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.findByNameAndId(userCondition);
        List<User> userList = userDao.findAll();
        System.out.println("查询用户结果:"+user);
        System.out.println("userList中的数据为"+userList);

    }

}
