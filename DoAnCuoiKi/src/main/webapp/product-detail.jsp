<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12/22/2024
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Wave RSX</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <link rel="stylesheet" href="./assets/css/base.css">
    <link rel="stylesheet" href="assets/css/product-detail.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">

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
</head>

<body>
<div class="product-detail">
    <header id="header"></header>
    <div class="container">
        <button onclick="topFunction()" id="myBtn" title="Go to top"><i class="fa-solid fa-arrow-up"></i></button>
        <div class="information-content">
            <div class="product-image">
                <img src="${p.img}" alt="Wave RSX">
            </div>
            <form class="rental-information" action="/submit-rental" method="post">
                <div class="info">
                    <h1 class="name-moto" style="text-align: center">${p.name}</h1>
                        <h3 id="price-per-day">Giá thuê: <f:formatNumber value="${p.price}" />đ/ngày</h3>
                        <h3 id="manufacturer">Nhà sản xuất: ${p.brand}</h3>
                        <h3 id="type">Loại xe: Xe số</h3>
                        <p class="note">* Giá thuê chưa bao gồm: Xăng phục vụ suốt chuyến đi, Bảo hiểm hành khách, Thuế VAT, Phụ thu dịp Lễ Tết.</p>
                </div>
                <div class="fill-in-info">
                    <div class="fill-in-item">
                        <label for="delivery-location">Địa điểm giao xe</label>
                        <input type="text" id="delivery-location" name="delivery-location" required>
                        <label for="delivery-time">Thời gian nhận xe</label>
                        <input type="date" id="delivery-time" name="delivery-time" required>
                        <label for="return-time">Thời gian trả xe</label>
                        <input type="date" id="return-time" name="return-time" required>
                    </div>
                    <div class="button">
                        <button type="submit">Đặt xe</button>
                        <button type="button" onclick="alert('Bạn đã thêm vào giỏ hàng thành công.')">Thêm vào giỏ hàng</button>
                    </div>
                </div>
            </form>
        </div>


        <div class="products-detailed-information">
            <h1 class="product-title">Mô tả / Đánh giá chi tiết</h1>
            <div class="product-description">



                <p>${p.description}</p>

            </div>
        </div>
    </div>
</div>
<%--<div class="cac-san-pham-khac">--%>
<%--    <h1>Các sản phẩm tương tự</h1>--%>
<%--<c:forEach var="relatedProduct" items="${relatedProducts}">--%>
<%--    <div id="wrapper">--%>

<%--        <div class="headline">--%>

<%--            <ul class="products">--%>
<%--                <li>--%>
<%--                    <div class="product-item">--%>
<%--                        <div class="product-top">--%>
<%--                            <a href="" class="product-thumb">--%>
<%--                                <img src="${relatedProduct.img}" alt="">--%>
<%--                            </a>--%>
<%--                            <!--Mua ngay-->--%>
<%--                            <a href="/product?pid=${relatedProduct.id}" class="buy-now">Đặt ngay</a>--%>
<%--                        </div>--%>
<%--                        <div class="product-info">--%>
<%--                            <a href="" class="product-cat"> ${relatedProduct.type}</a>--%>
<%--                            <a href="" class="product-name"> ${relatedProduct.name}</a>--%>
<%--                            <div class="product-price"><f:formatNumber value="${relatedProduct.price}"/></div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </li>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--    </div >--%>
<%--</c:forEach>--%>
<%--</div>--%>
    <div id="footer"></div>

</div>
</body>
</html>

