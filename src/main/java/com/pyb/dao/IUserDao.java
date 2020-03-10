package com.pyb.dao;


import com.pyb.domain.User;

import java.util.List;

/*持久层接口*/
public interface IUserDao {

    List<User> findAll();
    void insert(User user);
    void update(User user);
    List<User> findById(Integer id);
}
