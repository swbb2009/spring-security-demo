package com.limingda.spring.springbootsecurity;

import com.limingda.spring.springbootsecurity.spring.springmvc.dao.ResourceDao;
import com.limingda.spring.springbootsecurity.spring.springmvc.dao.RoleResourceRelationDao;
import com.limingda.spring.springbootsecurity.spring.springmvc.model.MyResource;
import com.limingda.spring.springbootsecurity.spring.springmvc.model.dto.ResourceAndRoleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootSecurityApplicationTests {

    @Autowired
    private ResourceDao dao;
    @Autowired
    private RoleResourceRelationDao resourceRelationDao;
    @Test
    void contextLoads() {
        List<MyResource> myResources = dao.selectRoleAndUser(1L);
        System.out.println(myResources);
    }
    @Test
    void tesets(){
        List<ResourceAndRoleDto> resourceAndRoleDtos = resourceRelationDao.selectRoleAndResource();
        System.out.println(1);
    }





}
