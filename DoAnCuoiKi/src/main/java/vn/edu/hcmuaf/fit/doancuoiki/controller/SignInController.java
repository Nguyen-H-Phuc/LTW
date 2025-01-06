package vn.edu.hcmuaf.fit.doancuoiki.controller;import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doancuoiki.dao.UserDao;
import vn.edu.hcmuaf.fit.doancuoiki.model.User;
import vn.edu.hcmuaf.fit.doancuoiki.model.UserInfo;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "SignInController", value = "/SignIn")
public class SignInController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String url = "";
        UserDao userDao = new UserDao();
        String email = request.getParameter("email");
        if (userDao.isEmailExists(email)) {
            url = "signin.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else {
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String birthdayStr = request.getParameter("birthday"); // Nhận chuỗi ngày từ form
            String address = request.getParameter("address");
            String password = request.getParameter("password");

            // Chuyển đổi chuỗi ngày thành LocalDate
            LocalDate birthday = null;
            if (birthdayStr != null && !birthdayStr.isEmpty()) {
                birthday = LocalDate.parse(birthdayStr); // Chuyển chuỗi thành LocalDate
            }

            // Tạo đối tượng UserInfo và User
            UserInfo userInfo = new UserInfo(name, phone, address, birthday);
            User user = new User(email, password, userInfo, true);

            // Thêm người dùng vào cơ sở dữ liệu


            if (userDao.addUser(user)) {
                url = "index.jsp";
            } else {
                url = "signin.jsp";
            }

            // Chuyển hướng tới trang tương ứng
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }
}