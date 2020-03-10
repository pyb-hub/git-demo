package com.pyb.service;


import com.pyb.domain.User;

import java.util.List;

/*业务层接口*/
public interface IUserService {

    List<User> findAll();
    void insert(User user);
    void update(User user);
    List<User> findById(Integer id);
    void transferAge(Integer id1,Integer id2,Integer age);
}
