package com.wos.service.impl;

import com.github.pagehelper.PageHelper;
import com.wos.mapper.SysUserMapper;
import com.wos.pojo.SysUser;
import com.wos.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author chenyuwei
 * @date 2018/10/21
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper userMapper;



    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(SysUser user) throws Exception {

        userMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(SysUser user) {
         userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(String userId) {
        userMapper.deleteByPrimaryKey(userId);

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public SysUser queryUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public SysUser queryUserName(String userName) {
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotBlank(userName)){
            criteria.andEqualTo("username",userName);
        }
        List<SysUser> sysUsers = userMapper.selectByExample(example);
        if (sysUsers.size()>0){
            return  sysUsers.get(0);
        }
        return null;
    }

    @Override
    public SysUser getUser(String userName, String passWord) {
        Example example=new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotBlank(userName)){
            criteria.andEqualTo("username",userName);
        }
        if (StringUtils.isNotBlank(passWord)){
            criteria.andEqualTo("password",passWord);
        }
        List<SysUser> sysUsers = userMapper.selectByExample(example);
        if (sysUsers.size()>0){
            return  sysUsers.get(0);
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SysUser> queryUserList(SysUser user) {
        Example example=new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(user.getUsername())){
            criteria.andLike("username", "%" + user.getUsername() + "%");
        }

        if ((StringUtils.isNotBlank(user.getNickname()))){
            criteria.andLike("nickname", "%" + user.getNickname() + "%");
        }
        List<SysUser> userList = userMapper.selectByExample(example);

        return userList;
    }

    @Override
    public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(user.getUsername())){
            criteria.andLike("username", "%" + user.getUsername() + "%");
        }

        if ((StringUtils.isNotBlank(user.getNickname()))){
            criteria.andLike("nickname", "%" + user.getNickname() + "%");
        }
        example.orderBy("regist_time").desc();
        List<SysUser> userList = userMapper.selectByExample(example);
        return userList;
    }
}
