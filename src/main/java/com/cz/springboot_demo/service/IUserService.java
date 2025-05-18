package com.cz.springboot_demo.service;

import com.cz.springboot_demo.pojo.User;
import com.cz.springboot_demo.pojo.dto.LoginDTO;
import com.cz.springboot_demo.pojo.dto.LoginResponseDTO;
import com.cz.springboot_demo.pojo.dto.UserDTO;
import com.cz.springboot_demo.pojo.dto.UserEditDTO;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public interface IUserService  {
    /**
     * 添加用户
     * @param user 要添加的User pojo类
     * @return 返回添加的用户信息
     */
    User add(UserDTO user);

    /**
     * 根据用户ID删除用户
     * @param userId 要删除的用户Id
     */
    void delete(Integer userId);

    /**
     * 修改目标用户
     * @param userDTO
     * @return
     */
    User edit(UserEditDTO userDTO);

    /**
     * 根据userId获取用户
     * @param userId 用户Id
     * @return 返回获得的用户（如果存在），否则抛出异常
     */
    User getUser(Integer userId);

    /**
     * 根据userName获取用户
     * @param userName 用户userName
     * @return 返回用户（如果存在）
     */
    User getUserByUsername(String userName);

    /**
     * 登陆接口
     * @param loginDTO 登陆信息，包含：username，password
     * @return 返回登陆信息，包含：用户token、用户信息
     */
    LoginResponseDTO login(LoginDTO loginDTO) throws AuthenticationException;
}
