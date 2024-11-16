
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
   /*--------------------------------Footer---------------------------------------*/
.footer {
    background: linear-gradient(135deg, #000, #223665); /* Thêm hiệu ứng gradient */
    color: #fff;
    padding: 40px 0;
    font-family: Arial, sans-serif;
}


.footer-container {
    
    display: flex;
    grid-template-columns: repeat(3, 1fr); /* Chia 3 cột đều nhau */
    gap: 20px;
    width: 90%;
    margin: 0 auto;
    text-align: center;
    align-items: center;
    flex-direction: column;
    transition: transform 0.3s ease-in-out;
}

.footer-container h4 {
    grid-column: span 3;
    margin-bottom: 20px;
    font-size: 24px;
    text-transform: uppercase;
    letter-spacing: 2px;
    color: #FFD700; /* Màu vàng nổi bật */
}


.footer-container ul {
    list-style-type: none;
    padding: 0;
    margin: 0;
    font-size: 16px;
    text-align: center; /* Căn giữa văn bản */
    margin: 0 auto; /* Căn giữa danh sách */
}

.footer-container li {
    margin-bottom: 8px;
    position: relative;
}

.footer-container li::before {
    content: '•'; /* Thêm icon bullet đẹp hơn */
    color: #FFD700;
    font-size: 1.2em;
    padding-right: 8px;
}

.footer-container a {
    color: #FFD700;
    text-decoration: none;
    transition: color 0.3s;
}

.footer-container a:hover {
    color: #FF4500; /* Đổi màu khi hover */
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
    filter: brightness(0) invert(1);
    transition: transform 0.3s ease, filter 0.3s ease;
}

.footer-social-icons img:hover {
    transform: scale(1.2); /* Phóng to icon khi hover */
    filter: brightness(1) invert(0); /* Hiệu ứng chuyển màu */
}

.footer-bottom {
    background-color: #1a2545; /* Màu tối hơn để phân biệt */
    padding: 10px 0;
    text-align: center;
    margin-top: 20px;
    font-size: 14px;
    color: #ddd;
}

.footer-bottom p {
    margin: 0;
    font-size: 14px;
}

.footer-bottom p span {
    color: #FFD700;
    font-weight: bold;
}

/* Thêm hiệu ứng di chuột nhẹ cho toàn bộ footer */
.footer:hover .footer-container {
    transform: translateY(-5px);
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
            <li>FACEBOOK: <a href="https://www.facebook.com/profile.php?id=100030750404219&mibextid=ZbWKwL" style="color: white; text-decoration: underline;">Facebook.com</a></li>
            <li>Mã số doanh nghiệp: 0104735865</li>
            <li>Cấp ngày: 09/06/2024</li>
        </ul>
    </div>
    <div class="footer-bottom">
        <p>Copyright © 2019 5BGunDam.com.vn. Thiết kế Website - SEO 5 Chàng Trai FPT</p>
    </div>
</footer>
