package com.cz.springboot_demo.pojo.dto;

import jakarta.validation.constraints.NotBlank;

// Since 2025/5/12 by CZ
public class LoginDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
