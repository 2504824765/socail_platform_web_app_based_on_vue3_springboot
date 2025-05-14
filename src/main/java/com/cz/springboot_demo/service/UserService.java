package com.cz.springboot_demo.service;

import com.cz.springboot_demo.exception.UserNotFoundException;
import com.cz.springboot_demo.pojo.User;
import com.cz.springboot_demo.pojo.dto.UserDTO;
import com.cz.springboot_demo.pojo.dto.UserEditDTO;
import com.cz.springboot_demo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Since 2025/5/11 by CZ
@Service // 配制成spring的bean
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User add(UserDTO userDto) {
        // 调用数据访问类方法
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User edit(UserEditDTO userEditDTO) {
        User user = new User();
        BeanUtils.copyProperties(userEditDTO,user);
        if (userRepository.existsById(userEditDTO.getUserId())) {
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public User getUser(Integer userId) {
        if (userRepository.existsById(userId)) {
            return userRepository.findById(userId).get();
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}
