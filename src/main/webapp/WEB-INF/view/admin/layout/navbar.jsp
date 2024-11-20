<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- bi bi-person-fill
    bi bi-box-fill
    bi bi-clipboard-fill
    bi bi-tags-fill -->
        <div class="p-3 d-flex flex-column align-items-center"
            style="width: 250px; min-height: 100vh; background-color: #1D3865;">
            <img src="/images/logo.jpg" alt="Logo" class="logo mb-4"
                style="width: 160px; height: auto; display: block; margin: 0 auto;">
            <ul class="list-unstyled w-100">
                <li><a href="/admin" class="btn btn-light w-100 text-start mb-3"><i class="bi bi-house-fill"></i>
                    Trang Chủ</a></li>
                <li><a href="/admin/category" class=" btn btn-light w-100 text-start mb-3"><i
                            class="bi bi-tags-fill"></i>
                       Quản Lý Category</a></li>
                <li><a href="/admin/product" class="btn btn-light w-100 text-start mb-3"><i
                            class="bi bi-box-fill"></i> Quản Lý Sản Phẩm</a></li>
                <li><a href="/admin/customer" class="btn btn-light w-100 text-start mb-3"><i
                            class="bi bi-person-fill"></i> Quản Lý Khách Hàng</a></li>
                <li><a href="/admin/employee" class="btn btn-light w-100 text-start mb-3"><i
                            class="bi bi-person-fill"></i> Quản Lý Nhân Viên</a></li>
                <li><a href="/admin/order" class="btn btn-light w-100 text-start mb-3"><i
                            class="bi bi-box-fill"></i> Quản Lý Đặt Hàng</a></li>
                <li><a href="/admin/profile/${sessionScope.id}" class="btn btn-light w-100 text-start mb-3"><i
                            class="bi bi-person-fill"></i>Quản Lý Hồ Sơ</a></li>

                <li><a href="/admin/contact" class="btn btn-light w-100 text-start mb-3"><i
                            class="bi bi-clipboard-fill"></i> Xem Liên Hệ</a></li>


                <li>
                    <form method="post" action="/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <button class="btn btn-danger w-100 text-start"><a><i class="bi bi-box-arrow-right"></i>
                                Đăng Xuất</a></button>
                    </form>
                </li>
            </ul>
        </div>