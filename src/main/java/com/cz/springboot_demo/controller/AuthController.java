package com.cz.springboot_demo.controller;

import com.cz.springboot_demo.pojo.dto.LoginDTO;
import com.cz.springboot_demo.pojo.dto.ResponseMessage;
import com.cz.springboot_demo.service.IUserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

// Since 2025/5/14 by CZ
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private IUserService userService;

    @Operation(summary = "用户登陆")
    @PostMapping("/login")
    public ResponseMessage login(@RequestBody LoginDTO loginDTO) throws AuthenticationException {

            return ResponseMessage.success("Login success", userService.login(loginDTO));

    }
}
