//package vn.edu.hcmuaf.fit.doancuoiki.controller;
//
//import vn.edu.hcmuaf.fit.doancuoiki.model.CartItem;
//import vn.edu.hcmuaf.fit.doancuoiki.model.Product;
//import vn.edu.hcmuaf.fit.doancuoiki.model.ShoppingCart;
//import vn.edu.hcmuaf.fit.doancuoiki.service.IProductService;
//import vn.edu.hcmuaf.fit.doancuoiki.service.ProductService;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;
//import java.io.IOException;
//
//@WebServlet(name = "ShopingCartCL", value = "/ShopingCartCL")
//public class ShopingCartCL extends HttpServlet {
//    IProductService productService = new ProductService();
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ShoppingCart shoppingCart;
//        HttpSession session = request.getSession();
//        shoppingCart = (ShoppingCart) session.getAttribute("cart");
//        if(shoppingCart==null){
//            shoppingCart = new ShoppingCart();
//        }
//        session.setAttribute("cart",shoppingCart);
//
//        doPost(request,response);
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
//        String action = request.getParameter("action");
//        switch (action){
//            case "get":
//                request.getRequestDispatcher("/cart.jsp").forward(request,response);
//                break;
//            case "delete":
//                Delete(request,response);
//                break;
//            case "put":
//                Put(request,response);
//
//                break;
//            case "post":
//                int id = Integer.parseInt(request.getParameter("id"));
//                Product product = productService.findById(id);
//                CartItem  cartItem = new CartItem(product,1);
//                shoppingCart.add(cartItem);
//                session.setAttribute("cart",shoppingCart);
//                response.sendRedirect("ProductController");
//                System.out.println("---------------------------------------------------");
//                break;
//            default:
//        }
//
//    }
//
//
//    protected void Put(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
//        int id = Integer.parseInt(req.getParameter("id"));
//        int quanlity  =  Integer.parseInt(req.getParameter("quanlity"));
//        String e ="";
//        if(quanlity>0){
//            shoppingCart.update(id,quanlity);
//        }
//        else{
//            e="So luong phai lon hon 0";
//        }
//        req.setAttribute("error",e);
//        session.setAttribute("cart",shoppingCart);
//        req.getRequestDispatcher("ShopingCartCL?action=get").forward(req,resp);
//    }
//
//
//    protected void Delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        HttpSession session = req.getSession();
//        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
//        int id = Integer.parseInt(req.getParameter("id"));
//        shoppingCart.remove(id);
//        session.setAttribute("cart",shoppingCart);
//        resp.sendRedirect("cart.jsp");
//
//    }
//}