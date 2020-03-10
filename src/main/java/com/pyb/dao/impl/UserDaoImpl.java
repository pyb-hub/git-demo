package com.pyb.dao.impl;

import com.pyb.Utils.ConnectionUtil;
import com.pyb.dao.IUserDao;
import com.pyb.domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {


    @Autowired
    private QueryRunner runner;
/*手动加入数据源，使得下面所有方法同一个一个连接，保证事务的原子性*/
    @Autowired
    private ConnectionUtil connectionUtil;

    public List<User> findAll(){

        try {
            return runner.query(connectionUtil.getThreadConnection(),"select * from user", new BeanListHandler<User>(User.class));
        } catch (Exception e) {
            throw new RuntimeException("查找失败");
        }
    }
    public void insert(User user){

        try {
            runner.update(connectionUtil.getThreadConnection(),"insert into user(username,age) values(?,?)", user.getUsername(),user.getAge());
        } catch (Exception e) {
            throw new RuntimeException("插入失败");
        }
    }

    public void update(User user) {
        try {
            runner.update(connectionUtil.getThreadConnection(),"update user set age = ? where id = ?", user.getAge(),user.getId());
        } catch (Exception e) {
            throw new RuntimeException("更新失败");
        }
    }

    public List<User> findById(Integer id){

        try {
            return runner.query(connectionUtil.getThreadConnection(),"select * from user where id = ?",new BeanListHandler<User>(User.class),id);
        } catch (Exception e) {
            throw new RuntimeException("查找错误");
        }
    }
}
