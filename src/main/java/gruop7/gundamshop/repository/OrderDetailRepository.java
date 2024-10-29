package gruop7.gundamshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gruop7.gundamshop.domain.OrderDetail;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findByProductId(long productId);

    List<OrderDetail> findByOrderId(long orderId);
}
