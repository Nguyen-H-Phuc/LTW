
/*chuyen trang san pham*/
function changePage(pageNumber) {
    // Ẩn tất cả các trang
    const pages = document.querySelectorAll('.product-page');
    pages.forEach(page => {
        page.classList.remove('active');
        page.style.display = 'none';
    });

    // Hiển thị trang được chọn
    const activePage = document.getElementById(`page-${pageNumber}`);
    if (activePage) {
        activePage.classList.add('active');
        activePage.style.display = 'block';
    }
}
/* END chuyen trang san pham*/

/*chuyen trang san pham moi*/
function redirectToNewPage() {
    window.location.href = "trangsanphammoi.html";
}/* END chuyen trang san pham moi*/
