package com.wos.utils;


import org.apache.commons.lang.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException.NoNodeException;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;


/**
 * @author chenyuwei
 * @date 2018/9/8
 */
public class ZookeeperUtil {

    private static CuratorFramework client;

    public static final String TYPE_CONFIGURATION = "configuration";

    @Value("$(zookeeper.address)")
    public static String ADDRESS;
    /**
     * 缓存client
     * @param c
     */
    protected synchronized static void cache(CuratorFramework c){
        client = c;
    }
    public static CuratorFramework getClient(){
        if(client == null){
            return getClient(ADDRESS);
        }
        return client;
    }
    public static synchronized CuratorFramework getClient(String address){
        if(client == null){
            if(StringUtils.isEmpty(address)){
                throw new RuntimeException("zookeeper地址不能为空");
            }
            //解决 zookeeper地址解析异常
            address = address.replace("zookeeper://", "");
            address = address.replace("?backup=", ",");
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
            client = CuratorFrameworkFactory.newClient(address, retryPolicy);
            client.start();
        }
        return client;
    }

    public static String getStringData(Stat stat, String path, String encode){
        try {
            byte[]	data = getClient().getData().storingStatIn(stat).forPath(path);
            return new String(data, encode);
        }catch(NoNodeException noNode){
            return null;
        }
        catch (Exception e) {
            throw new RuntimeException("获取信息失败", e);
        }
    }
    public static String getStringData(String path, String encode){
        try {
            byte[]	data = getClient().getData().forPath(path);
            return new String(data, encode);
        }catch(NoNodeException noNode){
            return null;
        }
        catch (Exception e) {
            throw new RuntimeException("获取信息失败", e);
        }
    }

    public static void saveOrUpdate(CuratorFramework client, String path, byte[] data) throws Exception{
        Stat stat = client.checkExists().forPath(path);
        if(stat != null){
            client.setData().forPath(path, data);
        }else{
            client.create().creatingParentsIfNeeded().forPath(path, data);
        }
    }
}
