<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Liên Hệ</title>
    <style>
        /* General styling */
        body {
            font-family: 'Open Sans', Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            height: 100vh; /* Set height to full viewport */
            display: flex;
            justify-content: center;
            align-items: center; /* Center content vertically */
        }

        /* Header adjustment */
        header {
            margin-bottom: 20px;
        }

        /* Form styling */
        form {
            max-width: 600px;
            width: 100%;
            padding: 30px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        /* Title */
        form h2 {
            text-align: center;
            margin-bottom: 30px;
            font-size: 26px;
            color: #333;
        }

        /* Label styling */
        form label {
            font-weight: bold;
            color: #333;
            display: block;
            margin-bottom: 5px;
            font-size: 14px;
        }

        /* Input and textarea styling */
        form input[type="text"],
        form textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 20px;
            font-size: 16px;
            background-color: #f9f9f9;
        }

        /* Textarea specific styling */
        form textarea {
            resize: vertical;
            min-height: 120px;
        }

        /* Button styling */
        form button {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        /* Button hover effect */
        form button:hover {
            background-color: #0056b3;
        }

        /* Error and success messages */
        .alert {
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 5px;
            color: #fff;
            font-size: 16px;
        }

        .alert-danger {
            background-color: #e74c3c;
        }

        .alert-success {
            background-color: #28a745;
        }

        /* Responsiveness */
        @media (max-width: 768px) {
            form {
                padding: 20px;
            }
            form h2 {
                font-size: 22px;
            }
            form label,
            form input,
            form textarea {
                font-size: 14px;
            }
        }
    </style>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
        rel="stylesheet">
    <!-- Icon Font Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
        rel="stylesheet">
    <!-- Libraries Stylesheet -->
    <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
    <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <!-- Customized Bootstrap Stylesheet -->
    <link href="/client/css/bootstrap.min.css" rel="stylesheet">
    <!-- Template Stylesheet -->
    <link href="/client/css/style.css" rel="stylesheet">
    <meta name="_csrf" content="${_csrf.token}" />
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/jquery-toast-plugin/1.3.2/jquery.toast.min.css"
        rel="stylesheet">
</head>

<body>
    <!-- Include Header -->
    <jsp:include page="../layout/header.jsp" />

    <!-- Contact Form -->
    <form:form method="POST" action="/contact/contact-success" modelAttribute="contact" class="row">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <h2 class="col-12">Gửi Liên Hệ</h2>

    <!-- Display error if any validation fails -->
    <!-- Phone Number Field -->
    <div class="col-12">
        <form:label for="phoneNumber" path="phoneNumber">Số Điện Thoại:</form:label>
        <form:input id="phoneNumber" path="phoneNumber" class="form-control" required="true" />
    </div>

    <!-- Hidden UserId Field -->
    <input type="hidden" id="userId" name="userId" value="${sessionScope.id}" />

    <!-- Subject Name Field -->
    <div class="col-12">
        <form:label for="subjectName" path="subjectName">Chủ Đề:</form:label>
        <form:input id="subjectName" path="subjectName" class="form-control" />
    </div>

    <!-- Note Field -->
    <div class="col-12">
        <form:label for="note" path="note">Ghi Chú:</form:label>
        <form:textarea id="note" path="note" class="form-control"></form:textarea>
    </div>

    <div class="col-12">
        <button type="submit" class="btn btn-primary">Gửi Liên Hệ</button>
    </div>

</form:form>

</body>

</html>
