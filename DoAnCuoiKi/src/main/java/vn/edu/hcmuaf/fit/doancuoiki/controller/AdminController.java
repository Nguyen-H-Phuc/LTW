package vn.edu.hcmuaf.fit.doancuoiki.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.doancuoiki.dao.UserDao;
import vn.edu.hcmuaf.fit.doancuoiki.dao.VehicleTypeDao;
import vn.edu.hcmuaf.fit.doancuoiki.model.User;
import vn.edu.hcmuaf.fit.doancuoiki.model.VehicleType;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminController", value = "/admin")
public class AdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "managerCustomer":
                managerCustomer(request, response);
                break;
            case "changeStatusUser":
                changeStatusUser(request, response);
                break;
            case "changeRoleUser":
                changeRoleUser(request, response);
                break;
            case "managerVehicleType":
                managerVehicleType(request, response);
                break;
            case "updateVehicleType":
                updateVehicleType(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "updateVehicleType":
                updateVehicleType(request, response);
                break;
        }
    }

    private void managerCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDao();
        List<User> users = dao.getUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("admin/customers.jsp").forward(request, response);
    }

    private void changeStatusUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDao();
        int id = Integer.parseInt(request.getParameter("userId"));
        int status = Integer.parseInt(request.getParameter("status")); // "true" hoặc "false"
        dao.changeActive(id, status);
        managerCustomer(request, response);
    }

    private void changeRoleUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDao();
        int id = Integer.parseInt(request.getParameter("userId"));
        int role = Integer.parseInt(request.getParameter("roleId"));
        dao.changeRole(id, role);
        managerCustomer(request, response);
    }

    private void managerVehicleType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VehicleTypeDao dao = new VehicleTypeDao();
        List<VehicleType> vehicleTypeList = dao.getAllVehicleType();
        request.setAttribute("vehicleTypeList", vehicleTypeList);
        request.getRequestDispatcher("admin/motorbikes.jsp").forward(request, response);
    }

    private void updateVehicleType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String category = request.getParameter("category");
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        int totalVehicles = Integer.parseInt(request.getParameter("totalVehicles"));
        int available = Integer.parseInt(request.getParameter("available"));

        VehicleTypeDao dao = new VehicleTypeDao();
        dao.updateVehicleType(id, name, brand, category, totalPrice, description, image, totalVehicles, available);
        managerVehicleType(request, response);
    }
}