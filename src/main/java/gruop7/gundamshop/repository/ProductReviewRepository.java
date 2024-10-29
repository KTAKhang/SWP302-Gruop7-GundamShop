package gruop7.gundamshop.repository;

import gruop7.gundamshop.domain.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import gruop7.gundamshop.domain.OrderDetail;
import gruop7.gundamshop.domain.Product;
import gruop7.gundamshop.domain.ProductReview;
import gruop7.gundamshop.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import gruop7.gundamshop.domain.ProductReview;
import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.domain.Product;

import java.util.Optional;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

    // Phương thức tìm kiếm đánh giá theo người dùng và sản phẩm
    Optional<ProductReview> findByUserAndProduct(User user, Product product);

    ProductReview findByOrderDetail(OrderDetail orderDetail); // Tìm đánh giá dựa vào OrderDetail

    List<ProductReview> findByProductId(long productId);

}
