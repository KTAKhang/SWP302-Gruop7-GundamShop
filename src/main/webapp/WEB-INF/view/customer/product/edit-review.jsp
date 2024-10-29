<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa đánh giá sản phẩm</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Chỉnh sửa đánh giá sản phẩm: ${product.name}</h2>
        <p>Giá: ${product.price}</p>
        
        <form action="${pageContext.request.contextPath}/customer/update-review/${review.id}" method="POST">
            <label for="reviewContent">Nội dung đánh giá:</label>
            <textarea id="reviewContent" name="reviewContent">${review.reviewContent}</textarea>
            
            <label for="rating">Đánh giá:</label>
            <input type="number" id="rating" name="rating" value="${review.rating}" min="1" max="5" />
            
            <button type="submit">Cập nhật đánh giá</button>
        </form> 
        
        
    </div>
</body>                                                                                                                     
</html> 
