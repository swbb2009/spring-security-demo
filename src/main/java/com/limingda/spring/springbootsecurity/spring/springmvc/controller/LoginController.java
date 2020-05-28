package com.limingda.spring.springbootsecurity.spring.springmvc.controller;

import com.limingda.spring.springbootsecurity.spring.springmvc.model.MyUser;
import com.limingda.spring.springbootsecurity.spring.springmvc.service.spring.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginUserService loginUserService;

    @RequestMapping(value = "/login-success",produces = "text/plain;charset=utf-8")
    public String login(){
        return "success";
    }

    @RequestMapping(value = "/r/rs",produces = "text/plain;charset=utf-8")
    public String get1(){
        MyUser u = loginUserService.getCurrentUser();
        String username = "";
        if(u!=null){
            username = u.getUsername();
        }
        return username+"=====success";
    }

    @RequestMapping(value = "/r/re",produces = "text/plain;charset=utf-8")
    public String get(){
        MyUser u = loginUserService.getCurrentUser();
        String username = "";
        if(u!=null){
            username = u.getUsername();
        }
        return username+"=====success";
    }

    @RequestMapping(value = "/r/rq",produces = "text/plain;charset=utf-8")
    public String get2(){
        MyUser u = loginUserService.getCurrentUser();
        String username = "";
        if(u!=null){
            username = u.getUsername();
        }
        return username+"=====success";
    }
}
