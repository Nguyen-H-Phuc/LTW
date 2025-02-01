package vn.edu.hcmuaf.fit.doancuoiki.controller;import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doancuoiki.dao.UserDao;
import vn.edu.hcmuaf.fit.doancuoiki.model.User;
import vn.edu.hcmuaf.fit.doancuoiki.model.UserInfo;
import vn.edu.hcmuaf.fit.doancuoiki.util.Email;
import vn.edu.hcmuaf.fit.doancuoiki.util.Encrypt;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

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

        String url = "signin.jsp"; // Mặc định quay lại trang đăng ký
        UserDao userDao = new UserDao();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String birthdayStr = request.getParameter("birthday");
        String address = request.getParameter("address");

        LocalDate birthday = null;
        if (birthdayStr != null && !birthdayStr.isEmpty()) {
            try {
                birthday = LocalDate.parse(birthdayStr);
            } catch (DateTimeParseException e) {
                request.setAttribute("error", "Ngày sinh không hợp lệ. Vui lòng nhập đúng định dạng.");
                forwardToPage(request, response, url);
                return;
            }
        }

        if (userDao.isEmailExists(email)) {
            request.setAttribute("emailExist", "Email đã tồn tại. Vui lòng sử dụng email khác.");
            setRequestAttributes(request, email, name, password, birthdayStr, address);
            forwardToPage(request, response, url);
            return;
        }

        String passwordEncrypt = Encrypt.encrypt(password);
        UserInfo userInfo = new UserInfo(name, phone, address, birthday);
        User user = new User(email, passwordEncrypt, userInfo, true);
        user.setRoleId(2);
        String message = "";
        if (userDao.addUser(user)) {
            url = "index.jsp"; // Chuyển hướng tới trang chính nếu thành công
        } else {
            request.setAttribute("error", "Đăng ký thất bại. Vui lòng thử lại.");
            setRequestAttributes(request, email, name, password, birthdayStr, address);
        }

        forwardToPage(request, response, url);
    }

    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    private void setRequestAttributes(HttpServletRequest request, String email, String name, String password, String birthday, String address) {
        request.setAttribute("email", email);
        request.setAttribute("name", name);
        request.setAttribute("password", password);
        request.setAttribute("birthday", birthday);
        request.setAttribute("address", address);
    }
}