package vn.edu.hcmuaf.fit.doancuoiki.dao;

import vn.edu.hcmuaf.fit.doancuoiki.db.DBContext;
import vn.edu.hcmuaf.fit.doancuoiki.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs    = null;
// lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<Product>();
        String sqery = "select * from products";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sqery);
            rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9)
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
//    đếm có bao nhiêu sản phẩm
    public int getTatolProduct(){
        String query = "select count(*) from products";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()) {
                return rs.getInt(1);
            }
        }catch (Exception e) {}
        return 0;
    }
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

    public static void main(String[] args) {
      ProductDao dao = new ProductDao();
      Product product = dao.getProductById(1);
      System.out.println(product);

        List<Product> relatedProducts = dao.getRelatedProducts(1);
        System.out.println("\nSản phẩm liên quan:");
        for (Product p : relatedProducts) {
            System.out.println(p);
        }
    }
}
