package com.limingda.spring.springbootsecurity.spring.springmvc.dao;

import com.limingda.spring.springbootsecurity.spring.springmvc.model.RoleResourceRelation;
import com.limingda.spring.springbootsecurity.spring.springmvc.model.dto.ResourceAndRoleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleResourceRelationDao extends JpaRepository<RoleResourceRelation,Long> {


    @Query(value = "select new com.limingda.spring.springbootsecurity.spring.springmvc.model.dto.ResourceAndRoleDto(t3.roleName ,t3.roleCode,t.url,t.resourceName) " +
            "from MyResource t , RoleResourceRelation t2 , MyRole t3" +
            " where t.id=t2.resourceId and t2.roleId = t3.id ")
    List<ResourceAndRoleDto> selectRoleAndResource();

}
