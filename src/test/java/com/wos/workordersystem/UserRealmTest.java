package com.wos.workordersystem;

import com.wos.WorkordersystemApplication;
import com.wos.shiro.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author chenyuwei
 * @date 2018/10/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WorkordersystemApplication.class)
public class UserRealmTest {



    @Test
    public void testAuthentication(){

        UserRealm userRealm = new UserRealm();


        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(userRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject=SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken("chenyuwei","123456");
        subject.login(token);
        System.out.println("isAuthenticated:"+subject.isAuthenticated());

    }


}
