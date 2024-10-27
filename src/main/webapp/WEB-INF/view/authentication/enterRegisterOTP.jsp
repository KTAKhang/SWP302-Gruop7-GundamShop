<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html>

            <head>
                <!-- Sử dụng HTTPS cho Bootstrap và jQuery -->
                <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet"
                    id="bootstrap-css">
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
                <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>

                <link rel="stylesheet"
                    href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

                <style type="text/css">
                    .form-gap {
                        padding-top: 70px;
                    }
                </style>
            </head>

            <body>
                <div class="form-gap"></div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-4 col-md-offset-4">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="text-center">
                                        <form:form method="post" action="/authentication/enterRegisterOTP"
                                            modelAttribute="newOtpForm">
                                            <div class="form-group">
                                                <form:input path="otp" placeholder="Enter OTP" cssClass="form-control"
                                                    required="required" />
                                            </div>
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                            <button type="submit" class="btn btn-primary">Submit OTP</button>
                                        </form:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </body>

            </html>