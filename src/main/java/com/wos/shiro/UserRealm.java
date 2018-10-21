package com.wos.shiro;

import com.wos.pojo.SysUser;
import com.wos.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenyuwei
 * @date 2018/10/21
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    {
        super.setName("userRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName= (String) authenticationToken.getPrincipal();
        String passWord= new String((char[]) authenticationToken.getCredentials());
        SysUser user = userService.getUser(userName, passWord);
        if (user==null){

           return null;
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),"userRealm");
        return simpleAuthenticationInfo;

    }
}
