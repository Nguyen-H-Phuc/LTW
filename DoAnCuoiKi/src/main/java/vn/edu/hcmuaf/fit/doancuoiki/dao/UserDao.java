package vn.edu.hcmuaf.fit.doancuoiki.dao;

import vn.edu.hcmuaf.fit.doancuoiki.db.DBContext;
import vn.edu.hcmuaf.fit.doancuoiki.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean isEmailExists(String email) {
        String query = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error checking if email exists", e);
        }
        return false;
    }

    public boolean addUser(User user) {
        String query1 = "INSERT INTO users (email, password, role_id, is_Active) VALUES (?, ?, ?, ?)";
        String query2 = "INSERT INTO userinfo (user_id, full_Name, phone_Number, birth_date, address) VALUES (?, ?, ?, ?, ?)";
        boolean success = false;

        try (Connection conn = new DBContext().getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu giao dịch

            // Thêm người dùng vào bảng Users
            try (PreparedStatement ps1 = conn.prepareStatement(query1, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps1.setString(1, user.getEmail());
                ps1.setString(2, user.getPassword());
                ps1.setInt(3, 1); // Role mặc định
                ps1.setBoolean(4, user.isActive());
                int rowsAffected1 = ps1.executeUpdate();

                // Lấy user_id vừa được sinh ra
                int userId = 0;
                if (rowsAffected1 > 0) {
                    try (ResultSet rs = ps1.getGeneratedKeys()) {
                        if (rs.next()) {
                            userId = rs.getInt(1);
                        }
                    }
                }

                // Thêm thông tin người dùng vào bảng UserInfo
                try (PreparedStatement ps2 = conn.prepareStatement(query2)) {
                    ps2.setInt(1, userId);
                    ps2.setString(2, user.getUserInfo().getFullName());
                    ps2.setString(3, user.getUserInfo().getPhoneNumber());
                    ps2.setString(4, user.getUserInfo().getBirthday() != null ? user.getUserInfo().getBirthday().toString() : null);
                    ps2.setString(5, user.getUserInfo().getAddress());
                    int rowsAffected2 = ps2.executeUpdate();

                    // Kiểm tra nếu cả hai câu lệnh đều thành công
                    if (rowsAffected1 > 0 && rowsAffected2 > 0) {
                        conn.commit(); // Commit giao dịch
                        success = true;
                    } else {
                        conn.rollback(); // Rollback nếu có lỗi
                    }
                }

            } catch (SQLException e) {
                if (conn != null) {
                    try {
                        conn.rollback(); // Rollback nếu có lỗi
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return success;
    }



}
