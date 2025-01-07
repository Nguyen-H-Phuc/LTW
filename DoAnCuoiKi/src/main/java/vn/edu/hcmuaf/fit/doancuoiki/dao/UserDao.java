package vn.edu.hcmuaf.fit.doancuoiki.dao;

import vn.edu.hcmuaf.fit.doancuoiki.db.DBContext;
import vn.edu.hcmuaf.fit.doancuoiki.model.User;
import vn.edu.hcmuaf.fit.doancuoiki.model.UserInfo;

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

    public int isActive(String email) throws SQLException {
        String query = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { // Kiểm tra có dữ liệu không
                    return rs.getInt("is_active");
                } else {
                    throw new SQLException("No user found with the given email.");
                }
            }
        }
    }


    public User getUser(String email, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try(Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UserInfo ui = getUserInfo(rs.getInt("user_id"));
                    return new User(rs.getInt("user_id"),
                            rs.getString("email"),
                            rs.getString("password"),
                            ui,
                            rs.getInt("role_id"),
                            rs.getBoolean("is_active"));
                } else { return null;
                }
            }
        }
    }

    public UserInfo getUserInfo(int userId) {
        String query = "SELECT * FROM userinfo WHERE user_id = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, userId); // Sửa lỗi truyền tham số
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { // Kiểm tra kết quả trước khi truy xuất
                    return new UserInfo(
                            rs.getString("full_name"),
                            rs.getString("phone_number"),
                            rs.getString("address"), // Xóa khoảng trắng thừa
                            rs.getDate("birth_date").toLocalDate()
                    );
                } else {
                    return null; // Trường hợp không có bản ghi
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving user info", e);
        }
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

    public static void main(String[] args) throws SQLException {
        UserDao dao = new UserDao();
        System.out.println(dao.isActive("e@gmail.com"));
        System.out.println(dao.isEmailExists("e@gmail.com"));
        String password= "b22343e71c4519d810350eca84caf214";
        System.out.println(dao.getUser("e@gmail.com", password).getUserInfo().getFullName());
    }
}
