package com.xxl.dao;

import com.xxl.pojo.Users;

import java.util.List;

/**
 * @类名： com.xxl.pojo.com.xxl.dao.UserDao
 * @描述： <尽量简短的描述类的作用>
 * @创建人： xiao.xl
 * @创建时间： 2019/1/2 22:59
 * @修改人和其他信息：
 */
public interface UserDao {
    void insertUser(Users users);
    void updateUser(Users users);
    void deleteUser(Users users);
    Users selectUserById(Integer userId);
    List<Users> selectAll();
    List<Users> selectByUserName(String userName);
    List<Users> selectByUserNameSQL(String userName);
    List<Users> selectByUserNameCriteria(String userName);
}
