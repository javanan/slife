package com.slife.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.slife.constant.Global;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by chenjianan on 2016/12/13-20:00.
 * <p>
 * Describe: 系统用户bean
 */
@TableName("sys_user")
public class SysUser extends CompanyLinkEntity<SysUser> {

    private static final long serialVersionUID = 1L;



    @TableField(value = "login_name")
    private String loginName;// 登录名
    private String password;// 密码
    private String no;        // 工号
    private String name;    // 姓名
    private String email;    // 邮箱
    private String phone;    // 电话
    private String mobile;    // 手机

    @TableField(value = "login_flag")
    private String loginFlag;    // 是否允许登陆
    private String photo;    // 头像

    /**
     * 角色列表
     */
    @TableField(exist = false)
    private List<SysRole> sysRoles;

    public List<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }

    public SysUser() {
        super();
        this.loginFlag = Global.YES;
    }


    public SysUser(Long id) {
        super(id);
    }

    public SysUser(Long id, String loginName) {
        super(id);
        this.loginName = loginName;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }


    @Override
    public Long getId() {
        return id;
    }


    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getLoginName() {
        return loginName;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Length(min = 1, max = 100, message = "姓名长度必须介于 1 和 100 之间")

    public String getName() {
        return name;
    }

    @Length(min = 1, max = 100, message = "工号长度必须介于 1 和 100 之间")

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Email(message = "邮箱格式不正确")
    @Length(min = 0, max = 200, message = "邮箱长度必须介于 1 和 100 之间")

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 0, max = 200, message = "电话长度必须介于 1 和 20 之间")

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Length(min = 0, max = 200, message = "手机长度必须介于 1 和 20 之间")

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    @Override
    public Date getCreateDate() {
        return createDate;
    }



    /**
     * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
     */

    @JsonIgnore
    public boolean isAdmin() {
        return isAdmin(this.id);
    }

    public static boolean isAdmin(Long id) {

        return id != null && id.equals(Global.ADMIN_ID);
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
