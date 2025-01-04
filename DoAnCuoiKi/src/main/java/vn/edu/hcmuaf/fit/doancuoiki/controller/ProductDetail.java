package vn.edu.hcmuaf.fit.doancuoiki.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doancuoiki.dao.ProductDao;
import vn.edu.hcmuaf.fit.doancuoiki.model.Product;

import java.io.IOException;

@WebServlet(name = "ProductDetail", value = "/product")
public class ProductDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pid= Integer.parseInt(request.getParameter("pid"));

        // Lấy chi tiết sản phẩm từ DB
        ProductDao productDao = new ProductDao();
        Product product = productDao.getProductById(pid);

        // Gửi sản phẩm vào trang JSP
        request.setAttribute("p", product);
        request.getRequestDispatcher("product-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}