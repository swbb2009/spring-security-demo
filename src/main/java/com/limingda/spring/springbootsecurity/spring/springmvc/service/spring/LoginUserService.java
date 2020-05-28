package com.limingda.spring.springbootsecurity.spring.springmvc.service.spring;

import com.limingda.spring.springbootsecurity.spring.springmvc.dao.UserDao;
import com.limingda.spring.springbootsecurity.spring.springmvc.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService {

    @Autowired
    private UserDao userDao;

    public MyUser getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof org.springframework.security.core.userdetails.User)) {
            return null;
        }
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User)principal;
        String userName = springUser.getUsername();
        MyUser user = userDao.findByUsername(userName);
        return user;
    }

}
