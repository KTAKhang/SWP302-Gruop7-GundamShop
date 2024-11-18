<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
                <meta name="author" content="Hỏi Dân IT" />
                <title>Delete Product</title>
                <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
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
                                <h1 class="mt-4">Delete Product</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/employee/order">Dashboard</a></li>
                                    <li class="breadcrumb-item"><a href="/employee/product">Products</a></li>
                                    <li class="breadcrumb-item active">Delete</li>
                                </ol>
                                <div class="mt-5">
                                    <div class="row">
                                        <div class="col-12 mx-auto">
                                            <div class="d-flex justify-content-between">
                                                <h3>Delete the product with id = ${id}</h3>
                                            </div>

                                            <hr />
                                            <div class="alert alert-danger">
                                                Are you sure to delete this product ?
                                            </div>
                                            <form:form method="post" action="/admin/product/delete"
                                                modelAttribute="Product">
                                                <div class="mb-3" style="display: none;">
                                                    <label class="form-label">Id:</label>
                                                    <form:input value="${id}" type="text" class="form-control"
                                                        path="id" />
                                                </div>
                                                <button class="btn btn-danger">Confirm</button>
                                            </form:form>

                                        </div>

                                    </div>

                                </div>
                            </div>
                        </main>

                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="js/scripts.js"></script>
            </body>

            </html>