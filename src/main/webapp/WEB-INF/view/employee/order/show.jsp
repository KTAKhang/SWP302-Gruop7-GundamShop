<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
                <meta name="author" content="Hỏi Dân IT" />
                <title>Manager Order</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
                <!-- Bootstrap Icon -->
                <link rel="stylesheet"
                    href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
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
                            <h1 class="mb-4 mt-4 text-center" style="font-weight: bold;">Manage order</h1>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="/employee">Dashboard</a></li>
                                <li class="breadcrumb-item active">Order</li>
                            </ol>
                            <div class="mt-5">
                                <div class="row">
                                    <div class="col-12 mx-auto">
                                        <div class="d-flex">
                                            <h3>Table Orders</h3>
                                        </div>

                                        <hr />
                                        <table class=" table table-bordered table-hover">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>User</th>
                                                    <th>Total Price</th>
                                                    <th>Date</th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="order" items="${orders}">
                                                    <tr>
                                                        <th>${order.id}</th>
                                                        <td>${order.user.fullName}</td>
                                                        <td>
                                                            <fmt:formatDate value="${order.convertedOrderDate}"
                                                                pattern="dd/MM/yyyy HH:mm:ss" />
                                                        <td>
                                                            <fmt:formatNumber type="number"
                                                                value="${order.totalPrice}" /> đ
                                                        </td>

                                                        <td>${order.status}</td>
                                                        <td>
                                                            <c:if test="${order.status == 'COMPLETE'}">
                                                                <a href="/employee/order/${order.id}"
                                                                    class="btn btn-success">View</a>
                                                            </c:if>
                                                            <c:if test="${order.status == 'CONFIRM'}">
                                                                <a href="/employee/order/${order.id}"
                                                                    class="btn btn-success">View</a>
                                                                <a href="/employee/order/update/${order.id}"
                                                                    class="btn btn-warning  mx-2">Update</a>
                                                            </c:if>
                                                            <c:if test="${order.status == 'SHIPPING'}">
                                                                <a href="/employee/order/${order.id}"
                                                                    class="btn btn-success">View</a>
                                                                <a href="/employee/order/update/${order.id}"
                                                                    class="btn btn-warning  mx-2">Update</a>
                                                            </c:if>
                                                            <c:if test="${order.status == 'PENDING'}">
                                                                <a href="/employee/order/${order.id}"
                                                                    class="btn btn-success">View</a>
                                                                <a href="/employee/order/update/${order.id}"
                                                                    class="btn btn-warning  mx-2">Update</a>
                                                            </c:if>
                                                            <c:if test="${order.status == 'CANCEL'}">
                                                                <a href="/employee/order/${order.id}"
                                                                    class="btn btn-success">View</a>
                                                                <a href="/employee/order/delete/${order.id}"
                                                                    class="btn btn-danger mx-2">Delete</a>
                                                            </c:if>
                                                            <!-- <a href="/admin/order/${order.id}"
                                                                    class="btn btn-success">View</a>
                                                                <a href="/admin/order/update/${order.id}"
                                                                    class="btn btn-warning  mx-2">Update</a>
                                                                <a href="/admin/order/delete/${order.id}"
                                                                    class="btn btn-danger">Delete</a> -->
                                                        </td>
                                                    </tr>

                                                </c:forEach>

                                            </tbody>
                                        </table>
                                    </div>

                                </div>

                            </div>
                        </div>
                        </main>

                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/scripts.js"></script>

            </body>

            </html>