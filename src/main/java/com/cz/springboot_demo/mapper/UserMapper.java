//package com.cz.springboot_demo.mapper;  // 注意包名改为mapper
//
//import com.cz.springboot_demo.pojo.User;
//import org.apache.ibatis.annotations.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@Mapper
//public interface UserMapper {
//
//    // 1. 新增用户（返回自增ID）
//    @Insert("INSERT INTO tb_user (user_name, user_password, user_email, gender, birthday, phone, region, role) " +
//            "VALUES (#{userName}, #{password}, #{email}, #{gender}, #{birthday}, #{phone}, #{region}, #{role})")
//    @Options(useGeneratedKeys = true, keyProperty = "userId")  // 获取自增ID
//    void save(User user);
//
//    // 2. 根据ID查询用户
//    @Select("SELECT * FROM tb_user WHERE user_id = #{id}")
//    Optional<User> findById(Integer id);
//
//    // 3. 根据用户名查询用户
//    @Select("SELECT * FROM tb_user WHERE user_name = #{userName}")
//    Optional<User> findByUserName(String userName);
//
//    // 4. 查询所有用户
//    @Select("SELECT * FROM tb_user")
//    List<User> findAll();
//
//    // 5. 更新用户
//    @Update("UPDATE tb_user SET user_name = #{userName}, user_password = #{password}, " +
//            "user_email = #{email}, gender = #{gender}, birthday = #{birthday}, " +
//            "phone = #{phone}, region = #{region}, role = #{role} " +
//            "WHERE user_id = #{userId}")
//    void update(User user);
//
//    // 6. 根据ID删除用户
//    @Delete("DELETE FROM tb_user WHERE user_id = #{id}")
//    void deleteById(Integer id);
//
//    // 7. 查询用户数量
//    @Select("SELECT COUNT(*) FROM tb_user")
//    long count();
//
//    // 8. 根据角色查询用户
//    @Select("SELECT * FROM tb_user WHERE role = #{role}")
//    List<User> findByRole(String role);
//
//    // 9. 检查用户是否存在
//    @Select("SELECT COUNT(*) FROM tb_user WHERE user_id = #{id}")
//    boolean existsById(Integer id);
//}