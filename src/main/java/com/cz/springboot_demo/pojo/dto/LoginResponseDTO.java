package com.cz.springboot_demo.pojo.dto;

// Since 2025/5/12 by CZ
public class LoginResponseDTO {
    private String token;
    private UserDTO userInfo;

    public LoginResponseDTO(String token, UserDTO userInfo) {
        this.token = token;
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserDTO userInfo) {
        this.userInfo = userInfo;
    }
}
