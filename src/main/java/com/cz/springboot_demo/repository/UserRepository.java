//package com.cz.springboot_demo.repository;
//
//import com.cz.springboot_demo.pojo.User;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//// 这里CrudRepository可以自动帮我们写好操作方法，User为我们要操作的pojo类，Integer为该pojo类唯一标识符(UserId)
//@Repository // 注册为spring的bean
//public interface UserRepository extends CrudRepository<User, Integer> {
//    Optional<User> findByUserName(String userName);
//    List<User> findByRole(String userName);
//    Optional<User> findByUserId(Long id);
//}
package com.cz.springboot_demo.repository;

import com.cz.springboot_demo.pojo.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    // 数据库连接信息（请替换成你的配置）
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/springboot_cz_demo?serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "12345678";

    // 获取数据库连接
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    // 1. 新增用户
    public void save(User user) {
        String sql = "INSERT INTO tb_user (user_name, user_password, user_email, gender, birthday, phone, region, role) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getGender());
            ps.setString(5, user.getBirthday());
            ps.setString(6, user.getPhone());
            ps.setString(7, user.getRegion());
            ps.setString(8, user.getRole());
            ps.executeUpdate();

            // 获取自增ID（如果数据库支持）
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setUserId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. 根据ID查询用户
    public Optional<User> findById(Integer id) {
        String sql = "SELECT * FROM tb_user WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // 3. 根据用户名查询用户
    public Optional<User> findByUserName(String userName) {
        String sql = "SELECT * FROM tb_user WHERE user_name = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // 4. 查询所有用户
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM tb_user";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // 5. 更新用户
    public void update(User user) {
        String sql = "UPDATE tb_user SET user_name = ?, user_password = ?, user_email = ?, " +
                "gender = ?, birthday = ?, phone = ?, region = ?, role = ? " +
                "WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getGender());
            ps.setString(5, user.getBirthday());
            ps.setString(6, user.getPhone());
            ps.setString(7, user.getRegion());
            ps.setString(8, user.getRole());
            ps.setInt(9, user.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 6. 根据ID删除用户
    public void deleteById(Integer id) {
        String sql = "DELETE FROM tb_user WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 在 UserRepository 类中添加以下方法：

    // 查询用户数量
    public long count() {
        String sql = "SELECT COUNT(*) FROM tb_user";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 根据角色查询用户
    public List<User> findByRole(String role) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM tb_user WHERE role = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // 检查用户是否存在
    public boolean existsById(Integer id) {
        String sql = "SELECT COUNT(*) FROM tb_user WHERE user_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 辅助方法：将 ResultSet 转换为 User 对象
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUserName(rs.getString("user_name"));
        user.setPassword(rs.getString("user_password"));
        user.setEmail(rs.getString("user_email"));
        user.setGender(rs.getString("gender"));
        user.setBirthday(rs.getString("birthday"));
        user.setPhone(rs.getString("phone"));
        user.setRegion(rs.getString("region"));
        user.setRole(rs.getString("role"));
        return user;
    }
}