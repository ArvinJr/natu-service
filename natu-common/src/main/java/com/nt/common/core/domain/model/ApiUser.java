package com.nt.common.core.domain.model;

import java.io.Serializable;

/**
 * @Author: 唐僧
 * @Desc:
 */
public class ApiUser implements Serializable {
    private static final long serialVersionUID = 768788313972112861L;

    /**
     * 登录人联系方式
     */
    private String userPhone;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String password;


    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "userPhone='" + userPhone + '\'' +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
