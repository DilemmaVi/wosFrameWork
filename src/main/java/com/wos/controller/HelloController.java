package com.wos.controller;

import com.wos.utils.PropertyUtils;
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
    public String hello() {
        String property = PropertyUtils.get("hello.data");

        return property;
    }
}
