package com.wos.shiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wos.pojo.SysUser;
import com.wos.service.UserService;
import com.wos.utils.constants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @author chenyuwei
 * @date 2018/10/21
 */
public class UserRealm extends AuthorizingRealm {


    private static Logger logger = LogManager.getLogger(UserRealm.class);

    @Autowired
    private UserService userService;

    {
        super.setName("userRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject permission = (JSONObject) session.getAttribute(Constants.SESSION_USER_PERMISSION);
        logger.info("permission的值为:" + permission);
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions((Collection<String>) permission.get("permissionList"));
        return authorizationInfo;
    }

    /**
     * 验证当前的subject
     *
     * @param authenticationToken
     * @return LoginController.login()方法中执行Subject.login()时 执行此方法
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        String passWord = new String((char[]) authenticationToken.getCredentials());
        logger.info("正在进行登陆验证=>" + "用户名：" + userName);
        SysUser user = userService.getUser(userName, passWord);
        if (user == null) {
            logger.info("没有找到账号");
            throw new UnknownAccountException();
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "userRealm");
        user.setPassword(null);
        SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, user);
        return simpleAuthenticationInfo;

    }
}
