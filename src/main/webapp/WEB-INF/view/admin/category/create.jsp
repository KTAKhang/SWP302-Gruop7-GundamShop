<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Group 7 - Dự án gundamshop" />
    <meta name="author" content="Group 7" />
    <title>Tạo Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        $(document).ready(() => {
            const avatarFile = $("#imageFile");
            avatarFile.change(function (e) {
                const imgURL = URL.createObjectURL(e.target.files[0]);
                $("#imagePreview").attr("src", imgURL);
                $("#imagePreview").css({ "display": "block" });
            });
        });
    </script>
     <!-- Bootstrap CSS -->
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
     <!-- Bootstrap Icon -->
     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
     <!-- Custom CSS -->
     <link rel="stylesheet" href="/css/ewstyle.css">
</head>

<body class="sb-nav-fixed">
    <div class="container-fluid">
        <!-- Header -->
        <header class="row">
            <div class="col-12">
                
            </div>
        </header>

        <div class="row">
            <!-- Sidebar -->
            <aside class="col-md-2">
                <jsp:include page="../layout/navbar.jsp" />
            </aside>

            <!-- Main Content -->
            <main class="col-md-10">
                <div class="container mt-4">
                    <h1 class="mt-4">Trang Chủ</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item"><a href="/admin">Trang Chủ</a></li>
                        <li class="breadcrumb-item"><a href="/admin/category">Category</a></li>
                        <li class="breadcrumb-item active">Tạo Category</li>
                    </ol>

                    <div class="mt-5">
                        <div class="row">
                            <div class="col-md-6 col-12 mx-auto">
                                <h3>Tạo Một Category</h3>
                                <hr />
                                <form:form method="post" action="/admin/category/create" modelAttribute="newCategory" class="row" enctype="multipart/form-data">
                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">Tên Của Category:</label>
                                        <form:input type="text" class="form-control" path="name" />
                                        <form:errors path="name" cssClass="text-danger" />
                                    </div>

                                    <div class="mb-3 col-12 col-md-6">
                                        <label for="imageFile" class="form-label">Ảnh:</label>
                                        <input class="form-control" type="file" id="imageFile" accept=".png, .jpg, .jpeg" name="imageFile" />
                                        <form:errors path="image" cssClass="text-danger" />
                                    </div>

                                    <div class="col-12 mb-3">
                                        <img style="max-height: 250px; display: none;" alt="image preview" id="imagePreview" />
                                    </div>
                                    <div class="col-12 mb-5">
                                        <button type="submit" class="btn btn-primary">Create</button>
                                        
                                    </div>
                                    
                                </form:form>
                               

                            </div>
                        
                             
                              
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="/js/scripts.js"></script>
</body>

</html>
