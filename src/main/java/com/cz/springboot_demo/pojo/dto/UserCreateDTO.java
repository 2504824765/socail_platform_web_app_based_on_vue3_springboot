package com.cz.springboot_demo.pojo.dto;

import jakarta.validation.constraints.NotBlank;

// Since 2025/5/11 by CZ
public class UserCreateDTO {
    @NotBlank(message = "用户名不能为空") // 去除空格之后不能为空
    private String userName;
    @NotBlank(message = "密码不能为空")
//    @Length(min = 6, max = 12) // 设定密码的最小最大长度
    private String password;
    @NotBlank(message = "邮箱不能为空")
    private String email;
    @NotBlank
    private String gender;
    @NotBlank
    private String birthday;
    @NotBlank
    private String phone;
    @NotBlank
    private String region;
//    @NotBlank
    private String role;

    public UserCreateDTO(String userName, String password, String email, String gender, String birthday, String phone, String region) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.region = region;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
