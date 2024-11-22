/*loc san pham tuwf cao den thap tu hyhap den cao*/
function sortProducts() {
    const sortType = document.getElementById("price-filter").value;
    const productList = document.querySelector(".products");
    const products = Array.from(productList.children);

    // Hàm lấy giá từ chuỗi "Giá: xx.xxx VND"
    function getPrice(product) {
        const priceText = product.querySelector(".product-price").textContent;
        return parseInt(priceText.replace(/[^0-9]/g, "")); // Loại bỏ ký tự không phải số và chuyển thành số nguyên
    }

    // Sắp xếp dựa trên giá
    products.sort((a, b) => {
        // Lấy giá sản phẩm
        const priceA = parseInt(a.querySelector(".product-price").textContent.replace(/\D/g, ''));
        const priceB = parseInt(b.querySelector(".product-price").textContent.replace(/\D/g, ''));

        // Sắp xếp theo giá
        return sortType === "asc" ? priceA - priceB : priceB - priceA;
    });

    // Gỡ các sản phẩm cũ và thêm các sản phẩm đã sắp xếp
    productList.innerHTML = "";
    products.forEach(product => productList.appendChild(product));
}
/*End loc san pham tu cao den thap, tu thap den cao*/

/*loc san pham ban chay*/
function filterBestSellers() {
    const productList = document.querySelector(".products");
    const products = Array.from(productList.children);

    // Hiển thị các sản phẩm có data-best-seller = true
    products.forEach(product => {
        if (product.getAttribute("data-best-seller") === "true") {
            product.style.display = "block"; // Hiển thị
        } else {
            product.style.display = "none"; // Ẩn
        }
    });
}
/*End loc san pham ban chay*/

/* san pham moi*/



/* End san pham moi*/
