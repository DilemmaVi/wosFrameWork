package com.wos.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author chenyuwei
 * @date 2018/10/21
 */

@Table(name = "sys_user")
public class SysUser {
    @Id
    private Integer id;

    /**
     * 用户名/登录名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
    0：女
    1：男
    2：保密
     */
    private Integer sex;

    /**
     * 岗位
     */
    private String job;

    /**
     * 头像地址
     */
    @Column(name = "face_image")
    private String faceImage;

    /**
     * 最后一次登录IP
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 最后一次登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 删除标识
    0：没有删除
    1：已删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 注册时间
     */
    @Column(name = "regist_time")
    private Date registTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名/登录名
     *
     * @return username - 用户名/登录名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名/登录名
     *
     * @param username 用户名/登录名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取性别
    0：女
    1：男
    2：保密
     *
     * @return sex - 性别
    0：女
    1：男
    2：保密
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别
    0：女
    1：男
    2：保密
     *
     * @param sex 性别
    0：女
    1：男
    2：保密
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取岗位
     *
     * @return job - 岗位
     */
    public String getJob() {
        return job;
    }

    /**
     * 设置岗位
     *
     * @param job 岗位
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * 获取头像地址
     *
     * @return face_image - 头像地址
     */
    public String getFaceImage() {
        return faceImage;
    }

    /**
     * 设置头像地址
     *
     * @param faceImage 头像地址
     */
    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    /**
     * 获取最后一次登录IP
     *
     * @return last_login_ip - 最后一次登录IP
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 设置最后一次登录IP
     *
     * @param lastLoginIp 最后一次登录IP
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * 获取最后一次登录时间
     *
     * @return last_login_time - 最后一次登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后一次登录时间
     *
     * @param lastLoginTime 最后一次登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取删除标识
        0：没有删除
        1：已删除
     *
     * @return is_delete - 删除标识
        0：没有删除
        1：已删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 设置删除标识
        0：没有删除
        1：已删除
     *
     * @param isDelete 删除标识
        0：没有删除
        1：已删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取注册时间
     *
     * @return regist_time - 注册时间
     */
    public Date getRegistTime() {
        return registTime;
    }

    /**
     * 设置注册时间
     *
     * @param registTime 注册时间
     */
    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }
}