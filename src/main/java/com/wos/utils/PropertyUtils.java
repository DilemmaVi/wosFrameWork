package com.wos.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.logging.log4j.LogManager;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chenyuwei
 * @date 2018/9/8
 */
@Component
public class PropertyUtils   {
    private static org.apache.logging.log4j.Logger log=LogManager.getLogger(PropertyUtils.class);
    private static NodeCache cache;
    private Properties properties;
    private static final Map<String, String> CACHE_PROPERTIES = new ConcurrentHashMap<>();
    private static final String ENCODING = "UTF-8";


    public Properties loadProperties(StandardEnvironment standardEnvironment){
        Map<String, String> props=new HashMap<>(3);
        props.put("zookeeper.address",standardEnvironment.getProperty("zookeeper.address"));
        props.put("app.name",standardEnvironment.getProperty("app.name"));
        props.put("app.version",standardEnvironment.getProperty("app.version"));
        loadPropertiesFromZookeeper(props);
        return properties;
    }


    private void loadPropertiesFromZookeeper(Map<String, String> props) {
        log.info("开始从zookeeper获取配置信息");
        String zookeeperAddress = props.get("zookeeper.address");
        String appName = props.get("app.name");
        String appVersion = props.get("app.version");
        if (StringUtils.isBlank(zookeeperAddress) || StringUtils.isBlank(appName)
                || StringUtils.isBlank(appVersion)) {
            throw new RuntimeException("配置项[zookeeper.address, app.name, app.version]不能为空");
        }
        try {
            CuratorFramework client = ZookeeperUtil.getClient(zookeeperAddress);
            String path = "/configuration/" + appName + "/" + appVersion + "/config";
            byte[] data = client.getData().forPath(path);
            properties = loadRemoteProperties(data);
            cacheProperties(properties);
            log.info("获取远程配置信息成功");

            cache = new NodeCache(client, path);
            cache.start(true);
            cache.getListenable().addListener(() -> {
                ChildData data1 = cache.getCurrentData();
                log.info("远程配置信息变更，重新加载远程配置信息");
                properties = loadRemoteProperties(data1.getData());
                cacheProperties(properties);
            });
        } catch (Exception e) {
            throw new RuntimeException("从zookeeper获取配置信息失败", e);
        }
        log.info("配置文件初始化完成");
    }



    private  Properties loadRemoteProperties(byte[] data) throws IOException {

        String config = new String(data, ENCODING);
        Properties p = new Properties();
        p.load(new StringReader(config));
        return p;
    }

    /**
     * 缓存配置信息
     *
     * @param p 具体配置
     */
    public   void cacheProperties(Properties p) {
        for (Object key : p.keySet()) {
            if (key instanceof String) {
                Object value = p.get(key);
                if (value instanceof String) {
                    PropertyUtils.set((String) key, (String) value);
                }
            }
        }
    }
    public static String get(String key) {
        return CACHE_PROPERTIES.get(key);
    }

    protected static Map<String, String> getProperties() {
        return CACHE_PROPERTIES;
    }

    private static void set(String key, String value) {
        CACHE_PROPERTIES.put(key, value);
    }




}
