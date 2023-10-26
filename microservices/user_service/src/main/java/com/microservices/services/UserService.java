package com.microservices.services;

import com.microservices.dao.UserDao;
import com.microservices.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUser(){
        List<User> all = userDao.findAll();

        return all;
    }

    public User getUser(int id){
        Object byId = null;
        try {
           byId = userDao.findById(id);

        }catch (Exception e){
            e.printStackTrace();
        }
        return (User) byId;

    }


    public void addUser(User user){
        try {
            userDao.save(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public User update(User user , int id){
        user.setId(id); // ab agr user jo id dy ga woh is address ko assign ho jay gi // or wohi id wala address update ho ga
        User update = userDao.save(user);
        return update;
    }

    public void delete(int id){
        userDao.deleteById(id);
    }



}
