<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Gruop 7 - Dự án gundamshop" />
                <meta name="author" content="Gruop 7" />
                <title>Category Detail</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
                  <!-- Bootstrap CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
            <!-- Bootstrap Icon -->
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
            <!-- Custom CSS -->
            <link rel="stylesheet" href="/css/ewstyle.css">
                <style>
                    body {
                        background-color: #f8f9fa;
                    }

                    .card {
                        border: none;
                        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                    }

                    .profile-photo {
                        border: 2px dashed #007bff;
                        height: 200px;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        font-size: 24px;
                        color: #007bff;
                        margin-bottom: 20px;
                    }
                </style>
            </head>

            <body class="">
                <div class="container-fluid d-flex p-0">


                    <jsp:include page="../layout/navbar.jsp" />
                <div id="layoutSidenav">
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Chi Tiết Category</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Trang Chu</a></li>
                                    <li class="breadcrumb-item"><a href="/admin/category">Category</a></li>
                                    <li class="breadcrumb-item active">Xem Chi Tiết</li>
                                </ol>

                                <div class="row justify-content-center">
                                    <div class="row">
                                        <div class="card mb-4">


                                            <div class="row">
                                                <div class="card p-4">
                                                    <h3>Ảnh</h3>
                                                    <img class="card-img-top"
                                                        src="/images/category/${newCategory.image}"
                                                        alt="${newCategory.name}'s image" />

                                                </div>
                                            </div>
                                            <div class="card-header">
                                                Thông Tin Category
                                            </div>
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item">ID: ${newCategory.id}</li>
                                                <li class="list-group-item">Tên Của Category: ${newCategory.name}</li>

                                            </ul>
                                            <a href="/admin/category"
                                                class="btn btn-success mt-3 justify-content-center col-4">Trở Về</a>
                                        </div>

                                    </div>


                                </div>
                            </div>
                        </main>

                        <jsp:include page="../layout/footer.jsp" />
                    </div>
                </div>

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/scripts.js"></script>
                </div>
            </body>

            </html>