package vn.edu.hcmuaf.fit.doancuoiki.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.fit.doancuoiki.db.DBContext;
import vn.edu.hcmuaf.fit.doancuoiki.db.JDBIConnector;
import vn.edu.hcmuaf.fit.doancuoiki.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class    ProductDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs    = null;
// lấy tất cả sản phẩm

    //    chia sản phẩm cho từng trang
    public List<Product> getPageProduct(int pageIndex) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products "
                + "ORDER BY id "
                + "LIMIT 8 OFFSET ?";
        System.out.println("Executing SQL: " + query);

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, (pageIndex - 1) * 8); // Tính toán OFFSET
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("year"),
                        rs.getString("brand"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("img"),
                        rs.getString("numberPlate")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    // lấy id của sản phẩm
    public Product getProductById(int id) {
        Product product = null;
        String query = "SELECT * FROM products WHERE id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("year"),
                        rs.getString("brand"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("img"),
                        rs.getString("numberPlate")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
    // Lấy 3 sản phẩm liên quan khi vào trang chi tiết sản phẩm
    public List<Product> getRelatedProducts(int productId) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products " +
                "WHERE id != ? " +
                "ORDER BY RAND() " +
                "LIMIT 2"; // Lấy ngẫu nhiên 3 sản phẩm khác sản phẩm hiện tại
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId); // Loại bỏ sản phẩm hiện tại
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("year"),
                        rs.getString("brand"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("img"),
                        rs.getString("numberPlate")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    // product new
    public List<Product> getLast8Products() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products ORDER BY id DESC LIMIT 8";  // Adjust the query to fetch the last 8 products

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("year"),
                        rs.getString("brand"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("img"),
                        rs.getString("numberPlate")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    // Fetch the 8 most expensive products
    public List<Product> getMostExpensiveProducts() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products ORDER BY price DESC LIMIT 8";  // Fetch the 8 most expensive products

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("year"),
                        rs.getString("brand"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("img"),
                        rs.getString("numberPlate")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products WHERE name LIKE ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" +txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("year"),
                        rs.getString("brand"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("img"),
                        rs.getString("numberPlate")
                ));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<Product> findALl() {
        Jdbi jdbi = JDBIConnector.getJdbi();
        List<Product> products = jdbi.withHandle(handle -> {
            String sql = "SELECT * FROM vehicletypes";
            return handle.createQuery(sql).mapToBean(Product.class).stream().collect(Collectors.toList());

        });
        return products;
    }


    public List<Product> findById(int id) {
        Jdbi jdbi = JDBIConnector.getJdbi();
        List<Product> products = jdbi.withHandle(handle -> {
            String sql = "SELECT * FROM vehicletypes where id =?";
            return handle.createQuery(sql).bind(0,id).mapToBean(Product.class).stream().collect(Collectors.toList());

        });
        return products;
    }
    public List<Product> listPro(String sdate, String edate ,int a ) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT \n" +
                "    vt.id,\n" +
                "    vt.name AS name,\n" +
                "    vt.brand, \n" +
                "    vt.category,\n" +
                "    vt.rentalPrice,\n" +
                "vt.image,\n" +
                "    vt.totalVehicles,\n" +
                "    vt.description, \n" +
                "--     COUNT(od.licensePlate) AS totalRentals, \n" +
                "    vt.totalVehicles - COUNT(od.licensePlate) AS availableVehicles\n" +
                "FROM \n" +
                "    vehicleTypes vt\n" +
                "LEFT JOIN \n" +
                "    vehicles v ON vt.id = v.typeId\n" +
                "LEFT JOIN \n" +
                "    orderDetails od ON v.licensePlate = od.licensePlate\n" +
                "LEFT JOIN \n" +
                "    orders o ON od.orderId = o.id\n" +
                "WHERE \n" +
                "    vt.isAvailable = 1 \n" +
                "    AND (\n" +
                "        (o.rentalStartDate <= ? OR o.rentalStartDate BETWEEN ? AND ?) \n" +
                "        AND (o.expectedReturnDate BETWEEN ? AND ? OR o.expectedReturnDate > ?)\n" +
                "        OR o.id IS NULL -- Đảm bảo lấy các loại xe không có đơn thuê\n" +
                "    )\n" +
                "GROUP BY \n" +
                "    vt.name, vt.totalVehicles\n" +
                "HAVING \n" +
                "    availableVehicles > ?;";
        try{
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, sdate);
            ps.setString(2, sdate);
            ps.setString(3, edate);
            ps.setString(4, sdate);
            ps.setString(5, edate);
            ps.setString(6, edate);
            ps.setInt(7, a);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        0,
                        rs.getString("brand"),
                        rs.getString("category"),
                        rs.getDouble("rentalPrice"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getInt("totalVehicles")
                ));
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public int countProducts(String sdate, String edate, int a) {
        String countQuery = "SELECT COUNT(*) AS rowCount FROM ("
                + "SELECT \n"
                + "    vt.id,\n"
                + "    vt.name AS name,\n"
                + "    vt.brand, \n"
                + "    vt.category,\n"
                + "    vt.rentalPrice,\n"
                + "    vt.image,\n"
                + "    vt.totalVehicles,\n"
                + "    vt.description, \n"
                + "    vt.totalVehicles - COUNT(od.licensePlate) AS availableVehicles\n"
                + "FROM \n"
                + "    vehicleTypes vt\n"
                + "LEFT JOIN \n"
                + "    vehicles v ON vt.id = v.typeId\n"
                + "LEFT JOIN \n"
                + "    orderDetails od ON v.licensePlate = od.licensePlate\n"
                + "LEFT JOIN \n"
                + "    orders o ON od.orderId = o.id\n"
                + "WHERE \n"
                + "    vt.isAvailable = 1 \n"
                + "    AND (\n"
                + "        (o.rentalStartDate <= ? OR o.rentalStartDate BETWEEN ? AND ?) \n"
                + "        AND (o.expectedReturnDate BETWEEN ? AND ? OR o.expectedReturnDate > ?)\n"
                + "        OR o.id IS NULL \n"
                + "    )\n"
                + "GROUP BY \n"
                + "    vt.name, vt.totalVehicles\n"
                + "HAVING \n"
                + "    availableVehicles > ?"
                + ") AS subquery";

        try {
            conn = new DBContext().getConnection(); // Kết nối DB
            ps = conn.prepareStatement(countQuery); // Chuẩn bị truy vấn
            ps.setString(1, sdate);
            ps.setString(2, sdate);
            ps.setString(3, edate);
            ps.setString(4, sdate);
            ps.setString(5, edate);
            ps.setString(6, edate);
            ps.setInt(7, a); // Thiết lập tham số cuối
            rs = ps.executeQuery();
            if (rs.next()) {
                int rowCount = rs.getInt("rowCount"); // Lấy kết quả đếm
                return rowCount;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Debug lỗi
        } finally {
            // Đóng các tài nguyên (ResultSet, PreparedStatement, Connection)
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0; // Trả về 0 nếu có lỗi
    }
    public List<Product> listPro(String sdate, String edate, int a, int pageIndex) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT \n" +
                "    vt.id,\n" +
                "    vt.name AS name,\n" +
                "    vt.brand, \n" +
                "    vt.category,\n" +
                "    vt.rentalPrice,\n" +
                "    vt.image,\n" +
                "    vt.totalVehicles,\n" +
                "    vt.description, \n" +
                "    vt.totalVehicles - COUNT(od.licensePlate) AS availableVehicles\n" +
                "FROM \n" +
                "    vehicleTypes vt\n" +
                "LEFT JOIN \n" +
                "    vehicles v ON vt.id = v.typeId\n" +
                "LEFT JOIN \n" +
                "    orderDetails od ON v.licensePlate = od.licensePlate\n" +
                "LEFT JOIN \n" +
                "    orders o ON od.orderId = o.id\n" +
                "WHERE \n" +
                "    vt.isAvailable = 1 \n" +
                "    AND (\n" +
                "        (o.rentalStartDate <= ? OR o.rentalStartDate BETWEEN ? AND ?) \n" +
                "        AND (o.expectedReturnDate BETWEEN ? AND ? OR o.expectedReturnDate > ?)\n" +
                "        OR o.id IS NULL -- Đảm bảo lấy các loại xe không có đơn thuê\n" +
                "    )\n" +
                "GROUP BY \n" +
                "    vt.id, vt.name, vt.brand, vt.category, vt.rentalPrice, vt.image, vt.totalVehicles, vt.description\n" +
                "HAVING \n" +
                "    availableVehicles > ?\n" +
                "LIMIT 8 OFFSET ?;"; // Phân trang với LIMIT 8 và OFFSET dựa trên pageIndex

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, sdate);
            ps.setString(2, sdate);
            ps.setString(3, edate);
            ps.setString(4, sdate);
            ps.setString(5, edate);
            ps.setString(6, edate);
            ps.setInt(7, a);
            ps.setInt(8, (pageIndex - 1) * 8); // Tính toán OFFSET dựa trên trang hiện tại
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        0,
                        rs.getString("brand"),
                        rs.getString("category"),
                        rs.getDouble("rentalPrice"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getInt("totalVehicles")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public List<Product> listPro1(String sdate, String edate, int a, int pageIndex) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT \n" +
                "    vt.id,\n" +
                "    vt.name AS name,\n" +
                "    vt.brand, \n" +
                "    vt.category,\n" +
                "    vt.rentalPrice,\n" +
                "    vt.image,\n" +
                "    vt.totalVehicles,\n" +
                "    vt.description, \n" +
                "    vt.totalVehicles - COUNT(od.licensePlate) AS availableVehicles\n" +
                "FROM \n" +
                "    vehicleTypes vt\n" +
                "LEFT JOIN \n" +
                "    vehicles v ON vt.id = v.typeId\n" +
                "LEFT JOIN \n" +
                "    orderDetails od ON v.licensePlate = od.licensePlate\n" +
                "LEFT JOIN \n" +
                "    orders o ON od.orderId = o.id\n" +
                "WHERE \n" +
                "    vt.isAvailable = 1 \n" +
                "    AND (\n" +
                "        (o.rentalStartDate <= ? OR o.rentalStartDate BETWEEN ? AND ?) \n" +
                "        AND (o.expectedReturnDate BETWEEN ? AND ? OR o.expectedReturnDate > ?)\n" +
                "        OR o.id IS NULL -- Đảm bảo lấy các loại xe không có đơn thuê\n" +
                "    )\n" +
                "GROUP BY \n" +
                "    vt.id, vt.name, vt.brand, vt.category, vt.rentalPrice, vt.image, vt.totalVehicles, vt.description\n" +
                "HAVING \n" +
                "    availableVehicles > ?\n" +
                "LIMIT 8 OFFSET ?;"; // Phân trang với LIMIT 8 và OFFSET dựa trên pageIndex

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, sdate);
            ps.setString(2, sdate);
            ps.setString(3, edate);
            ps.setString(4, sdate);
            ps.setString(5, edate);
            ps.setString(6, edate);
            ps.setInt(7, a);
            ps.setInt(8, (pageIndex - 1) * 8); // Tính toán OFFSET dựa trên trang hiện tại
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        0,
                        rs.getString("brand"),
                        rs.getString("category"),
                        rs.getDouble("rentalPrice"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getInt("totalVehicles")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public Product getUnbookedProductById(int id) {
        Product product = null;
        String query = "SELECT vt.id, vt.name, vt.brand, vt.category, vt.rentalPrice, vt.image, vt.totalVehicles, vt.description " +
                "FROM vehicleTypes vt " +
                "LEFT JOIN vehicles v ON vt.id = v.typeId " +
                "LEFT JOIN orderDetails od ON v.licensePlate = od.licensePlate " +
                "LEFT JOIN orders o ON od.orderId = o.id " +
                "WHERE vt.id = ? AND o.id IS NULL";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        0, // Bạn có thể thay đổi số liệu nếu cần
                        rs.getString("brand"),
                        rs.getString("category"),
                        rs.getDouble("rentalPrice"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getInt("totalVehicles")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return product;
    }
// * search
public List<Product> searchUnbookedProductByName(String name) {
    List<Product> productList = new ArrayList<>();
    String query = "SELECT vt.id, vt.name, vt.brand, vt.category, vt.rentalPrice, vt.image, vt.totalVehicles, vt.description " +
            "FROM vehicleTypes vt " +
            "LEFT JOIN vehicles v ON vt.id = v.typeId " +
            "LEFT JOIN orderDetails od ON v.licensePlate = od.licensePlate " +
            "LEFT JOIN orders o ON od.orderId = o.id " +
            "WHERE vt.name LIKE ? AND vt.totalVehicles > ( " +
            "    SELECT COUNT(od.licensePlate) " +
            "    FROM orderDetails od " +
            "    LEFT JOIN orders o ON od.orderId = o.id " +
            "    WHERE o.rentalStartDate <= NOW() AND o.expectedReturnDate >= NOW() " +
            "    AND od.licensePlate = v.licensePlate " +
            ") " +
            "GROUP BY vt.id, vt.name, vt.brand, vt.category, vt.rentalPrice, vt.image, vt.totalVehicles, vt.description";

    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(query);
        ps.setString(1, "%" + name + "%");
        rs = ps.executeQuery();

        while (rs.next()) {
            Product product = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    0, // Giá trị mặc định, sửa nếu cần
                    rs.getString("brand"),
                    rs.getString("category"),
                    rs.getDouble("rentalPrice"),
                    rs.getString("description"),
                    rs.getString("image"),
                    rs.getInt("totalVehicles")
            );
            productList.add(product);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return productList;
}
// * sp moi nhat
public List<Product> getTop8ProductNew() {
    List<Product> productList = new ArrayList<>();
    String query = "SELECT vt.id, vt.name, vt.brand, vt.category, vt.rentalPrice, vt.image, vt.totalVehicles, vt.description " +
            "FROM vehicleTypes vt " +
            "LEFT JOIN vehicles v ON vt.id = v.typeId " +
            "LEFT JOIN orderDetails od ON v.licensePlate = od.licensePlate " +
            "LEFT JOIN orders o ON od.orderId = o.id " +
            "WHERE vt.totalVehicles > ( " +
            "    SELECT COUNT(od.licensePlate) " +
            "    FROM orderDetails od " +
            "    LEFT JOIN orders o ON od.orderId = o.id " +
            "    WHERE o.rentalStartDate <= NOW() AND o.expectedReturnDate >= NOW() " +
            "    AND od.licensePlate = v.licensePlate " +
            ") " +
            "GROUP BY vt.id, vt.name, vt.brand, vt.category, vt.rentalPrice, vt.image, vt.totalVehicles, vt.description " +
            "ORDER BY vt.rentalPrice DESC " +
            "LIMIT 8";

    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()) {
            Product product = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    0, // Giá trị mặc định, sửa nếu cần
                    rs.getString("brand"),
                    rs.getString("category"),
                    rs.getDouble("rentalPrice"),
                    rs.getString("description"),
                    rs.getString("image"),
                    rs.getInt("totalVehicles")
            );
            productList.add(product);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return productList;
}
    public List<Product> getLast8BestSeller() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT vt.id, vt.name, vt.brand, vt.category, vt.rentalPrice, vt.image, vt.totalVehicles, vt.description " +
                "FROM vehicleTypes vt " +
                "LEFT JOIN vehicles v ON vt.id = v.typeId " +
                "LEFT JOIN orderDetails od ON v.licensePlate = od.licensePlate " +
                "LEFT JOIN orders o ON od.orderId = o.id " +
                "WHERE o.id IS NULL " +  // Lọc sản phẩm chưa có đơn đặt
                "ORDER BY vt.id DESC " +  // Sắp xếp theo id giảm dần (sản phẩm mới nhất)
                "LIMIT 8";  // Lấy 8 sản phẩm cuối cùng

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        0,  // Giá trị mặc định, có thể thay đổi nếu cần
                        rs.getString("brand"),
                        rs.getString("category"),
                        rs.getDouble("rentalPrice"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getInt("totalVehicles")
                );
                productList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return productList;
    }




    public static void main(String[] args) {
        ProductDao dao = new ProductDao();
//        int id = 5;
//        Product product = dao.getUnbookedProductById(id);
//        System.out.println(product);
//        System.out.println(dao.countProducts("2025-01-01","2025-01-31",2));
//        dao.countProducts()
//        for (Product product : lis) {
//            System.out.println(product);
        dao.countProducts("2025-01-01","2025-01-31",2);
        System.out.println(dao.countProducts("2025-01-01","2025-01-31",3));
//        }
    }
}
