package vn.edu.hcmuaf.fit.doancuoiki.dao;

import vn.edu.hcmuaf.fit.doancuoiki.db.DBContext;

import java.sql.*;
import java.text.SimpleDateFormat;

public class OrderDao {

    public boolean createOrder(int id, String location, Date rentalStartDate, Date expectedReturnDate, String licensePlate, double priceAtOrder) {
        String sql1 = "insert into orders (customerId, rentalStartDate, expectedReturnDate, deliveryAddress) values(?,?,?,?)";
        String sql2 = "INSERT INTO orderdetails (orderId, licensePlate, priceAtOrder) values(?,?,?)";
        if(licensePlate.equals(""))return false;
        try(Connection conn = new DBContext().getConnection();
            PreparedStatement pre = conn.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS)) {
            pre.setInt(1, id);
            pre.setDate(2, rentalStartDate);
            pre.setDate(3, expectedReturnDate);
            pre.setString(4, location);
            int rowsAffected1 = pre.executeUpdate();

            // Lấy user_id vừa được sinh ra
            int orderId = 0;
            if (rowsAffected1 > 0) {
                try (ResultSet rs = pre.getGeneratedKeys()) {
                    if (rs.next()) {
                        orderId = rs.getInt(1);
                    }
                }
            }

            int rowsAffected2;
            try (PreparedStatement pre2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {
                pre2.setInt(1, orderId);
                pre2.setString(2, licensePlate);
                pre2.setDouble(3, priceAtOrder);
                rowsAffected2 = pre2.executeUpdate();
            }
            if (rowsAffected1 > 0 && rowsAffected2 > 0){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public String getLicensePlate(int vehicleType, Date rentalStartDate, Date expectedReturnDate) {
        String sql = "SELECT v.licensePlate\n" +
                "FROM vehicles v\n" +
                "WHERE v.typeId = ? and v.isInUse = 1 -- Điều kiện typeId được áp dụng ở truy vấn ngoài\n" +
                "  AND v.licensePlate NOT IN (\n" +
                "    SELECT od.licensePlate\n" +
                "    FROM orderDetails od\n" +
                "    JOIN orders o ON od.orderId = o.id\n" +
                "    WHERE (o.rentalStartDate <= ?) \n" +
                "      AND (o.expectedReturnDate BETWEEN ? AND ? \n" +
                "           OR o.expectedReturnDate > ?) \n" +
                "     AND o.status NOT LIKE \"Đã huỷ\"\n" +
                ");";
        String licensePlate = "";
        try(Connection conn = new DBContext().getConnection();
        PreparedStatement pre = conn.prepareStatement(sql)){
            pre.setInt(1, vehicleType);
            pre.setDate(2, expectedReturnDate);
            pre.setDate(3, rentalStartDate);
            pre.setDate(4,expectedReturnDate);
            pre.setDate(5, expectedReturnDate);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                licensePlate = rs.getString("licensePlate");
            }
            pre.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return licensePlate;
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
            String liencep= orderDao.getLicensePlate(1, rentalStartDate, expectedReturnDate);
            boolean success = orderDao.createOrder(1, "a", rentalStartDate, expectedReturnDate, liencep, 1000.0 );
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
