<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Manage Customer</title>
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
                        <h1 class="mb-4 mt-4 text-center" style="font-weight: bold;">Manage Customer</h1>

                        <!-- Thông báo thành công hoặc lỗi -->
                        <div>
                            <c:if test="${not empty message}">
                                <div class="alert alert-success">${message}</div>
                            </c:if>
                            <c:if test="${not empty error}">
                                <div class="alert alert-danger">${error}</div>
                            </c:if>
                        </div>

                        <table class="table table-bordered table-hover align-middle text-center">
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                <li class="breadcrumb-item active">Customers</li>
                            </ol>
                            <div class="mt-5">
                                <div class="row">
                                    <div class="col-12 mx-auto">
                                        <div class="d-flex justify-content-between">
                                            <h3>Table Customer</h3>
                                            <a href="/admin/customer/create" class="btn btn-primary">Create a
                                                Customer</a>
                                        </div>

                                        <hr />
                                        <table class="table table-bordered table-hover">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Email</th>
                                                    <th>Full Name</th>
                                                    <th>Status</th> <!-- Thêm cột trạng thái -->
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="customer" items="${customers}">
                                                    <tr>
                                                        <th>${customer.id}</th>
                                                        <td>${customer.email}</td>
                                                        <td>${customer.fullName}</td>
                                                        <td>
                                                            <span
                                                                class="${customer.status ? 'text-success' : 'text-danger'}">
                                                                ${customer.status ? 'Active' : 'Banned'}
                                                            </span>
                                                        </td>
                                                        <td>
                                                            <a href="/admin/customer/${customer.id}"
                                                                class="btn btn-success">View</a>
                                                            <a href="/admin/customer/update/${customer.id}"
                                                                class="btn btn-warning mx-2">Update</a>
                                                            <a href="/admin/customer/${customer.id}/feedback"
                                                                class="btn btn-info">Feedback</a>
                                                            <a href="/admin/customer/${customer.id}/purchase-history"
                                                                class="btn btn-secondary mx-2">Purchase History</a>
                                                            <form action="/admin/customer/ban/${customer.id}"
                                                                method="post" class="d-inline">
                                                                <input type="hidden" name="_csrf"
                                                                    value="${_csrf.token}" />
                                                                <input type="hidden" name="status"
                                                                    value="${customer.status ? 'false' : 'true'}" />
                                                                <button type="submit"
                                                                    class="btn ${customer.status ? 'btn-danger' : 'btn-success'}">
                                                                    ${customer.status ? 'Ban' : 'Unban'}
                                                                </button>
                                                            </form>
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