package vn.edu.hcmuaf.fit.doancuoiki.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doancuoiki.dao.ProductDao;
import vn.edu.hcmuaf.fit.doancuoiki.model.Product;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexControl", value = "/IndexControl")
public class IndexControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao dao = new ProductDao();
        List<Product> lis = dao.getLastProduct();
        request.setAttribute("listA", lis);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}