package gruop7.gundamshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gruop7.gundamshop.domain.Order;
import gruop7.gundamshop.domain.User;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Tìm đơn hàng theo người dùng và trạng thái
    List<Order> findByUserAndStatus(User user, String status);

    // Tìm tất cả đơn hàng theo người dùng
    List<Order> findByUser(User user);

    // Tìm các đơn hàng của người dùng với trạng thái khác "COMPLETE"
    List<Order> findByUserAndStatusNotIn(User user, List<String> statuses);

    List<Order> findByUser_Id(long userId);
}
