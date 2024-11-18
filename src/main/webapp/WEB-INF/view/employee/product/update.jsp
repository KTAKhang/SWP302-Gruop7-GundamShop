<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Gruop 7 - Dự án gundamshop" />
                <meta name="author" content="Gruop 7" />
                <title>Update Product</title>
                <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script>
                    $(document).ready(() => {
                        const avatarFile = $("#productFile");
                        const orgImage = "${newProduct.image}";
                        console.log("Original Image: ", orgImage);
                        if (orgImage) {
                            const urlImage = "/images/product/" + orgImage;
                            $("#productPreview").attr("src", urlImage);
                            $("#productPreview").css({ "display": "block" });
                        }

                        avatarFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#productPreview").attr("src", imgURL);
                            $("#productPreview").css({ "display": "block" });
                        });
                    });
                </script>
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
                                <h1 class="mt-4">Dashboard</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/employee/order">Dashboard</a></li>
                                    <li class="breadcrumb-item"><a href="/employee/product">Products</a></li>
                                    <li class="breadcrumb-item active">Update</li>
                                </ol>
                                <div class="mt-5">
                                    <div class="row">
                                        <div class="col-md-6 col-12 mx-auto">
                                            <h3>Update a Product</h3>
                                            <hr />
                                            <form:form method="post" action="/employee/product/update" class="row"
                                                enctype="multipart/form-data" modelAttribute="newProduct">
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                    value="${_csrf.token}" />
                                                <form:input type="hidden" class="form-control" path="id" />
                                                <div class="mb-3 col-12 col-md-6">
                                                    <label class="form-label">Name:</label>
                                                    <form:input type="text" class="form-control" path="name" />
                                                </div>
                                                <div class="mb-3 col-12 col-md-6">
                                                    <label class="form-label">Price:</label>
                                                    <form:input type="number" class="form-control" path="price" />
                                                </div>
                                                <div class="mb-3 col-12">
                                                    <label class="form-label">Detail description:</label>
                                                    <form:textarea class="form-control" path="detailDesc" />
                                                </div>
                                                <div class="mb-3 col-12 col-md-6">
                                                    <label class="form-label">Short description:</label>
                                                    <form:input type="text" class="form-control" path="shortDesc" />
                                                </div>
                                                <div class="mb-3 col-12 col-md-6">
                                                    <label class="form-label">Quantity:</label>
                                                    <form:input type="number" class="form-control" path="quantity" />
                                                </div>

                                                <div class="mb-3 col-12 col-md-6">
                                                    <label class="form-label">Factory:</label>
                                                    <form:select class="form-select" path="factory">
                                                        <form:option value="BANDAINAMCO">BANDAINAMCO
                                                        </form:option>
                                                        <form:option value="MR-HOBBY">MR HOBBY</form:option>
                                                    </form:select>
                                                </div>

                                                <div class="mb-3 col-12 col-md-6">
                                                    <label class="form-label">Category:</label>
                                                    <form:select class="form-select" path="category.name">
                                                        <c:forEach var="category" items="${categories}">
                                                            <form:option value="${category.name}">
                                                                ${category.name}
                                                            </form:option>
                                                        </c:forEach>
                                                    </form:select>
                                                </div>
                                                <div class="mb-3 col-12 col-md-6">
                                                    <label class="form-label">Target:</label>
                                                    <form:select class="form-select" path="target">
                                                        <form:option value="MOI-CHOI">Mới chơi</form:option>
                                                        <form:option value="TRUNG-CAP">Trung cấp</form:option>
                                                        <form:option value="CHI-TIET">Chi tiết</form:option>
                                                        <form:option value="CHUYEN-GIA">Chuyên gia</form:option>
                                                        <form:option value="SUU-TAM">Sưu tầm</form:option>
                                                        <form:option value="LAP-DE">Lắp dễ</form:option>
                                                        <form:option value="LAP-SAN">Lắp sẵn</form:option>
                                                    </form:select>
                                                </div>

                                                <div class="mb-3 col-12 col-md-6">
                                                    <label for="productFile" class="form-label">Image:</label>
                                                    <input class="form-control" type="file" id="productFile"
                                                        accept=".png, .jpg, .jpeg" name="productFile" />
                                                </div>

                                                <div class="col-12 mb-3">
                                                    <img style="max-height: 250px; display: none;" alt="product preview"
                                                        id="productPreview" />
                                                </div>

                                                <div class="col-12 mb-5">
                                                    <button type="submit" class="btn btn-primary">Update</button>
                                                </div>
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