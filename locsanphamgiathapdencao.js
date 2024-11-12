function sortProducts() {
    const filter = document.getElementById("price-filter").value; // Lấy giá trị từ select box
    const productList = document.querySelector(".products"); // Chọn danh sách sản phẩm
    const products = Array.from(productList.children); // Chuyển danh sách sản phẩm thành mảng

    // Hàm lấy giá từ chuỗi "Giá: xx.xxx VND"
    function getPrice(product) {
        const priceText = product.querySelector(".product-price").textContent;
        return parseInt(priceText.replace(/[^0-9]/g, "")); // Loại bỏ ký tự không phải số và chuyển thành số nguyên
    }

    // Sắp xếp dựa trên giá
    products.sort((a, b) => {
        const priceA = getPrice(a);
        const priceB = getPrice(b);
        return filter === "asc" ? priceA - priceB : priceB - priceA;
    });

    // Xóa và thêm lại các sản phẩm đã sắp xếp
    products.forEach(product => productList.appendChild(product));
}