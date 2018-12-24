package com.wos.service;

import com.alibaba.fastjson.JSONObject;
import com.wos.pojo.SysUser;

import java.util.List;

/**
 * @author chenyuwei
 * @date 2018/10/21
 */
public interface UserService {
     void saveUser(SysUser user) throws Exception;

     void updateUser(SysUser user);

     void deleteUser(String userId);

     SysUser queryUserById(String userId);

     SysUser queryUserName(String userName);

     SysUser getUser(String userName,String passWord);

     List<SysUser> queryUserList(SysUser user);

     List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize);


     SysUser authLogin(JSONObject requestJson);
}
