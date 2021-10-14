package com.raphuscucullatus.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.raphuscucullatus.dao.UserDao;
import com.raphuscucullatus.mapper.UserMapper;
import com.raphuscucullatus.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    @Test
    public void testQuickStart() throws IOException {
        //1.Resources工具类,配置文件的加载,把配置文件加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.解析配置文件,并创建sqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.生成sqlSession, 该sqlSession默认开启事务,但不自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.调用sqlSession调用方法:查询所有或查询单个
        List<User> list = sqlSession.selectList("com.raphuscucullatus.dao.UserDao.findAll");
        for (User user : list) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void testSaveUser() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        User user = new User();
        user.setUsername("苟泽");
        user.setId(5);
        sqlSession.insert("com.raphuscucullatus.dao.UserDao.insertUser", user);
        sqlSession.close();
    }

    @Test
    public void testUpdateUserName() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = build.openSession();
        User user = new User();
        user.setUsername("铁柱");
        user.setId(5);
        sqlSession.update("com.raphuscucullatus.dao.UserDao.updateUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteUser() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = build.openSession();
        int delete = sqlSession.delete("com.raphuscucullatus.dao.UserDao.deleteUser", 5);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void userDaoFindAll() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = build.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> all = userDao.findAll();
        for (User user : all) {
            System.out.println(user);
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void userDaoFindByCondition() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = build.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
//        user.setId(3);
        user.setUsername("狗蛋");
        List<User> byCondition = userDao.findByCondition(user);
        System.out.println("查询结果:" + byCondition);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void userDaoFindByIds() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = build.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        int[] ids = new int[]{1, 2, 3, 4};
        List<User> byCondition = userDao.findByIds(ids);
        System.out.println("查询结果:" + byCondition);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectPageHelper() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = build.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        PageHelper.startPage(2, 2);
        List<User> all = userDao.findAll();
        for (User user : all) {
            System.out.println(user);
        }
        PageInfo<User> userPageInfo = new PageInfo<User>(all);
        System.out.println("总条数" + userPageInfo.getTotal());
        System.out.println("总页数" + userPageInfo.getPages());
        System.out.println("当前页" + userPageInfo.getPageNum());
        System.out.println("分页大小" + userPageInfo.getPageSize());
    }

    @Test
    public void testGenericMapper() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = build.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(2);
//(1)mapper基础接⼝//select接⼝
        User user1 = userMapper.selectOne(user);//根据实体中的属性进⾏查询，只能有—个返回值
        System.out.println(user1);
//(2)example⽅法
//⾃定义查询
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("id", 1);
        List<User> users1 = userMapper.selectByExample(example);
        System.out.println(users1);
    }


    @Test
    public void testSelectAll() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = build.openSession();
        //使用JDK动态代理对mapper接口产生代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    }
}
