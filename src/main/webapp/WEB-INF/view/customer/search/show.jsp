<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="utf-8">
                    <title> Sản Phẩm - Gundamshop</title>
                    <meta content="width=device-width, initial-scale=1.0" name="viewport">
                    <meta content="" name="keywords">
                    <meta content="" name="description">

                    <!-- Google Web Fonts -->
                    <link rel="preconnect" href="https://fonts.googleapis.com">
                    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                    <link
                        href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
                        rel="stylesheet">

                    <!-- Icon Font Stylesheet -->
                    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                        rel="stylesheet">

                    <!-- Libraries Stylesheet -->
                    <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
                    <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


                    <!-- Customized Bootstrap Stylesheet -->
                    <link href="/client/css/bootstrap.min.css" rel="stylesheet">

                    <!-- Template Stylesheet -->
                    <link href="/client/css/style.css" rel="stylesheet">
                    <style>
                        .page-link.disabled {
                            color: var(--bs-pagination-disabled-color);
                            pointer-events: none;
                            background-color: var(--bs-pagination-disabled-bg);
                        }

                        .custom-product-blog {
                            display: flex;
                            flex-wrap: wrap;
                            gap: 16px;
                            /* Khoảng cách giữa các sản phẩm */
                            justify-content: center;
                            /* Căn giữa sản phẩm */
                        }

                        .custom-product-blog .product-item {
                            flex: 0 0 calc(33.333% - 16px);
                            /* 3 sản phẩm trên mỗi dòng */
                            max-width: calc(33.333% - 16px);
                            box-sizing: border-box;
                            /* Đảm bảo padding và border không ảnh hưởng đến kích thước */
                            margin-bottom: 24px;
                        }

                        @media (max-width: 768px) {
                            .custom-product-blog .product-item {
                                flex: 0 0 calc(50% - 16px);
                                /* 2 sản phẩm trên mỗi dòng trên màn hình nhỏ hơn */
                                max-width: calc(50% - 16px);
                            }
                        }

                        @media (max-width: 480px) {
                            .custom-product-blog .product-item {
                                flex: 0 0 100%;
                                /* 1 sản phẩm trên mỗi dòng trên màn hình rất nhỏ */
                                max-width: 100%;
                            }
                        }

                        .custom-product-blog .product-item img {
                            width: 100%;
                            height: auto;
                            object-fit: cover;
                            /* Đảm bảo hình ảnh không bị méo */
                            border-radius: 8px;
                            /* Bo góc cho hình ảnh */
                        }

                        .custom-product-blog .product-item .product-desc {
                            overflow: hidden;
                            text-overflow: ellipsis;
                            white-space: nowrap;
                            /* Hiển thị dòng mô tả duy nhất, cắt bớt nếu quá dài */
                            font-size: 13px;
                            color: #555;
                            /* Màu chữ mô tả */
                        }

                        .custom-product-blog .product-item .btn {
                            margin-top: 8px;
                            width: 100%;
                            /* Nút rộng bằng toàn bộ sản phẩm */
                            text-align: center;
                            padding: 10px;
                            border-radius: 16px;
                            /* Bo tròn nút */
                        }
                    </style>
                </head>

                <body>

                    <!-- Spinner Start -->
                    <div id="spinner"
                        class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
                        <div class="spinner-grow text-primary" role="status"></div>
                    </div>
                    <!-- Spinner End -->

                    <jsp:include page="../layout/header.jsp" />

                    <!-- Single Product Start -->
                    <div class="container-fluid py-5 mt-5">
                        <div class="container py-5">
                            <div class="row g-4 mb-5">
                                <div>
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="/">Home</a></li>
                                            <c:choose>
                                                <c:when test="${not empty searchKeyword}">
                                                    <li class="breadcrumb-item active" aria-current="page">
                                                        Kết quả tìm kiếm cho:
                                                        <span class="text-primary">${searchKeyword}</span>
                                                    </li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="breadcrumb-item active" aria-current="page">Tất cả sản
                                                        phẩm</li>
                                                </c:otherwise>
                                            </c:choose>
                                        </ol>
                                    </nav>
                                </div>

                                <div class="row g-4 fruite">
                                    <div class="col-12 col-md-4">
                                        <div class="row g-4">
                                            <div class="col-12" id="factoryFilter">
                                                <div class="mb-2"><b>Hãng sản xuất</b></div>
                                                <c:forEach var="factory" items="${factories}">
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="checkbox"
                                                            id="factory-${factory}" value="${factory}">
                                                        <label class="form-check-label"
                                                            for="factory-${factory}">${factory}</label>
                                                    </div>
                                                </c:forEach>
                                            </div>

                                            <div class="col-12" id="targetFilter">
                                                <div class="mb-2"><b>Mục đích sử dụng</b></div>
                                                <c:forEach var="target" items="${targets}">
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="checkbox"
                                                            id="target-${target}" value="${target}">
                                                        <label class="form-check-label"
                                                            for="target-${target}">${target}</label>
                                                    </div>
                                                </c:forEach>
                                            </div>

                                            <div class="col-12" id="priceFilter">
                                                <div class="mb-2"><b>Mức giá</b></div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="price-2"
                                                        value="duoi-10-trieu">
                                                    <label class="form-check-label" for="price-2">Dưới 10 triệu</label>
                                                </div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="price-3"
                                                        value="10-15-trieu">
                                                    <label class="form-check-label" for="price-3">Từ 10 - 15
                                                        triệu</label>
                                                </div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="price-4"
                                                        value="15-20-trieu">
                                                    <label class="form-check-label" for="price-4">Từ 15 - 20
                                                        triệu</label>
                                                </div>

                                                <div class="form-check form-check-inline">
                                                    <input class="form-check-input" type="checkbox" id="price-5"
                                                        value="tren-20-trieu">
                                                    <label class="form-check-label" for="price-5">Trên 20 triệu</label>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <div class="col-12">
                                                    <div class="mb-2"><b>Sắp xếp</b></div>

                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" id="sort-1"
                                                            value="gia-tang-dan" name="radio-sort">
                                                        <label class="form-check-label" for="sort-1">Giá tăng
                                                            dần</label>
                                                    </div>

                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" id="sort-2"
                                                            value="gia-giam-dan" name="radio-sort">
                                                        <label class="form-check-label" for="sort-2">Giá giảm
                                                            dần</label>
                                                    </div>

                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" id="sort-3"
                                                            value="gia-nothing" name="radio-sort" checked>
                                                        <label class="form-check-label" for="sort-3">Không sắp
                                                            xếp</label>
                                                    </div>
                                                </div>



                                            </div>
                                            <div class="col-12">
                                                <button
                                                    class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4"
                                                    id="btnFilter">
                                                    Lọc Sản Phẩm
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-12 col-md-8 text-center">
                                        <div class="row g-4">
                                            <c:if test="${totalPages ==  0}">
                                                <div>Không tìm thấy sản phẩm</div>
                                            </c:if>
                                            <div class="custom-product-blog">
                                                <c:forEach var="product" items="${products}">
                                                    <c:if test="${product.status == true}">
                                                        <div class="product-item">
                                                            <div class="rounded position-relative">
                                                                <div class="fruite-img">
                                                                    <img src="/images/product/${product.image}"
                                                                        class="img-fluid w-100 rounded-top" alt="">
                                                                </div>
                                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                                                                    style="top: 10px; left: 10px;">
                                                                    ${product.category.name}
                                                                </div>
                                                                <div
                                                                    class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                                    <h4 style="font-size: 15px;">
                                                                        <a
                                                                            href="/product/${product.id}">${product.name}</a>
                                                                    </h4>
                                                                    <p class="product-desc">${product.shortDesc}</p>
                                                                    <p style="font-size: 15px; text-align: center; width: 100%;"
                                                                        class="text-dark fw-bold mb-3">
                                                                        <fmt:formatNumber type="number"
                                                                            value="${product.price}" /> đ
                                                                    </p>
                                                                    <c:choose>
                                                                        <c:when test="${product.quantity > 0}">
                                                                            <form
                                                                                action="/add-products-to-cart/${product.id}"
                                                                                method="post">
                                                                                <input type="hidden"
                                                                                    name="${_csrf.parameterName}"
                                                                                    value="${_csrf.token}" />
                                                                                <button class="btn btn-primary"><i
                                                                                        class="fa fa-shopping-bag me-2"></i>Thêm
                                                                                    vào giỏ hàng</button>
                                                                            </form>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <button class="btn btn-secondary"
                                                                                disabled>Hết hàng</button>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </div>




                                            <c:if test="${totalPages > 0}">
                                                <div class="pagination d-flex justify-content-center mt-5">
                                                    <li class="page-item">
                                                        <a class="${1 eq currentPage ? 'disabled page-link' : 'page-link'}"
                                                            href="/products?page=${currentPage - 1}${queryString}"
                                                            aria-label="Previous">
                                                            <span aria-hidden="true">&laquo;</span>
                                                        </a>
                                                    </li>
                                                    <c:forEach begin="0" end="${totalPages - 1}" varStatus="loop">
                                                        <li class="page-item">
                                                            <a class="${(loop.index + 1) eq currentPage ? 'active page-link' : 'page-link'}"
                                                                href="/products?page=${loop.index + 1}${queryString}">
                                                                ${loop.index + 1}
                                                            </a>
                                                        </li>
                                                    </c:forEach>
                                                    <li class="page-item">
                                                        <a class="${totalPages eq currentPage ? 'disabled page-link' : 'page-link'}"
                                                            href="/products?page=${currentPage + 1}${queryString}"
                                                            aria-label="Next">
                                                            <span aria-hidden="true">&raquo;</span>
                                                        </a>
                                                    </li>

                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Single Product End -->

                    <jsp:include page="../layout/footer.jsp" />


                    <!-- Back to Top -->
                    <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i
                            class="fa fa-arrow-up"></i></a>


                    <!-- JavaScript Libraries -->
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
                    <script src="/client/lib/easing/easing.min.js"></script>
                    <script src="/client/lib/waypoints/waypoints.min.js"></script>
                    <script src="/client/lib/lightbox/js/lightbox.min.js"></script>
                    <script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

                    <!-- Template Javascript -->
                    <script src="/client/js/main.js"></script>
                    <script>
                        document.querySelectorAll('.product-desc').forEach(function (desc) {
                            const words = desc.textContent.trim().split(/\s+/);
                            if (words.length > 4) {
                                desc.textContent = words.slice(0, 4).join(' ') + '...';
                            }
                        });
                    </script>

                </body>

                </html>