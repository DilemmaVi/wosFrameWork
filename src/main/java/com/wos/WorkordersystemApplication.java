package com.wos;


import com.wos.utils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Map;
import java.util.Properties;

/**
 * @author chenyuwei
 * @date 2018/9/8
 */
@EnableConfigurationProperties
@MapperScan("com.wos.mapper")
@SpringBootApplication
public class WorkordersystemApplication {


    public static void main(String[] args) {
        SpringApplication.run(WorkordersystemApplication.class, args);
    }

    @Autowired
    private PropertyUtils propertyUtils;

    /**
     * 自动从zookeeper获取配置信息并重写本地配置文件
     *
     */
    @Bean
    public String changeEnvironment(ApplicationContext applicationContext) {
        //支持Environment获取  修改容器里面StandardServletEnvironment

        StandardEnvironment standardEnvironment = applicationContext.getBean(StandardEnvironment.class);
        //根据本地loadPropFromRemote的参数是否为true来确定是否要从远程zookeeper加载配置数据
        if (StringUtils.isNotEmpty(standardEnvironment.getProperty("loadPropFromRemote")) && "true".equals(standardEnvironment.getProperty("loadPropFromRemote"))) {
            Properties properties = propertyUtils.loadProperties(standardEnvironment);
            standardEnvironment.getPropertySources().replace("applicationConfig: [classpath:/application.properties]", new PropertiesPropertySource("applicationConfig: [classpath:/application.properties]", properties));
        }else {
            Properties properties = (Properties)standardEnvironment.getPropertySources().get("applicationConfig: [classpath:/application.properties]").getSource();
            propertyUtils.cacheProperties(properties);

        }

        return null;
    }


}
