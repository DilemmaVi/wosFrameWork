package com.wos.controller;

import com.wos.pojo.SysUser;
import com.wos.pojo.WosReturnModel;
import com.wos.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenyuwei
 * @date 2018/9/3
 */
@RestController
public class HelloController {

    private static Logger logger=LogManager.getLogger(HelloController.class);

    @Autowired
    private UserService userService;


    @RequestMapping("/hello")
    public WosReturnModel hello() throws Exception {


        SysUser user=new SysUser();
        user.setUsername("chenyuwei");
        user.setPassword("123456");
        user.setNickname("DilemmaVi");
        user.setSex(1);
        user.setAge(28);
        user.setIsDelete(0);
        user.setJob("JAVA开发工程师");
        userService.saveUser(user);

        return WosReturnModel.ok(user);
    }
}
