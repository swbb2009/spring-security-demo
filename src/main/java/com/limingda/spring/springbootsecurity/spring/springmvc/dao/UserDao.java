package com.limingda.spring.springbootsecurity.spring.springmvc.dao;

import com.limingda.spring.springbootsecurity.spring.springmvc.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<MyUser,Long> {

     MyUser findByUsername(String username);

}
