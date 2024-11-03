package gruop7.gundamshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gruop7.gundamshop.domain.ProductReview;

@Repository
public interface FeedbackRepository extends JpaRepository<ProductReview, Long> {

}
