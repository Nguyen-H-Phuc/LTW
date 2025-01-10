<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 1/10/2025
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chi tiết 5</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../csstintuc/csschitiettintuc.css">

    <script>
        // Chèn header
        fetch('header.html')
            .then(response => response.text())
            .then(data => document.getElementById('header').innerHTML = data);

        // Chèn footer
        fetch('footer.html')
            .then(response => response.text())
            .then(data => document.getElementById('footer').innerHTML = data);
    </script>
</head>
<body>
<header id="header"></header>
<div class="slider">
    <div id="carouselExample" class="carousel slide mb-4">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="../../assets/img/anh/tintuc/11.jpg" class="d-block w-100" alt="Slide 1">
                <div class="carousel-caption d-none d-md-block">

                </div>
            </div>
            <div class="carousel-item">
                <img src="../../assets/img/anh/tintuc/12.jpg" class="d-block w-100" alt="Slide 2">
                <div class="carousel-caption d-none d-md-block">
                </div>
            </div>
            <div class="carousel-item">
                <img src="../../assets/img/anh/tintuc/12.jpg" class="d-block w-100" alt="Slide 3">
                <div class="carousel-caption d-none d-md-block">
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</div>
<!-- Kết thúc Slider -->
<div class="container">
    <div class="content">
        <h1 class="title">Chào Mừng Ngày Quốc Khánh 2/9</h1>
        <p class="description">
            <strong>Ngày Quốc khánh 2/9</strong> đánh dấu một bước ngoặt lớn trong lịch sử dân tộc.
        </p>
        <img src="https://chothuexemayhcm.com/upload/news/chao-mung-ngay-quoc-khanh-291725298440_385x205.jpg" alt="KYMCO Like" class="main-image">
        <ul class="features">

            <li>  Hình ảnh lá cờ Tổ quốc tung bay phấp phới trong ngày đặc biệt này luôn khắc sâu trong tâm trí mỗi con dân đất Việt.</li>
            <li><strong>Ngày 2/9 </strong>còn giúp thế hệ trẻ cảm thấy trân trọng hơn nền độc lập tự do đang có. Từ đó, mỗi người sẽ ý thức được trách nhiệm của mình đối với sự nghiệp bảo vệ và phát triển đất nước ngày càng giàu mạnh, phồn vinh.</li>
            <li>  Ngày Quốc khánh Việt Nam là ngày lễ chính thức của Việt Nam, diễn ra vào ngày 2 tháng 9 hằng năm, kỷ niệm ngày Chủ tịch Hồ Chí Minh đọc bản Tuyên ngôn độc lập tại Quảng trường Ba Đình, Hà Nội.</li>
        </ul>
    </div>
    <div class="related-articles">
        <h2>Các bài viết liên quan</h2>
        <div class="article-list">
            <div class="article-item">
                <a href="../../chitiettintuc.html">
                    <img src="../../assets/img/anh/tintuc/1.jpg" alt="Bài viết 1">
                    <h3>KYMCO Like</h3>
                    <p>Xe máy, thông tin, bảng giá</p>
                </a>
            </div>
            <div class="article-item">
                <a href="chitiettintuc4.html">
                    <img src="../../assets/img/anh/tintuc/2.jpg" alt="Bài viết 2">
                    <h3>
                        Max Nhật huyền thoại   </h3>
                    <p>Xe máy, thông tin, bảng giá</p>
                </a>
            </div>
            <div class="article-item">
                <a href="chitiettintuc2.html">
                    <img src="../../assets/img/anh/tintuc/5.jpg" alt="Bài viết 3">
                    <h3>Bảo tàng Chứng tích Chiến tranh - TP HCM</h3>
                    <p>du lịch, tổng hợp, cẩm nang, Sài Gòn</p>
                </a>
            </div>
        </div>
    </div>
</div>
<div id="footer"></div>
</body>
</html>