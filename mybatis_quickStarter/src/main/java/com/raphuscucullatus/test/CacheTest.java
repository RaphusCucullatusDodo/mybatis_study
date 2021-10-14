package com.raphuscucullatus.test;

import com.raphuscucullatus.dao.UserDao;
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
 * @version 2021/10/2 20:31
 * @since JDK11
 */
public class CacheTest {
    private UserDao userMapper;
    private SqlSession sqlSession;
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserDao.class);
    }

    @Test
    public void testSecondLevelCache() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        UserDao mapper1 = sqlSession1.getMapper(UserDao.class);
        UserDao mapper2 = sqlSession2.getMapper(UserDao.class);
        List<User> all = mapper.findAll();
        System.out.println(all);
        sqlSession.close();
        List<User> all1 = mapper1.findAll();
        System.out.println(all1);
        List<User> all2 = mapper2.findAll();
        System.out.println("all1==all2:" + (all1 == all2));
    }

    @Test
    public void testSecondLevelCacheInvalidation() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        UserDao mapper1 = sqlSession1.getMapper(UserDao.class);
        UserDao mapper2 = sqlSession2.getMapper(UserDao.class);
        List<User> all = mapper.findAll();
        System.out.println(all);
        sqlSession.close();
        mapper1.deleteUser(5);//增删改会清空二级缓存
        sqlSession1.commit();
        List<User> all2 = mapper2.findAll();
        System.out.println(all2);
    }

    /**
     * 二级缓存开启失败????
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
//        SqlSession sqlSession1 = sqlSessionFactory.openSession();
//        SqlSession sqlSession2 = sqlSessionFactory.openSession();
//        List<User> all1 = sqlSession1.selectList("com.raphuscucullatus.dao.UserDao.findAll");
//        System.out.println(all1);
//        sqlSession1.close();
//        List<User> all2 = sqlSession2.selectList("com.raphuscucullatus.dao.UserDao.findAll");
//        System.out.println(all2);

        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        Integer id = 1;
        User user1 = sqlSession1.selectOne("com.raphuscucullatus.mapper.IUserMapper.findById", id);
        System.out.println("用户信息为:" + user1);
        sqlSession1.close();
        User user2 = sqlSession2.selectOne("com.raphuscucullatus.mapper.IUserMapper.findById", id);
        System.out.println("用户信息为:" + user2);
    }
}
