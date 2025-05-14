package com.cz.springboot_demo.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

// Since 2025/5/12 by CZ
public class UserEditDTO {
    private Integer userId;
    @NotBlank(message = "用户名不能为空") // 去除空格之后不能为空
    private String userName;
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 12) // 设定密码的最小最大长度
    private String password;
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @Override
    public String toString() {
        return "UserEditDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
