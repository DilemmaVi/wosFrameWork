package com.wos.controller;

import com.wos.pojo.WosReturnModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenyuwei
 * @date 2018/9/3
 */
@RestController
public class HelloController {

    private static Logger logger=LogManager.getLogger(HelloController.class);




    @RequestMapping("/hello")
    public WosReturnModel hello() {
        int a = 1 / 0;

        return WosReturnModel.ok(a);
    }
}
