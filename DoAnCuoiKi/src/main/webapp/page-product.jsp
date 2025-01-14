<%@ page import="vn.edu.hcmuaf.fit.doancuoiki.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.doancuoiki.model.ShoppingCart" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12/22/2024
  Time: 12:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12/19/2024
  Time: 8:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
    if(shoppingCart==null){
        shoppingCart = new ShoppingCart();
    }

%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Sản Phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="csssp.css" rel="stylesheet">
    <script>
        function sortProducts() {
            const priceFilter = document.getElementById('price-filter').value;
            const productList = document.querySelector('.products');
            const products = Array.from(productList.children);

            if (priceFilter === 'asc') {
                products.sort((a, b) => parseFloat(a.getAttribute('data-price')) - parseFloat(b.getAttribute('data-price')));
            } else if (priceFilter === 'desc') {
                products.sort((a, b) => parseFloat(b.getAttribute('data-price')) - parseFloat(a.getAttribute('data-price')));
            }

            productList.innerHTML = '';
            products.forEach(product => productList.appendChild(product));
        }
        // Chèn header
        fetch('header.jsp')
            .then(response => response.text())
            .then(data => document.getElementById('header').innerHTML = data);

        // Chèn footer
        fetch('footer.jsp')
            .then(response => response.text())
            .then(data => document.getElementById('footer').innerHTML = data);
        function filterBestSellers() {
            window.location.href = 'ProductBestSeller';

        }
        function redirectToNewPage() {
            window.location.href = 'ProductNew';
        }
    </script>
</head>
<body>
<header id="header"></header>
<div class="hom-filter">
    <span class="hom-filter-label">Sắp xếp theo</span>
    <button class="btnloc" onclick="redirectToNewPage()">Mới nhất </button>
    <button class="btnloc" onclick="filterBestSellers()">Bán chạy </button>
    <div class="filter-group">
        <label for="price-filter">Sắp xếp giá:</label>
        <select id="price-filter" onchange="sortProducts()">
            <option value="default">Mặc định</option>
            <option value="asc">Thấp đến cao</option>
            <option value="desc">Cao đến thấp</option>
        </select>
    </div>
</div>
<div id="wrapper">

    <div class="headline">

        <ul class="products">
            <c:forEach var="p" items="${listA}">
                <li data-price="${p.price}">
                    <div class="product-item">
                        <div class="product-top">
                            <a class="add-to-cart" href="ShoppingCartCL?action=post&id=${p.id}" >+</a>
                            <a href="" class="product-thumb">
                                <img src="${p.img}" alt="">
                            </a>
                            <!--Mua ngay-->
                            <a href="product?pid=${p.id}" class="buy-now">Đặt ngay</a>

                        </div>
                        <div class="product-info">
                            <a href="" class="product-cat"> ${p.type}</a>
                            <a href="" class="product-name"> ${p.name}</a>
                            <div class="product-price">Giá: <f:formatNumber value="${p.price}"/> VND</div>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>

    <div class="pagination">
        <c:forEach begin="1" end="${endP}" var="i">
            <a href="PagingProduct?index=${i}"   class="${tag ==i?"page active":"page"} ">${i}</a>
            <!-- More pagination links if needed -->
        </c:forEach>
    </div>
</div>
<div id="footer"></div>
</body>
</html>

