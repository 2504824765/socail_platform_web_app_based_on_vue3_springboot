package com.cz.springboot_demo.pojo;

import jakarta.persistence.*;

// Since 2025/5/11 by CZ
// 将类映射成一个表
@Table(name = "tb_user") // 映射的表名称
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 默认自增生成方式
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_email")
    private String email;
    @Column(name = "gender")
    private String gender;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "phone")
    private String phone;
    @Column(name = "region")
    private String region;
    @Column(name = "role")
    private String role;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userID) {
        this.userId = userID;
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
