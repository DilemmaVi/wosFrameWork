package com.wos.service;

import com.wos.pojo.SysUser;

import java.util.List;

/**
 * @author chenyuwei
 * @date 2018/10/21
 */
public interface UserService {
    public void saveUser(SysUser user) throws Exception;

    public void updateUser(SysUser user);

    public void deleteUser(String userId);

    public SysUser queryUserById(String userId);

    public SysUser queryUserName(String userName);

    public SysUser getUser(String userName,String passWord);

    public List<SysUser> queryUserList(SysUser user);

    public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize);

}
