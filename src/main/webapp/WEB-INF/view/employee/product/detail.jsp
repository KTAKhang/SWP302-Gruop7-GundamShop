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
                <title>Product Detail</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
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

            <body class="sb-nav-fixed">
                <div class="d-flex">
                    <!-- Sidebar -->
                    <div class="p-3 d-flex flex-column align-items-center"
                        style="width: 250px; min-height: 100vh; background-color: #1D3865;">
                        <img src="/images/logo.jpg" alt="Logo" class="logo mb-4"
                            style="width: 160px; height: auto; display: block; margin: 0 auto;">
                        <ul class="list-unstyled w-100">
                            <li><a href="/employee/order" class="btn btn-light w-100 text-start mb-3"><i
                                        class="bi bi-house-fill"></i> Manage Order</a></li>
                            <li><a href="/employee/product" class="btn btn-light w-100 text-start mb-3"><i
                                        class="bi bi-calendar-fill"></i> Manage Product</a></li>
                            <form method="post" action="/logout">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <button class="btn btn-danger w-100 text-start"><i class="bi bi-box-arrow-right"></i> Log Out</button>
                            </form>
                        </ul>
                    </div>
                <div id="layoutSidenav">
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Product Detail</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/employee/order">Dashboard</a></li>
                                    <li class="breadcrumb-item"><a href="/employee/product">Products</a></li>
                                    <li class="breadcrumb-item active">View Detail</li>
                                </ol>

                                <div class="row justify-content-center">
                                    <div class="row">
                                        <div class="card mb-4">


                                            <div class="row">
                                                <div class="card p-4">
                                                    <h3>Profile Photo</h3>
                                                    <img class="card-img-top" src="/images/product/${newProduct.image}"
                                                        alt="${newProduct.name}'s image" />

                                                </div>
                                            </div>
                                            <div class="card-header">
                                                Product Information
                                            </div>
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item">ID: ${newProduct.id}</li>
                                                <li class="list-group-item">Name of Product: ${newProduct.name}</li>
                                                <li class="list-group-item">Factory: ${newProduct.factory}</li>
                                                <li class="list-group-item">Category: ${newProduct.category.name}</li>


                                            </ul>
                                            <a href="/admin/newProduct"
                                                class="btn btn-success mt-3 justify-content-center col-4">Back</a>
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
            </body>

            </html>