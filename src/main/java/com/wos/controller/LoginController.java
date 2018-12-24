package com.wos.controller;

import com.alibaba.fastjson.JSONObject;
import com.wos.pojo.SysUser;
import com.wos.pojo.WosReturnModel;
import com.wos.service.UserService;
import com.wos.utils.IpUtil;
import com.wos.utils.ValidateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author chenyuwei
 * @date 2018/9/3
 */
@RestController
public class LoginController {

    private static Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private UserService userService;


    /**
     * 登录
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public WosReturnModel login(@RequestBody JSONObject requestJson) throws Exception {

        ValidateUtil.hasAllRequired(requestJson, "username,password");
        SysUser user = userService.authLogin(requestJson);
        return WosReturnModel.ok(user);
    }

    @RequestMapping("add")
    public WosReturnModel add(HttpServletRequest request) {

        SysUser sysUser = new SysUser();
        sysUser.setUsername("chenyuwei");
        sysUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        sysUser.setNickname("DilemmaVi");
        sysUser.setJob("JAVA开发");
        sysUser.setLastLoginIp(IpUtil.getIpAddr(request));
        sysUser.setLastLoginTime(new Date());
        sysUser.setRegistTime(new Date());
        try {
            userService.saveUser(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WosReturnModel.ok(sysUser);
    }


}
