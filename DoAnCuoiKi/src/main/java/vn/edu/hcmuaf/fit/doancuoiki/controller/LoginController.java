package vn.edu.hcmuaf.fit.doancuoiki.controller;import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doancuoiki.dao.UserDao;
import vn.edu.hcmuaf.fit.doancuoiki.model.User;
import vn.edu.hcmuaf.fit.doancuoiki.util.Encrypt;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginController", value = "/Login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String url = "login.jsp";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDao userDao = new UserDao();

        try {
            if(userDao.isActive(email)!=1) {
                request.setAttribute("error", "Tài khoản bị khoá");
                forwardToPage(request, response, url);
                return;
            }

            String passwordEncrypt = Encrypt.encrypt(password);
            User user = userDao.getUser(email, passwordEncrypt);

            if(user !=null) {
                url = "index.jsp";
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect(url);
            } else {
                request.setAttribute("error", "Sai mật khẩu hoặc tên đăng nhập");
                forwardToPage(request, response, url);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

}