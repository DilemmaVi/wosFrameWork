package com.wos.shiro;

import com.wos.pojo.WosReturnModel;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author chenyuwei
 * @date 2018/10/21
 * @description 对没有登陆的请求进行拦截，返回json信息，覆盖shiro原本跳转到login.jsp的拦截方式
 */
public class AjaxPermissionsAuthorizationFilter extends FormAuthenticationFilter {


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        WosReturnModel wosReturnModel = WosReturnModel.errorTokenMsg("未登陆");
        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            out.println(wosReturnModel);
        } catch (Exception e) {

        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
        return false;
    }


    @Bean
    public FilterRegistrationBean registration(AjaxPermissionsAuthorizationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
}