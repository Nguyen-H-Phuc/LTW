<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 1/10/2025
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0">
    <title>Trang admin</title>
    <link rel= "stylesheet" href= "https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css" >
    <link rel="stylesheet" href="admin.css">
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
                <a href="admin.html">
                    <span class="las la-igloo"></span>
                    <span>Dashboard</span></a>
            </li>
            <li>
                <a href="customers.html"><span class="las la-users"></span>
                    <span>Quản lý khách hàng</span></a>
            </li>

            <li>
                <a href="motorbikes.html" class="motorbikes-active"><span class="las la-motorcycle"></span>
                    <span>Quản lý xe máy</span></a>
            </li>
            <li>
                <a href="orders.html"><span class="las la-shopping-bag"></span>
                    <span>Quản lý đơn hàng</span></a>
            </li>
            <li>
                <a href="qltintuc.html"><span class="las la-newspaper"></span>
                    <span>Quản lý tin tức</span></a>
            </li>
            <li>
                <a href="feedback.html"><span class="las la-receipt"></span>
                    <span>Phản hồi khách hàng</span></a>
            </li>
            <li>
                <a href="promotion.html"><span class="las la-ticket-alt"></span>
                    <span>Quản lý khuyến mãi</span></a>
            </li>
            <li>
                <a href="stats_motor.html"><span class="las la-circle"></span>
                    <span>Thống kê xe máy</span></a>
            </li>
            <li>
                <a href="stats_income.html"><span class="las la-clipboard-list"></span>
                    <span>Thống kê doanh thu</span></a>
            </li>
            <li>
                <a href="setting.html"><span class="las la-cog"></span>
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
            Xe máy
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
                        <h3>Xe máy</h3>
                        <button onclick="openConfig()"> Thêm xe</button>
                        <button class="card-header-btn"> Xóa xe</button>
                    </div>

                    <div class="card-body">
                        <table width="100%">
                            <thead>
                            <tr>
                                <th>ID Xe</th>
                                <th>Tên xe</th>
                                <th>Loại xe</th>
                                <th>Giá thuê</th>
                                <th>Mô tả</th>
                                <th>Hãng sản xuất</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>XM001</td>
                                <td>Honda Vision</td>
                                <td>Xe tay ga</td>
                                <td>120,000 VND/ngày</td>
                                <td>Dễ lái, tiết kiệm xăng</td>
                                <td>Honda</td>
                            </tr>
                            <tr>
                                <td>XM002</td>
                                <td>Yamaha Exciter</td>
                                <td>Xe số</td>
                                <td>150,000 VND/ngày</td>
                                <td>Phong cách thể thao</td>
                                <td>Yamaha</td>
                            </tr>
                            <tr>
                                <td>XM003</td>
                                <td>Honda Vision</td>
                                <td>Xe tay ga</td>
                                <td>120,000 VND/ngày</td>
                                <td>Dễ lái, tiết kiệm xăng</td>
                                <td>Honda</td>
                            </tr>
                            <tr>
                                <td>XM004</td>
                                <td>Yamaha Exciter</td>
                                <td>Xe số</td>
                                <td>150,000 VND/ngày</td>
                                <td>Phong cách thể thao</td>
                                <td>Yamaha</td>
                            </tr>
                            <tr>
                                <td>XM005</td>
                                <td>Honda Vision</td>
                                <td>Xe tay ga</td>
                                <td>120,000 VND/ngày</td>
                                <td>Dễ lái, tiết kiệm xăng</td>
                                <td>Honda</td>
                            </tr>
                            <tr>
                                <td>XM006</td>
                                <td>Yamaha Exciter</td>
                                <td>Xe số</td>
                                <td>150,000 VND/ngày</td>
                                <td>Phong cách thể thao</td>
                                <td>Yamaha</td>
                            </tr>
                            <tr>
                                <td>XM007</td>
                                <td>Honda Vision</td>
                                <td>Xe tay ga</td>
                                <td>120,000 VND/ngày</td>
                                <td>Dễ lái, tiết kiệm xăng</td>
                                <td>Honda</td>
                            </tr>
                            <tr>
                                <td>XM008</td>
                                <td>Yamaha Exciter</td>
                                <td>Xe số</td>
                                <td>150,000 VND/ngày</td>
                                <td>Phong cách thể thao</td>
                                <td>Yamaha</td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div id="configModal" class="modal">
            <div class="modal-content">
                <div class="form-container">
                    <span class="close-btn" onclick="closeConfig()">&times;</span>
                    <h3>Thêm Xe</h3>
                    <form action="#" method="POST" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="idXe">ID Xe</label>
                            <input type="text" id="idXe" name="idXe" placeholder="Nhập ID xe" required>
                        </div>
                        <div class="form-group">
                            <label for="tenXe">Tên Xe</label>
                            <input type="text" id="tenXe" name="tenXe" placeholder="Nhập tên xe" required>
                        </div>
                        <div class="form-group">
                            <label for="loaiXe">Loại Xe</label>
                            <select id="loaiXe" name="loaiXe" required>
                                <option value="">Chọn loại xe</option>
                                <option value="Xe côn">Xe côn</option>
                                <option value="Xe số">Xe số</option>
                                <option value="Xe tay ga">Xe tay ga</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="giaThue">Giá Thuê (VND)</label>
                            <input type="number" id="giaThue" name="giaThue" placeholder="Nhập giá thuê" required>
                        </div>
                        <div class="form-group">
                            <label for="hangXe">Hãng Xe</label>
                            <input type="text" id="hangXe" name="hangXe" placeholder="Nhập hãng xe" required>
                        </div>
                        <div class="form-group">
                            <label for="xuatXu">Xuất Xứ</label>
                            <input type="text" id="xuatXu" name="xuatXu" placeholder="Nhập xuất xứ" required>
                        </div>
                        <div class="form-group">
                            <label for="moTa">Mô Tả</label>
                            <textarea id="moTa" name="moTa" placeholder="Mô tả chi tiết về xe" rows="3"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="hinhAnh">Hình Ảnh</label>
                            <input type="file" id="hinhAnh" name="hinhAnh" accept="image/*" required>
                        </div>
                        <button type="submit" class="submit-btn">Thêm Xe</button>
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

    </script>
</div>
</body>
</html>
