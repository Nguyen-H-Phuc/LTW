<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang san pham moi </title>
    <link rel="stylesheet" href="csssp.css">
    <script src="../../js/locsanphamgiathapdencao.js"></script>
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
<header id="header"></header>
<!-- boc loc san pham-->
<div class="hom-filter">
    <span class="hom-filter-label">Sắp xếp theo</span>

    <button class="btnloc" >Mới nhất </button>
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
<!-- boc loc san pham-->
<div id="wrapper">

    <div class="headline">

        <ul class="products">
            <c:forEach var="p" items="${listPrice}">
                <li data-best-seller="true" >
                    <div class="product-item">
                        <div class="product-top">
                            <a href="" class="product-thumb">
                                <img src="${p.img}" alt="">
                            </a>
                            <!--Mua ngay-->
                            <a href="product?pid=${p.id}" class="buy-now">Đặt ngay</a>
                        </div>
                        <div class="product-info">
                            <a href="" class="product-cat"> ${p.type}</a>
                            <a href="" class="product-name"> ${p.name}</a>
                            <div class="product-price">Giá: <f:formatNumber value="${p.price}"/> </div>
                        </div>
                    </div>
                </li>
            </c:forEach>

        </ul>
    </div>
</div>
<div id="footer"></div>
</body>
</html>
