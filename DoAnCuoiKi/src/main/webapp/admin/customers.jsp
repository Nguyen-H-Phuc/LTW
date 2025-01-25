<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 1/10/2025
  Time: 3:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "f" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0">
  <title>Trang admin</title>
  <link rel= "stylesheet" href= "https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css" >
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/admin.css">
  <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>

  <style>
    .modal {
        display: none; /* Hidden by default */
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgb(0,0,0); /* Fallback color */
        background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
    }

    .modal-content {
        background-color: #fefefe;
        margin: auto; /* Centered */
        padding: 20px;
        border: 1px solid #888;
        width: 50%; /* Smaller size */
        max-width: 500px; /* Ensures it doesn't get too wide */
    }



    .form-container {
        margin-top: 120px;
        width: 150%;
        max-width: 350px; /* Giảm chiều rộng form */
        background: #fff;
        padding: 10px; /* Giảm padding */
        border-radius: 5px;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
    }

    h3 {
        text-align: center;
        margin-bottom: 10px; /* Giảm khoảng cách tiêu đề */
        color: #333;
        font-size: 1em; /* Giảm kích thước chữ */
    }

    .form-group {
        margin-bottom: 8px; /* Giảm khoảng cách giữa các trường */
    }

    label {
        display: block;
        font-size: 0.8em; /* Giảm kích thước chữ */
        margin-bottom: 3px; /* Giảm khoảng cách giữa nhãn và input */
        color: #555;
    }

    input, select, textarea {
        width: 100%;
        padding: 5px; /* Giảm padding trong input */
        font-size: 0.8em; /* Giảm kích thước chữ */
        border: 1px solid #ccc;
        border-radius: 3px;
        box-sizing: border-box;
    }

    textarea {
        resize: none;
    }

    button.submit-btn {
        width: 100%;
        background-color: #EE4D2D;
        color: white;
        border: none;
        padding: 8px; /* Giảm kích thước nút */
        border-radius: 3px;
        font-size: 0.9em; /* Giảm kích thước chữ nút */
        cursor: pointer;
    }

    button.submit-btn:hover {
        background-color: #EE4D2D;
    }

    @media (max-width: 768px) {
        .form-container {
            padding: 8px; /* Giảm padding cho thiết bị nhỏ */
        }
    }
  </style>
</head>
<body>
<input type="checkbox" id="nav-toggle">

<div class="sidebar">
  <div class="sidebar-brand">
    <h2><span>Rental Motorbike</span></h2>

  </div>

  <div class="sidebar-menu">
    <ul>
      <li>
        <a href="/DoAnCuoiKi/admin?action=dashboard">
          <span class="las la-igloo"></span>
          <span>Dashboard</span></a>
      </li>
      <li>
          <a href="/DoAnCuoiKi/admin?action=managerCustomer"><span class="las la-users"></span>
              <span>Quản lý khách hàng</span></a>
      </li>
      <li>
          <a href="/DoAnCuoiKi/admin?action=managerVehicleType"><span class="las la-motorcycle"></span>
              <span>Quản lý xe máy</span></a>
      </li>
      <li>
        <a href="/DoAnCuoiKi/admin?action=managerOrder"><span class="las la-shopping-bag"></span>
          <span>Quản lý đơn hàng</span></a>
      </li>
      <li>
        <a href="qltintuc.jsp"><span class="las la-newspaper"></span>
          <span>Quản lý tin tức</span></a>
      </li>
      <li>
        <a href="feedback.jsp"><span class="las la-receipt"></span>
          <span>Phản hồi khách hàng</span></a>
      </li>
      <li>
        <a href="promotion.jsp"><span class="las la-ticket-alt"></span>
          <span>Quản lý khuyến mãi</span></a>
      </li>
      <li>
        <a href="stats_motors.jsp"><span class="las la-circle"></span>
          <span>Thống kê xe máy</span></a>
      </li>
      <li>
        <a href="stats_income.jsp"><span class="las la-clipboard-list"></span>
          <span>Thống kê doanh thu</span></a>
      </li>
      <li>
        <a href="setting.jsp"><span class="las la-cog"></span>
          <span>Cài đặt</span></a>
      </li>
    </ul>
  </div>
</div>

<div class="main-content">
  <header>
    <h2>
      <label for="nav-toggle" >
        <span class="las la-bars"></span>
      </label>
      Khách hàng
    </h2>

    <div class="search-wrapper">
      <span class="las la-search"></span>
      <input type="search" placeholder="Tìm kiếm tại đây"/>
    </div>

    <div class="user-wrapper">
      <img src="../assets/img/home_img/user.png" height="40" width="40"/>
      <div>
        <h4>Admin</h4>
        <small>NNP</small>
      </div>
    </div>
  </header>
  <main>

    <div class="recent-grid">
      <div class="projects">
        <div class="card">
          <div class="card-header">
            <h3>Khách hàng</h3>
            <button onclick="openConfig()">Thêm khách hàng</button>
            <button class="card-header-btn"> Xóa khách hàng</button>

          </div>

          <div class="card-body">
            <table id="customerTable" width="100%">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Họ tên</th>
                  <th>Ngày sinh</th>
                  <th>Email</th>
                  <th>Số điện thoại</th>
                  <th>Địa chỉ</th>
                  <th>Vai trò</th>
                  <th>Trạng thái</th>
                </tr>
              </thead>
              <tbody>
                <c:if test="${not empty users}">
                  <c:forEach var="u" items="${users}">
                    <tr>
                      <td>${u.id}</td>
                      <td>${u.userInfo.fullName}</td>
                      <td>${u.userInfo.birthday}</td>
                      <td>${u.email}</td>
                      <td>${u.userInfo.phoneNumber}</td>
                      <td>${u.userInfo.address}</td>
                      <td>
                        <form action="admin" method="get">
                          <input type="hidden" name="action" value="changeRoleUser" />
                          <input type="hidden" name="userId" value="${u.id}" />
                          <select name="roleId" onchange="this.form.submit()">
                            <option value="1" ${u.roleId == 1 ? 'selected' : ''}>Admin</option>
                            <option value="2" ${u.roleId == 2 ? 'selected' : ''}>Người dùng thường</option>
                          </select>
                        </form>
                      </td>
                      <td>
                        <form action="admin" method="get">
                          <input type="hidden" name="action" value="changeStatusUser" />
                          <input type="hidden" name="userId" value="${u.id}" />
                          <select name="status" onchange="this.form.submit()">
                            <option value="1" ${u.active ? 'selected' : ''}>Đang hoạt động</option>
                            <option value="0" ${!u.active ? 'selected' : ''}>Bị khoá</option>
                          </select>
                        </form>
                      </td>
                    </tr>
                  </c:forEach>
                </c:if>
                <c:if test="${empty users}">
                  <tr>
                    <td colspan="8">Không có khách hàng nào</td>
                  </tr>
                </c:if>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
     <!-- form them khach hang -->
    <div id="configModal" class="modal">
      <div class="modal-content">
        <div class="form-container">
            <span class="close-btn" onclick="closeConfig()">&times;</span>
            <h3>Thêm khách hang</h3>
          <form id="addCustomerForm" class="auth-form__group" method="post" action="admin?action=addUser">
                <div class="auth-form__form">
                    <div class="auth-form__group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" class="auth-form__input" placeholder="Email của bạn" required>
                    </div>
                    <div class="auth-form__group">
                        <label for="name">Họ và tên của bạn</label>
                        <input type="text" id="name" name="name" class="auth-form__input" placeholder="Họ và tên của bạn" required>
                    </div>
                    <div class="auth-form__group">
                        <label for="birthday">Sinh nhật</label>
                        <input type="date" id="birthday" name="birthday" class="auth-form__input" required>
                    </div>
                    <div class="auth-form__group">
                        <label for="address">Địa chỉ</label>
                        <input type="text" id="address" name="address" class="auth-form__input" placeholder="Địa chỉ của bạn" required>
                    </div>
                    <div class="auth-form__group">
                        <label for="password">Mật khẩu</label>
                        <input type="password" id="password" name="password" class="auth-form__input" placeholder="Mật khẩu của bạn" required>
                    </div>
                    <div class="auth-form__group">
                        <label for="roleId">Vai trò</label>
                        <select name="roleId">
                            <option value="1">Admin</option>
                            <option value="2">Người dùng thường</option>
                        </select>
                    </div>
                    <div class="auth-form__group">
                        <label for="status">Trạng thái</label>
                        <select name="status">
                            <option value="1">Đang hoạt động</option>
                            <option value="0">Bị khoá</option>
                        </select>
                    </div>
                </div>

                <div class="auth-form__controls">
                    <button type="submit" class="btn btn--primary">Thêm khách hàng</button>
                </div>
          </form>
        </div>
      </div>
    </div>
  </main>

  <script>
    function openConfig() {
      document.getElementById('configModal').style.display = 'flex';
    }

    function closeConfig() {
      document.getElementById('configModal').style.display = 'none';
    }

      $(document).ready(function() {
        $('#customerTable').DataTable({
          paging: true,        // Bật phân trang
          searching: true,     // Bật tìm kiếm
          ordering: true,      // Bật sắp xếp
          info: true,          // Hiển thị thông tin số bản ghi
          language: {
            url: "//cdn.datatables.net/plug-ins/1.13.6/i18n/vi.json" // Hiển thị tiếng Việt
          }
        });
      });

     function openConfig() {
        document.getElementById("configModal").style.display = "block";
    }

    function closeConfig() {
        document.getElementById("configModal").style.display = "none";
    }

  </script>
</div>

</body>
</html>
