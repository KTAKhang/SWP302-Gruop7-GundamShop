
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    /*--------------------------------Footer---------------------------------------*/
    .footer {
        background-color: #000; /* Giữ màu đen chủ đạo */
        color: #fff; /* Màu chữ trắng */
        padding: 40px 0;
    }

    .footer-container {
        display: grid;
        grid-template-columns: repeat(3, 1fr); /* Chia 3 cột đều nhau */
        gap: 20px;
        width: 90%;
        margin: 0 auto;
        text-align: center;
    }

    .footer-container h4 {
        grid-column: span 3; /* Tiêu đề chạy ngang cả 3 cột */
        margin-bottom: 20px;
        font-size: 24px;
    }

    .footer-container ul {
        list-style-type: none;
        padding: 0;
        margin: 0;
        font-size: 16px;
    }

    .footer-container li {
        margin-bottom: 8px;
    }

    .footer-social-icons {
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 20px;
    }

    .footer-social-icons img {
        width: 35px;
        height: 35px;
        filter: brightness(0) invert(1); /* Đổi màu icon thành trắng */
    }

    .footer-bottom {
        background-color: #223665; /* Giữ màu gốc */
        padding: 10px 0;
        text-align: center;
        margin-top: 20px;
    }

    .footer-bottom p {
        margin: 0;
        font-size: 14px;
    }

    /* Responsive adjustments */
    @media (max-width: 768px) {
        .footer-container {
            grid-template-columns: 1fr;
        }
    }
</style>

<footer class="footer">
    <div class="footer-container">
        <h4>Shop Mô Hình Gundam Online</h4>
        <ul>
            <li>Trụ sở: Xuân Khánh, quận Ninh Kiều, thành phố Cần Thơ</li>
            <li>Điện thoại: 024.6266.1225</li>
            <li>Email: skynguyenlee@gmail.com</li>
            <li>Website: <a href="#" style="color: white; text-decoration: underline;">5BGunDam.com.vn</a></li>
            <li>Mã số doanh nghiệp: 0104735865</li>
            <li>Cấp ngày: 09/06/2024</li>
        </ul>
        <div class="footer-social-icons">
            <img src="/client/img/logo.jpg" alt="">
            <img src="/client/img/logo.jpg" alt="">
            <img src="/client/img/logo.jpg" alt="">
        </div>
    </div>
    <div class="footer-bottom">
        <p>Copyright © 2019 5BGunDam.com.vn. Thiết kế Website - SEO 5 Chàng Trai FPT</p>
    </div>
</footer>
