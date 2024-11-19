<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8" />
                <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
                <link rel="stylesheet" href="/css/style.css" />
                <title>Trang Đăng Nhập</title>
            </head>

            <body>
                <div class="container" id="container">
                    <div>

                    </div>
                    <div class="form-container sign-in">


                        <form method="post" action="/login">

                            <h1>Đăng Nhập</h1>
                            <c:if test="${param.error != null}">
                                <div class="my-2" style="color: red;">Email hoặc Password không hợp lệ</div>
                            </c:if>
                            <c:if test="${param.logout != null}">
                                <div class="my-2" style="color: green;">Logout thành công</div>
                            </c:if>

                            <c:if test="${param.resetsuccess != null}">
                                <div class="my-2" style="color: green;">Đổi mật khẩu thành công</div>
                            </c:if>

                            <c:if test="${param.locked != null}">
                                <div class="d-flex justify-content-center my-2">
                                    <div style="color: orange; text-align: center;">
                                        Tài khoản của bạn đã bị xóa vì vi phạm quy tắc. Vui long liên hệ để biết thêm
                                    </div>
                                </div>

                            </c:if>

                            <c:if test="${param.registersuccess != null}">
                                <div class="my-2" style="color: green;">Đăng kí thành công</div>
                            </c:if>


                            <input type="email" placeholder="Email Address" name="username" />
                            <input type="password" placeholder="Password" name="password" />

                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <a href="/forgotpassword">Quên Mật Khẩu?</a>

                            <button>Đăng Nhập</button>

                        </form>


                    </div>
                    <div class="toggle-container">
                        <div class="toggle">
                            <div class="toggle-panel toggle-right">
                                <h1>Chào bạn!</h1>
                                <p>
                                    Đăng ký với thông tin cá nhân của bạn để sử dụng tất cả các tính năng của trang web
                                </p>
                                <a href="/register"><button class="hidden" id="register">Đăng ký</button></a>
                                <a href="/"><button class="hidden" id="register">Quay lại</button></a>

                            </div>
                        </div>
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/script.js"></script>
            </body>

            </html>