package com.raphuscucullatus.raphuscucullatusmybatisplusspringboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raphuscucullatus.mapper.UserMapper;
import com.raphuscucullatus.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class RaphuscucullatusMybatisPlusSpringbootApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindAll(){
        for (User user : userMapper.findAll()) {
            System.out.println(user);
        }

    }

    /**
     * 测试查询所有记录
     */
    @Test
    public void testSelectList(){
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println("查询结果:"+user);

        }
    }

    /**
     * 测试增加一条记录
     * 自动回填id
     */
    @Test
    public void testInsert(){
        User user = new User();
//        user.setId(6L);
        user.setName("葳葳");
        user.setAge(18);
        user.setMail("1346812@qq.com");

        int row = userMapper.insert(user);
        userMapper.insert(user);
        userMapper.insert(user);
        System.out.println("受影响行数:"+row);
        System.out.println("MP自动回填数据:id="+user.getId());
    }

    /**
     * 测试通过id更新数据
     */
    @Test
    public void testUpdateById(){
        User user = new User();
        user.setId(10L);
        user.setAge(17);

        int row = userMapper.updateById(user);
        System.out.println("受影响行数:"+row);
    }
    /**
     * 测试根据条件更新数据
     * update set [字段] where [条件]
     */
    @Test
    public void testUpdateByCondition(){
        //方式一:[字段]
        User user = new User();
        user.setAge(18);
        //方式一: [条件] 使用QueryWrapper
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name","葳葳");

        int row = userMapper.update(user,userQueryWrapper);
        System.out.println("受影响行数:"+row);

        //方式二: [条件] 使用UpdateWrapper
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",10L).set("id",6L);
        userMapper.update(null,updateWrapper);
        System.out.println("受影响行数:"+row);
    }

    @Test
    public void testDeleteById(){
        int row = userMapper.deleteById(6L);
        System.out.println(row);
    }

    /**
     * 批量满足map中所有条件的所有记录?????
     * HashMap<String, Object> Key:字段名 Value:字段值
     */
    @Test
    public void testDeleteByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","葳葳");
        map.put("age",18);
        map.put("id",25L);
        int row = userMapper.deleteByMap(map);
        System.out.println("受影响行数:"+row);
    }

    /**
     * 批量满足(多个)的条件的所有记录
     */
    @Test
    public void testDelete(){
        //方式一:
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//        userQueryWrapper.eq("name","葳葳").eq("age",18);
        //方式二(推荐):
        User user = new User();
        user.setName("葳葳");
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(user);
        int row = userMapper.delete(userQueryWrapper);
        System.out.println("受影响行数:"+row);
    }

    /**
     * 根据Id批量删除
     */
    @Test
    public void testDeleteBatchIds(){
        int row = userMapper.deleteBatchIds(Arrays.asList(6L, 7L, 8L));
        System.out.println("受影响行数:"+row);
    }

    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1L);
        System.out.println("查询结果:"+user);

    }
    @Test
    public void testSelectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L, 5L));
        for (User user : users) {
            System.out.println("查询结果:"+user);
        }
    }

    /**
     * 结果超过一条会报异常
     */
    @Test
    public void testSelectOne(){
        User condition = new User();
        condition.setName("葳葳");
        User user = userMapper.selectOne(new QueryWrapper<User>(condition));
        System.out.println("查询结果:"+user);
    }
    /**
     * 统计满足条件的记录
     * gt ->  greater than 大于
     * qe ->  equals 等于
     * lt ->  less than 小于
     */
    @Test
    public void testSelectCount(){
        User condition = new User();
        condition.setAge(18);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(condition);
        //SELECT COUNT( 1 ) FROM user WHERE age=?
        Integer row = userMapper.selectCount(userQueryWrapper);
        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println("满足条件记录数:"+row+",记录信息为:"+users);

        //SELECT COUNT( 1 ) FROM user WHERE (age < ?)
        QueryWrapper<User> age = new QueryWrapper<User>().lt("age", 19);
        row = userMapper.selectCount(age);
        users = userMapper.selectList(userQueryWrapper);
        System.out.println("满足条件记录数:"+row+",记录信息为:"+users);

    }

    @Test
    public void testSelectPage(){
        User condition = new User();
        condition.setAge(18);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(condition);
        //参数1:当前页码, 参数2:分页大小
        Page<User> userPage = new Page<>(1, 2);
        //SELECT COUNT(1) FROM user WHERE age = ?
        Page<User> selectPage = userMapper.selectPage(userPage, userQueryWrapper);
        System.out.println("总条数:"+selectPage.getTotal()+",总页数:"+selectPage.getPages()
                +",当前页:"+selectPage.getCurrent()+",分页数据:"+selectPage.getRecords());
    }

    @Test
    public void testSelectMapPages(){

    }

}
