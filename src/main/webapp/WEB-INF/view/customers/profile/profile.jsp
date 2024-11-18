<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Manage Customer</title>
                <!-- Bootstrap CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
                <!-- Bootstrap Icons -->
                <link rel="stylesheet"
                    href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
                <!-- Custom CSS -->
                <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
                <!-- Icon Font Stylesheet -->
                <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
                <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                    rel="stylesheet">


                <!-- jQuery -->
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script>
                    $(document).ready(() => {
                        const avatarFile = $("#avatarFile");
                        const orgImage = "${newUser.avatar}";
                        console.log("Original Image: ", orgImage);

                        if (orgImage) {
                            const urlImage = "/images/avatar/" + orgImage;
                            $("#avatarPreview").attr("src", urlImage).show();
                        } else {
                            $("#avatarPreview").hide();
                        }

                        avatarFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#avatarPreview").attr("src", imgURL).show();
                        });
                    });
                </script>

            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layouts/header.jsp" />
                <div class="container my-5 pt-5"> <!-- Thêm class "pt-5" để tạo khoảng cách trên -->
                    <!-- Page Title -->
                    <div class="d-flex justify-content-between align-items-center">
                        <h3>Customer Profile</h3>
                        <a href="/customers/changepass" class="btn btn-primary">Change Password</a>
                    </div>
                    <hr />

                    <!-- Profile Form -->
                    <div class="row">
                        <div class="col-md-6 mx-auto">
                            <form:form method="post" action="/customers/profile/update" modelAttribute="newUser"
                                enctype="multipart/form-data" class="row g-3">
                                <!-- Avatar Preview -->
                                <div class="text-center mb-3">
                                    <img id="avatarPreview" class="img-fluid rounded-circle"
                                        style="max-height: 250px; display: none;" alt="Avatar Preview" />
                                </div>

                                <!-- Hidden ID -->
                                <form:input type="hidden" path="id" />

                                <!-- Email -->
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email</label>
                                    <form:input path="email" class="form-control" disabled="true" />
                                    <form:errors path="email" cssClass="invalid-feedback" />
                                </div>

                                <!-- Phone -->
                                <div class="mb-3">
                                    <label for="phone" class="form-label">Phone</label>
                                    <form:input path="phone" class="form-control" />
                                </div>

                                <!-- Full Name -->
                                <div class="mb-3">
                                    <label for="fullName" class="form-label">Full Name</label>
                                    <form:input path="fullName" class="form-control" />
                                    <form:errors path="fullName" cssClass="invalid-feedback" />
                                </div>

                                <!-- Address -->
                                <div class="mb-3">
                                    <label for="address" class="form-label">Address</label>
                                    <form:input path="address" class="form-control" />
                                </div>

                                <!-- Avatar Upload -->
                                <div class="mb-3">
                                    <label for="avatarFile" class="form-label">Avatar</label>
                                    <input type="file" id="avatarFile" class="form-control" name="imagesFile"
                                        accept=".png, .jpg, .jpeg" />
                                </div>

                                <!-- Submit Button -->
                                <div class="d-grid">
                                    <button type="submit" class="btn btn-primary">Save</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </body>

            </html>