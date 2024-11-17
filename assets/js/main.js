// load header
document.addEventListener("DOMContentLoaded", function () {
    fetch("../assets/header_footer/header.html")
        .then(response => {
            if (!response.ok) throw new Error("Không thể tải header.html");
            return response.text();
        })
        .then(data => {
            document.getElementById("header").innerHTML = data;

            // Xử lý lại các thành phần (nếu cần) sau khi nạp header thành công
            console.log("Header đã được tải thành công!");
        })
        .catch(error => console.error("Lỗi khi tải header:", error));
});


// load footer

    // Lấy nội dung từ file header.html và chèn vào div có id="header-placeholder"
    document.addEventListener("DOMContentLoaded", function () {
    fetch("../assets/header_footer/footer.html")
        .then(response => response.text())
        .then(data => {
            document.getElementById("footer").innerHTML = data;
        })
        .catch(error => console.error("Lỗi khi tải header:", error));
});
