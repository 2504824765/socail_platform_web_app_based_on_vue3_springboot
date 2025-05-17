package com.cz.springboot_demo.controller;

import com.cz.springboot_demo.pojo.User;
import com.cz.springboot_demo.pojo.dto.ResponseMessage;
import com.cz.springboot_demo.pojo.dto.UserDTO;
import com.cz.springboot_demo.pojo.dto.UserEditDTO;
import com.cz.springboot_demo.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

// Since 2025/5/11 by CZ
@CrossOrigin
@RestController // 接口方法返回对象 直接转换成JSON文本
@RequestMapping("/api/user") // 访问方法：localhost:8080/user
public class UserController {
    @Autowired
    IUserService userService;

    // Add:Post
    // 流程：通过localhost:8080/user的post方法，调用业务逻辑层的add，调用repository的save
    @PostMapping // 可以指定访问接口
    @Operation(summary = "添加用户")
    public ResponseMessage addUser(@Validated @RequestBody UserDTO user) {
        User newUser = userService.add(user);
        return ResponseMessage.success("Add success", newUser);
    }

    // Delete:Delete
    @DeleteMapping("/{userId}")
    @Operation(summary = "删除用户")
    public ResponseMessage deleteUser(@PathVariable Integer userId) {
        userService.delete(userId);
        return ResponseMessage.success("Delete success");
    }

    // Edit:Put
    @PutMapping
    @Operation(summary = "修改用户数据")
    public ResponseMessage updateUser(@Validated @RequestBody UserEditDTO userEditDTO) {
        User newUser = userService.edit(userEditDTO);
        return ResponseMessage.success("Edit success", newUser);
    }

    // Search:Get
    @GetMapping("/{userId}")
    @Operation(summary = "查询用户")
    public ResponseMessage getUser(@PathVariable Integer userId) {
        User user = userService.getUser(userId);
        return ResponseMessage.success("Search success", user);
    }
}
