package com.limingda.spring.springbootsecurity.spring.springmvc.dao;

import com.limingda.spring.springbootsecurity.spring.springmvc.model.MyResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceDao extends JpaRepository<MyResource,Long> {

    @Query(value = "select t from MyResource t,RoleResourceRelation t2 ,UserRoleRelation t3 where t.id=t2.resourceId " +
            "and t2.roleId = t3.roleId and t3.userId = ?1")
//    @Query(value = "select t.* from resource t,Role_Resource_Relation t2 ,User_Role_Relation t3 where t._id=t2.resource_Id " +
//            "and t2.role_Id = t3.role_Id and t3.user_Id = ?1",nativeQuery = true)
    List<MyResource> selectRoleAndUser(Long userId);
}
