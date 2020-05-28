package com.limingda.spring.springbootsecurity.spring.springmvc.service.spring;

import com.limingda.spring.springbootsecurity.spring.springmvc.dao.RoleResourceRelationDao;
import com.limingda.spring.springbootsecurity.spring.springmvc.model.dto.ResourceAndRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    private HashMap<String, List<ConfigAttribute>> map = null;

    @Autowired
    private RoleResourceRelationDao resourceRelationDao;
    /**
     * 加载权限表中所有权限，这里不想从数据库中获取直接写在了这
     */
    public void loadResourceDefine() {
        List<ResourceAndRoleDto> resourceAndRoleDtos = resourceRelationDao.selectRoleAndResource();
//        map = new HashMap<>();
//        Collection<ConfigAttribute> array;
//        ConfigAttribute cfg, cfg1;
//        array = new ArrayList<>();
//        cfg = new SecurityConfig("ROLE_USER");
//        cfg1 = new SecurityConfig("ROLE_USER1");
//        array.add(cfg);
//        array.add(cfg1);
//        map.put("/r/rs", array);
        map = new HashMap<>();
        for (ResourceAndRoleDto dto:resourceAndRoleDtos){
            if(map.get(dto.getUrl())==null) {
                ConfigAttribute  configAttribute =  new SecurityConfig(dto.getRoleCode());
                List<ConfigAttribute> list  = new ArrayList<ConfigAttribute>();
                list.add(configAttribute);
                map.put(dto.getUrl(),list);
            }else{
                List<ConfigAttribute> list  = map.get(dto.getUrl());
                ConfigAttribute  configAttribute =  new SecurityConfig(dto.getRoleCode());
                list.add(configAttribute);
            }
        }
    }

    //此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，
    // 用来判定用户是否有此权限。如果不在权限表中则放行。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        System.out.println("object的类型为:" + object.getClass());
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String url = filterInvocation.getRequestUrl();
        System.out.println("访问的URL地址为(包括参数):" + url);
        url = filterInvocation.getRequest().getServletPath();
        System.out.println("访问的URL地址为:" + url);
        if (map == null)
            loadResourceDefine();
        //object 中包含用户请求的request 信息
        final HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            //matches() 方法用于检测字符串是否匹配给定的正则表达式
            boolean a=matcher.matches(request);
            if (matcher.matches(request)) {
                Collection<ConfigAttribute> c = map.get(resUrl);
                return map.get(resUrl);
            }
        }
        return null;
        //return collection;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        //UsernamePasswordAuthenticationToken.class.equals(clazz);
        return FilterInvocation.class.isAssignableFrom(clazz);
        //return true;
    }
}