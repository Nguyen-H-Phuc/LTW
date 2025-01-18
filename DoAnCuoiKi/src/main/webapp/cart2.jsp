<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Giỏ hàng</title>
  <link rel="stylesheet" href="assets/css/base.css">
  <link rel="stylesheet" href="cart.css">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
</head>

<body>


<!-- section 1 -->
<div id="sec1">
  <hr>
  <div class="container border">
    <h1><strong>Giỏ Hàng Của Bạn </strong></h1>

    <!-- Giỏ hàng -->
    <div class="row">

      <!-- col left -->
      <div class="col-lefts">
        <div class="cart-items">
          <div class="cart-item__products">
            <h2>Giỏ hàng</h2>
            <a href="PagingProduct"><button class="btn btn-primary">Tiếp tục mua hàng</button></a>
            <table class="table">
              <thread>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Sản phẩm</th>
                  <th scope="col">Ảnh</th>
                  <th scope="col">Đơn giá</th>
                  <th scope="col">Số lượng</th>
                  <th scope="col">Tổng</th>
                  <th scope="col">Hành động</th>
                </tr>
              </thread>
              <tbody>
              <c:forEach items="${sessionScope.cart.list}" var="cp">
                <tr>
                  <th scope="row"></th>
                  <td>${cp.title}</td>
                  <td><img src="${cp.img}" style=""></td>
                  <td>${cp.price}VND</td>
                  <td>
                    <input type="number" class="form-control" value="${cp.quantity}" min="1" style="">
                  </td>
                  <td>${cp.price * cp.quantity}VND</td>
                  <td>
                    <a href="del-cart?pid=${cp.id}"><button class="btn btn-danger btn-sm">xóa</button></a>
                    <a href="update-cart?pid=${cp.id}"><button class="btn btn-danger btn-sm">Cập nhật</button></a>
                  </td>
                </tr>
              </c:forEach>
              </tbody>
            </table>


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


</body>
</html>

