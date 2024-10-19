<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    /*--------------------------------Footer---------------------------------------*/
.footer {
    margin-top: 20px; /* Add 20px space on top */
    margin-bottom: 20px; /* Add 20px space at the bottom */
    background-color:black; /* Màu nền của footer */
    color: white;
    padding: 20px 0;
}

.footer-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
    width: 90%;
    margin: 0 auto;
}

.footer-container h4 {
    margin-bottom: 15px;
}

.footer-container ul {
    list-style-type: none;
    padding: 0;
    margin-bottom: 20px;
}

.footer-container li {
    margin-bottom: 10px;
}

.footer-social-icons {
    display: flex;
    gap: 20px;
    justify-content: center;
    margin-bottom: 20px;
}

.footer-social-icons img {
    width: 30px;
    height: 30px;
}

.footer-bottom {
    background-color:  #223665; /* Màu của phần dưới */
    padding: 5px 5px;
    text-align: center;
    color: white;
}
    </style>
    <footer class="footer">
        <div class="footer-container">
            <h4>Shop Mô Hình Gundam Online</h4>
            <ul>
                <li>Trụ sở: Xuân Khánh, quận Ninh Kiều, thành phố Cần Thơ</li>
                <li>Điện thoại: 024.6266.1225</li>
                <li>Email: skynguyenlee@gmail.com</li>
                <li>Website: <a href="#" style="color: white;">5BGunDam.com.vn</a></li>
                <li>Mã số doanh nghiệp: 0104735865</li>
                <li>Cấp ngày: 09/06/2024</li>
            </ul>
            <div class="footer-social-icons">
                <img src="<%= request.getContextPath() %>/image/FB.jpg" alt="Facebook">
                <img src="<%= request.getContextPath() %>/image/ITR.jpg" alt="Instagram">
                <img src="<%= request.getContextPath() %>/image/YTB.jpg" alt="YouTube">
            </div>
        </div>
        <div class="footer-bottom">
            <p>Copyright © 2019 5BGunDam.com.vn. Thiết kế Website - SEO 5 Chàng Trai FPT</p>
        </div>
    </footer>