package com.cz.springboot_demo.repository;

import com.cz.springboot_demo.pojo.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// 这里CrudRepository可以自动帮我们写好操作方法，User为我们要操作的pojo类，Integer为该pojo类唯一标识符(UserId)
@Repository // 注册为spring的bean
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}
