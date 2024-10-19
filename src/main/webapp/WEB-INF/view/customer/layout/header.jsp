<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <style>
/*** by me ***/
/* Tạo kiểu cho phần tử chứa thanh tìm kiếm */
.nav-item.search-bar {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 5px;
    position: relative; /* Thêm thuộc tính position để điều chỉnh vị trí */
}

/* Tạo kiểu cho thanh tìm kiếm */
.nav-search {
    width: 200px; /* Thu nhỏ chiều rộng của thanh tìm kiếm */
    padding: 8px; /* Giảm khoảng cách bên trong */
    border: 2px solid #ccc; /* Viền thanh tìm kiếm */
    border-radius: 20px; /* Bo tròn các góc */
    outline: none; /* Loại bỏ đường viền khi focus */
    font-size: 14px; /* Giảm cỡ chữ */
    transition: width 0.4s ease-in-out; /* Hiệu ứng mở rộng thanh tìm kiếm khi focus */
}

/* Thêm hiệu ứng khi focus vào thanh tìm kiếm */
.nav-search:focus {
    width: 300px; /* Mở rộng khi người dùng nhấp vào */
    border-color: #007bff; /* Đổi màu viền khi focus */
}

/* Placeholder text style */
.nav-search::placeholder {
    color: #999;
    font-style: italic;
}

/* Mobile responsive - làm cho thanh tìm kiếm nhỏ hơn trên màn hình nhỏ */
@media screen and (max-width: 768px) {
    .nav-search {
        width: 150px; /* Thu nhỏ thanh tìm kiếm hơn trên mobile */
    }

    .nav-search:focus {
        width: 200px; /* Mở rộng ít hơn khi focus trên mobile */
    }
}



    </style>
        <!-- Navbar start -->
        <div class="container-fluid fixed-top">
            <div class="container px-0">
                <nav class="navbar navbar-light bg-white navbar-expand-xl" >
                
                    <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars text-primary"></span>
                    </button>
                    <div class="collapse navbar-collapse bg-white justify-content-between mx-5" id="navbarCollapse">
                        <div class="navbar-nav">
                            <li class="nav-item logo-item"><a href="#"><img class="nav-logo" style="width: 50px" src="/client/img/logo.jpg" alt=""></a></li>
                            <a href="/" class="nav-item nav-link active">Trang Chủ</a>
                            <li class="nav-item search-bar"><input class="nav-search" type="text" placeholder="bạn cần gì..."></i></li>
                            <a href="/products" class="nav-item nav-link">Sản Phẩm</a>
                            <a href="" class="nav-item nav-link">lịch sử mua hàng</a>
                            <a href="" class="nav-item nav-link">Theo Dõi Vận Chuyển</a>
                            <a href="" class="nav-item nav-link">Liên Hệ</a>
                        </div>
                        <div class="d-flex m-3 me-0">
                            <c:if test="${empty pageContext.request.userPrincipal}">
                                <a href="/login" class="a-login position-relative me-4 my-auto">
                                    Đăng nhập
                                </a>
                            </c:if>
                        </div>
                        <form method="post" action="/logout">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <button class="btn btn-danger w-100 text-start"><a><i class="bi bi-box-arrow-right"></i>
                                    Log Out</a></button>
                        </form>
                    </div>
                </nav>
            </div>
        </div>
        <!-- Navbar End -->