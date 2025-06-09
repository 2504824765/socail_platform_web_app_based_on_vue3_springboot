package com.cz.springboot_demo.pojo.dto;

// Since 2025/5/12 by CZ
public class LoginResponseDTO {
    private String token;
    private UserCreateDTO userInfo;

    public LoginResponseDTO(String token, UserCreateDTO userInfo) {
        this.token = token;
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserCreateDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserCreateDTO userInfo) {
        this.userInfo = userInfo;
    }
}
