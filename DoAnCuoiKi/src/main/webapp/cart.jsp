<%@ page import="vn.edu.hcmuaf.fit.doancuoiki.model.ShoppingCart" %>
<%@ page import="vn.edu.hcmuaf.fit.doancuoiki.model.CartItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 1/10/2025
  Time: 11:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ hàng</title>
    <link rel="stylesheet" href="assets/css/base.css">
    <link rel="stylesheet" href="cart.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-free-6.6.0-web/fontawesome-free-6.6.0-web/css/all.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
</head>

<body>
<%
    ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
    if(shoppingCart==null){
        response.sendRedirect("ProductController");
    }
    List<CartItem> cartItems = shoppingCart.getCartItemList();
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
    String e = request.getAttribute("error")==null?"":(String) request.getAttribute("error");
%>
<header id="header"></header>
<!-- section 1 -->
<div id="sec1">
    <hr>
    <div class="container border">
        <h1><strong>Giỏ Hàng Của Bạn </strong></h1>

        <!-- Giỏ hàng -->
        <div class="row">

            <!-- col left -->
            <div class="col-left">
                <div class="cart-item">
                    <div class="cart-item__product">

                        <img src="${p.img}" class="cart-img">
                        <p><strong>${p.name}</strong></p>
                        <i class="cart-icon fa-regular fa-pen-to-square fa-xl"></i>
                        <input type="number" class="form-control" value="${p.quantity}" min="1" max="5">

                        <p><strong>${p.price}</strong></p>
                        <i class="cart-icon fa-solid fa-trash fa-xl"></i>

                    </div>
                </div>
            </div>


            <!-- col right -->
            <div class="col-right">
                <form class="rental-information" action="/submit-rental" method="post">
                    <div class="info">
                        <p class="note" style="font-size: 1.4rem">*Giá thuê chưa bao gồm: Xăng phục vụ suốt chuyến đi, Bảo hiểm hành khách, Thuế VAT, Phụ thu dịp Lễ Tết.</p>
                    </div>
                    <div class="fill-in-info">
                        <div class="fill-in-item">
                            <label for="coupon">Mã giảm giá</label>
                            <input type="text" id="coupon" name="coupon">
                            <label for="delivery-location">Địa điểm giao xe</label>
                            <input type="text" id="delivery-location" name="delivery-location" required>
                            <label for="delivery-time">Thời gian nhận xe</label>
                            <input type="date" id="delivery-time" name="delivery-time" required>
                            <label for="return-time">Thời gian trả xe</label>
                            <input type="date" id="return-time" name="return-time" required>
                        </div>
                        <div class="button">
                            <button type="submit" style="width: 100%">Đặt xe</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
<footer id="footer"></footer>
<script>
    // Chèn header
    fetch('header.jsp')
        .then(response => response.text())
        .then(data => document.getElementById('header').innerHTML = data);

    // Chèn footer
    fetch('footer.jsp')
        .then(response => response.text())
        .then(data => document.getElementById('footer').innerHTML = data);
</script>

</body>
</html>

