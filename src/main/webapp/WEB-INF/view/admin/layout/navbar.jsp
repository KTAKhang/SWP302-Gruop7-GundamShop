<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <div class="p-3 d-flex flex-column align-items-center"
            style="width: 250px; min-height: 100vh; background-color: #1D3865;">
            <img src="/images/logo.jpg" alt="Logo" class="logo mb-4"
                style="width: 160px; height: auto; display: block; margin: 0 auto;">
            <ul class="list-unstyled w-100">
                <li><a href="dashboard.html" class="btn btn-light w-100 text-start mb-3"><i
                            class="bi bi-house-fill"></i> Dashboard</a></li>
                <li><a href="/admin/category" class=" btn btn-light w-100 text-start mb-3"><i
                            class="bi bi-person-fill"></i>
                        Manage Category</a></li>
                <li><a href="/admin/product" class="btn btn-light w-100 text-start mb-3"><i
                            class="bi bi-clipboard-fill"></i> Manage Product</a></li>
                <li><a href="/admin/user" class="btn btn-light w-100 text-start mb-3"><i class="bi bi-box-fill"></i>
                        Manage User</a></li>
                <li><a href="/admin/customer" class="btn btn-light w-100 text-start mb-3"><i
                            class="bi bi-tags-fill"></i> Manage Customer</a></li>
                <li><a href="/admin/employee" class="btn btn-light w-100 text-start mb-3"><i
                            class="bi bi-clipboard-fill"></i> Manage Employee</a></li>
                <li><a href="/admin/order" class="btn btn-light w-100 text-start mb-3"><i
                            class="bi bi-clipboard-fill"></i> Manage Order</a></li>
                <li><a href="/admin/profile/${sessionScope.id}" class="btn btn-light w-100 text-start mb-3"><i
                            class="bi bi-calendar-fill"></i>Manage Profile</a></li>

                <li>
                    <form method="post" action="/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <button class="btn btn-danger w-100 text-start"><a><i class="bi bi-box-arrow-right"></i>
                                Log Out</a></button>
                    </form>
                </li>
            </ul>
        </div>