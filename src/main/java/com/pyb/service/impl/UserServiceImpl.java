package com.pyb.service.impl;

import com.pyb.Utils.TransactionUtil;
import com.pyb.dao.IUserDao;
import com.pyb.domain.User;
import com.pyb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private TransactionUtil transactionUtil;

    public List<User> findAll() {
        return userDao.findAll();
    }

    public void insert(User user) {
        userDao.insert(user);
    }

    public void update(User user) {

        userDao.update(user);
    }


    public List<User> findById(Integer id) {
        return userDao.findById(id);
    }


    public void transferAge(Integer id1, Integer id2, Integer age) {

        try {

            List<User> u1 = userDao.findById(id1);
            List<User> u2 = userDao.findById(id2);
            User user1 = u1.get(0);
            user1.setAge(user1.getAge() - age);
            userDao.update(user1);

            User user2 = u2.get(0);
            user2.setAge(user2.getAge() + age);
            int i = 1 / 0;
            userDao.update(user2);

        } catch (Exception e) {

            throw new RuntimeException("被除数为0");


        }
    }
}
