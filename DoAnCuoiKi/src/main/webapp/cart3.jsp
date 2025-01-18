<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 1/18/2025
  Time: 11:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-5">
    <h1 class="text-center mb-4">Giỏ Hàng</h1>
    <form>
        <table class="table table-bordered">
            <thead class="table-dark">
            <tr>
                <th>#</th>
                <th>Sản phẩm</th>
                <th>Giá</th>
                <th>Số lượng</th>
                <th>Thành tiền</th>
                <th>Hành động</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>Sản phẩm A</td>
                <td>100,000đ</td>
                <td>
                    <input type="number" class="form-control" value="1" min="1">
                </td>
                <td>100,000đ</td>
                <td>
                    <button type="button" class="btn btn-danger btn-sm">Xóa</button>
                </td>
            </tr>
            <tr>
                <td>2</td>
                <td>Sản phẩm B</td>
                <td>200,000đ</td>
                <td>
                    <input type="number" class="form-control" value="1" min="1">
                </td>
                <td>200,000đ</td>
                <td>
                    <button type="button" class="btn btn-danger btn-sm">Xóa</button>
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="4" class="text-end"><strong>Tổng cộng:</strong></td>
                <td colspan="2">300,000đ</td>
            </tr>
            </tfoot>
        </table>
        <div class="text-end">
            <button type="submit" class="btn btn-success">Thanh toán</button>
            <button type="button" class="btn btn-secondary">Tiếp tục mua sắm</button>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

