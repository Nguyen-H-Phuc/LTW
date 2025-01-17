package vn.edu.hcmuaf.fit.doancuoiki.dao;

import vn.edu.hcmuaf.fit.doancuoiki.db.DBContext;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class OrderDao {

    public boolean createOrder(int id, String location, Date rentalStartDate, Date expectedReturnDate, int pid) {
        String sql = "insert into orders (customerId, rentalStartDate, expectedReturnDate, deliveryAddress) values(?,?,?,?)";
        System.out.println(pid);
        try(Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql)){
            pre.setInt(1, id);
            pre.setDate(2, rentalStartDate);
            pre.setDate(3, expectedReturnDate);
            pre.setString(4, location);
            pre.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        OrderDao orderDao = new OrderDao();
        try {
            // Chuyển đổi chuỗi ngày tháng thành java.sql.Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Parse chuỗi ngày tháng thành java.util.Date
            java.util.Date startDate = dateFormat.parse("2025-01-02");
            java.util.Date endDate = dateFormat.parse("2025-02-01");

            // Chuyển java.util.Date thành java.sql.Date
            Date rentalStartDate = new Date(startDate.getTime());
            Date expectedReturnDate = new Date(endDate.getTime());

            // Gọi phương thức createOrder
            boolean success = orderDao.createOrder(1, "a", rentalStartDate, expectedReturnDate,1);
            if (success) {
                System.out.println("Order created successfully!");
            } else {
                System.out.println("Failed to create order.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
