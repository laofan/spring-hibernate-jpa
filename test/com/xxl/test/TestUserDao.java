package com.xxl.test;

import com.xxl.dao.UserDao;
import com.xxl.pojo.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @类名： com.xxl.test.TestUserDao
 * @描述： <尽量简短的描述类的作用>
 * @创建人： xiao.xl
 * @创建时间： 2019/1/2 23:15
 * @修改人和其他信息：
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestUserDao {

    @Autowired
    private UserDao userDao;

    @Test
    public void testFindById(){
        Users users = this.userDao.selectUserById(1);
        System.out.println(users);
    }

    @Test
    public void testInsertUser(){
        Users users = new Users();
        users.setUserName("李四");
        users.setUserAge(18);
        this.userDao.insertUser(users);
    }

    @Test
    public void testSelectByUserName(){
        String userName = "李四";
        System.out.println("---------------HQL-------------");
        List<Users> users = this.userDao.selectByUserName(userName);
        users.forEach(item -> System.out.println(item));

        System.out.println("---------------SQL-------------");
        List<Users> usersSQl = this.userDao.selectByUserNameSQL(userName);
        usersSQl.forEach(item -> System.out.println(item));

        System.out.println("---------------Criteria-------------");
        List<Users> usersCriteria = this.userDao.selectByUserNameCriteria(userName);
        usersCriteria.forEach(item -> System.out.println(usersCriteria));
    }

}
