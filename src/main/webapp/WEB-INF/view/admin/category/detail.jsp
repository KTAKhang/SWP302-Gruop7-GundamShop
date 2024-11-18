<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Group 7 - Dự án gundamshop" />
    <meta name="author" content="Group 7" />
    <title>Category Detail</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icon -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
    <!-- Custom CSS -->
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>

<body>
    <div class="container-fluid d-flex p-0">
        <!-- Sidebar -->
        <jsp:include page="../layout/navbar.jsp" />

        <!-- Main Content -->
        <div class="main-content p-0">
            <div class="p-4">
                <!-- Breadcrumb -->
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                    <li class="breadcrumb-item"><a href="/admin/category">Categories</a></li>
                    <li class="breadcrumb-item active">View Detail</li>
                </ol>

                <div class="container-fluid px-4">
                    <div class="mt-5">
                        <div class="row">
                            <div class="col-12 mx-auto">
                                <div class="card">
                                    <div class="card-header">
                                        <h3>Category Details</h3>
                                    </div>
                                    <div class="card-body">
                                        <!-- Profile Photo -->
                                        <div class="text-center mb-4">
                                            <img class="rounded" src="/images/category/${newCategory.image}" 
                                                alt="${newCategory.name}'s image" style="max-height: 300px; width: auto;" />
                                        </div>

                                        <!-- Category Information -->
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item"><strong>ID:</strong> ${newCategory.id}</li>
                                            <li class="list-group-item"><strong>Name of Category:</strong> ${newCategory.name}</li>
                                        </ul>
                                    </div>

                                    <!-- Back Button -->
                                    <div class="card-footer text-center">
                                        <a href="/admin/category" class="btn btn-success">Back</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and custom scripts -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="/js/scripts.js"></script>
</body>

</html>
