package com.limingda.spring.springbootsecurity.spring.springmvc.service.spring;

import com.limingda.spring.springbootsecurity.spring.springmvc.dao.RoleDao;
import com.limingda.spring.springbootsecurity.spring.springmvc.dao.UserDao;
import com.limingda.spring.springbootsecurity.spring.springmvc.model.MyRole;
import com.limingda.spring.springbootsecurity.spring.springmvc.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userDao.findByUsername(username);
        if(user==null){
            return null;
        }
        List<MyRole> myRoles = roleDao.selectRoleByUserId(user.getId());
        List<SimpleGrantedAuthority> grantedAuthority = new ArrayList<>();
        for(MyRole role :myRoles){
            grantedAuthority.add(new SimpleGrantedAuthority(role.getRoleCode()));
        }
        UserDetails userDetails = User.withUsername(user.getUsername()).password(user.getPassword()).authorities(grantedAuthority).build();
        return userDetails;
    }
}
