package com.cz.springboot_demo.service;

import com.cz.springboot_demo.exception.UserAlreadyExistException;
import com.cz.springboot_demo.exception.UserNotFoundException;
//import com.cz.springboot_demo.mapper.UserMapper;
import com.cz.springboot_demo.pojo.User;
import com.cz.springboot_demo.pojo.dto.*;
import com.cz.springboot_demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.naming.AuthenticationException;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    // 构造函数注入（不再使用Spring的@Autowired）
    public UserService() {
        this.userRepository = new UserRepository();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public long getUserCount() {
        return userRepository.count();
    }

    @Override
    public User add(UserCreateDTO userCreateDto) {
        // 检查用户名是否已存在
        if (userRepository.findByUserName(userCreateDto.getUserName()).isPresent()) {
            throw new UserAlreadyExistException("UserName already exist");
        }

        // 转换DTO -> User
        User user = new User();
        user.setUserName(userCreateDto.getUserName());
        user.setPassword(userCreateDto.getPassword());
        user.setEmail(userCreateDto.getEmail());
        user.setGender(userCreateDto.getGender());
        user.setBirthday(userCreateDto.getBirthday());
        user.setPhone(userCreateDto.getPhone());
        user.setRegion(userCreateDto.getRegion());
        user.setRole("USER"); // 默认角色

        // 保存用户
        userRepository.save(user);
        return user;
    }

    @Override
    public void delete(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public User edit(UserEditDTO userEditDTO) {
        Optional<User> optionalUser = userRepository.findByUserName(userEditDTO.getUserName());
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("User not found");
        }

        User existingUser = optionalUser.get();
        // 更新属性（排除ID）
        existingUser.setUserName(userEditDTO.getUserName());
        existingUser.setPassword(userEditDTO.getPassword());
        existingUser.setEmail(userEditDTO.getEmail());
        existingUser.setGender(userEditDTO.getGender());
        existingUser.setBirthday(userEditDTO.getBirthday());
        existingUser.setPhone(userEditDTO.getPhone());
        existingUser.setRegion(userEditDTO.getRegion());

        userRepository.update(existingUser);
        return existingUser;
    }

    @Override
    public User getUser(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        return user.get();
    }

    @Override
    public User getUserByUsername(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        return user.get();
    }

    @Override
    public LoginResponseDTO login(LoginDTO loginDTO) throws AuthenticationException {
        Optional<User> user = userRepository.findByUserName(loginDTO.getUsername());
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found");
        }

        // 验证密码
        if (!loginDTO.getPassword().equals(user.get().getPassword())) {
            throw new AuthenticationException("Wrong password");
        }

        // 生成Token
        String token = UUID.randomUUID().toString();
        return new LoginResponseDTO(token, convertUser2UserDTO(user.get()));
    }

    @Override
    public List<User> getAllCompany() {
        return userRepository.findByRole("deliveryCo");
    }

    // 转换User -> UserCreateDTO
    private UserCreateDTO convertUser2UserDTO(User user) {
        return new UserCreateDTO(
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getGender(),
                user.getBirthday(),
                user.getPhone(),
                user.getRegion()
        );
    }
}

//package com.cz.springboot_demo.service;
//
//import com.cz.springboot_demo.exception.UserAlreadyExistException;
//import com.cz.springboot_demo.exception.UserNotFoundException;
////import com.cz.springboot_demo.mapper.UserMapper;
//import com.cz.springboot_demo.pojo.User;
//import com.cz.springboot_demo.pojo.dto.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.naming.AuthenticationException;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//@Transactional  // 添加事务管理
//public class UserService implements IUserService {
//
//    private final UserMapper userMapper;  // 替换为MyBatis的Mapper
//
//    @Autowired  // 构造函数注入（推荐方式）
//    public UserService(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        return userMapper.findAll();  // 直接调用Mapper方法
//    }
//
//    @Override
//    public long getUserCount() {
//        return userMapper.count();
//    }
//
//    @Override
//    public User add(UserCreateDTO userCreateDto) {
//        // 检查用户名是否已存在
//        if (userMapper.findByUserName(userCreateDto.getUserName()).isPresent()) {
//            throw new UserAlreadyExistException("UserName already exist");
//        }
//        User user = new User();
//        user.setUserName(userCreateDto.getUserName());
//        user.setPassword(userCreateDto.getPassword());
//        user.setEmail(userCreateDto.getEmail());
//        user.setGender(userCreateDto.getGender());
//        user.setBirthday(userCreateDto.getBirthday());
//        user.setPhone(userCreateDto.getPhone());
//        user.setRegion(userCreateDto.getRegion());
//        user.setRole("USER");
//        userMapper.save(user);
//        return user;
//    }
//
//    @Override
//    public void delete(Integer userId) {
//        if (!userMapper.existsById(userId)) {
//            throw new UserNotFoundException("User not found");
//        }
//        userMapper.deleteById(userId);
//    }
//
//    @Override
//    public User edit(UserEditDTO userEditDTO) {
//        Optional<User> optionalUser = userMapper.findByUserName(userEditDTO.getUserName());
//        if (!optionalUser.isPresent()) {
//            throw new UserNotFoundException("User not found");
//        }
//        User existingUser = optionalUser.get();
//        existingUser.setUserName(userEditDTO.getUserName());
//        existingUser.setPassword(userEditDTO.getPassword());
//        existingUser.setEmail(userEditDTO.getEmail());
//        existingUser.setGender(userEditDTO.getGender());
//        existingUser.setBirthday(userEditDTO.getBirthday());
//        existingUser.setPhone(userEditDTO.getPhone());
//        existingUser.setRegion(userEditDTO.getRegion());
//        userMapper.update(existingUser);
//        return existingUser;
//    }
//
//    @Override
//    public User getUser(Integer userId) {
//        return userMapper.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException("User not found"));
//    }
//
//    @Override
//    public User getUserByUsername(String userName) {
//        return userMapper.findByUserName(userName)
//                .orElseThrow(() -> new UserNotFoundException("User not found"));
//    }
//
//    @Override
//    public LoginResponseDTO login(LoginDTO loginDTO) throws AuthenticationException {
//        User user = getUserByUsername(loginDTO.getUsername());
//
//        // 验证密码（实际项目中应使用密码加密验证！）
//        if (!loginDTO.getPassword().equals(user.getPassword())) {
//            throw new AuthenticationException("Wrong password");
//        }
//
//        // 生成模拟Token
//        String token = UUID.randomUUID().toString();
//        return new LoginResponseDTO(token, convertUser2UserDTO(user));
//    }
//
//    @Override
//    public List<User> getAllCompany() {
//        return userMapper.findByRole("deliveryCo");  // 注意角色名称拼写一致
//    }
//
//    private UserCreateDTO convertUser2UserDTO(User user) {
//        return new UserCreateDTO(
//                user.getUserName(),
//                user.getPassword(),
//                user.getEmail(),
//                user.getGender(),
//                user.getBirthday(),
//                user.getPhone(),
//                user.getRegion()
//        );
//    }
//}