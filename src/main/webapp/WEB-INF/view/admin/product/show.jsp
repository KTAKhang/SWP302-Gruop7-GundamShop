<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Manage Product</title>
            <!-- Bootstrap CSS -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
            <!-- Bootstrap Icon -->
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
            <!-- Custom CSS -->
            <link rel="stylesheet" href="/css/ewstyle.css">
        </head>

        <body>
            <div class="container-fluid d-flex p-0">


                <jsp:include page="../layout/navbar.jsp" />


                <!-- Main Content -->
                <div class="main-content p-0">
                    <jsp:include page="../layout/header.jsp" />

                    <div class="p-4">
                        <h1 class="mb-4 mt-4 text-center" style="font-weight: bold;">Manage Product</h1>
                        <table class="table table-bordered table-hover align-middle text-center">
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                <li class="breadcrumb-item active">Products</li>
                            </ol>
                            <div class="mt-5">
                                <div class="row">
                                    <div class="col-12 mx-auto">
                                        <div class="d-flex justify-content-between">
                                            <h3>Table Product</h3>
                                            <a href="/admin/product/create" class="btn btn-primary">Create a
                                                Product</a>
                                        </div>

                                        <hr />
                                        <table class=" table table-bordered table-hover">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Name</th>
                                                    <th>Price</th>
                                                    <th>Factory</th>
                                                    <th>Category</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="product" items="${products}">

                                                    <tr>
                                                        <th>${product.id}</th>
                                                        <td>${product.name}</td>
                                                        <td>${product.price}</td>
                                                        <td>${product.factory}</td>
                                                        <td>${product.category.name}</td>
                                                        <td>
                                                            <a href="/admin/product/${product.id}"
                                                                class="btn btn-success">View</a>
                                                            <a href="/admin/product/update/${product.id}"
                                                                class="btn btn-warning  mx-2">Update</a>
                                                            <a href="/admin/product/delete/${product.id}"
                                                                class="btn btn-danger">Delete</a>
                                                        </td>
                                                    </tr>

                                                </c:forEach>

                                            </tbody>
                                        </table>
                                    </div>

                                </div>

                            </div>
                        </table>
                    </div>
                </div>
            </div>



            <!-- Bootstrap JS -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

        </body>

        </html>