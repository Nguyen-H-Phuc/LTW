package vn.edu.hcmuaf.fit.doancuoiki.controller;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doancuoiki.dao.ProductDao;
import vn.edu.hcmuaf.fit.doancuoiki.model.Product;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PagingProduct", value = "/PagingProduct")
public class PagingProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sdate = request.getParameter("sdate"); // Ngày bắt đầu
        String edate = request.getParameter("edate"); // Ngày kết thúc
        String minAvailableStr = request.getParameter("minAvailable"); // Số lượng xe tối thiểu
        String indexPageStr = request.getParameter("index"); // Chỉ số trang

        if (sdate == null || edate == null || minAvailableStr == null) {
            sdate = "2023-01-01"; // Mặc định
            edate = "2025-01-01";
            minAvailableStr = "1";
        }

        int minAvailable = Integer.parseInt(minAvailableStr);
        int indexPage = (indexPageStr == null) ? 1 : Integer.parseInt(indexPageStr);

        // Gọi DAO để lấy dữ liệu
        ProductDao dao = new ProductDao();
        List<Product> list = dao.listPro1(sdate, edate, minAvailable, indexPage);

        // Tổng số sản phẩm để tính số trang
        int totalProducts = dao.countProducts(sdate, edate, minAvailable);
        int endPage = totalProducts / 8;
        if (totalProducts % 8 != 0) {
            endPage++;
        }

        // Đưa dữ liệu lên request để hiển thị
        request.setAttribute("listA", list);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", indexPage);

        // Forward đến trang JSP
        request.getRequestDispatcher("page-product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}