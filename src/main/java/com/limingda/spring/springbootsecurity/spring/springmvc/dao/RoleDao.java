package com.limingda.spring.springbootsecurity.spring.springmvc.dao;

import com.limingda.spring.springbootsecurity.spring.springmvc.model.MyResource;
import com.limingda.spring.springbootsecurity.spring.springmvc.model.MyRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<MyRole,Long> {

    @Query(value = "select t from MyRole t,UserRoleRelation t3 where t.id = t3.roleId and t3.userId = ?1")
    List<MyRole> selectRoleByUserId(Long userId);
}
