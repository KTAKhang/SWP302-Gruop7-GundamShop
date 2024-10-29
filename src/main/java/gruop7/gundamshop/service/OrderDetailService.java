package gruop7.gundamshop.service;

import gruop7.gundamshop.domain.OrderDetail;
import gruop7.gundamshop.domain.ProductReview;
import gruop7.gundamshop.repository.OrderDetailRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    // Đổi tên phương thức getById thành findById
    public Optional<OrderDetail> findById(long id) {
        return orderDetailRepository.findById(id);
    }

    public Optional<OrderDetail> getById(long id) {
        return orderDetailRepository.findById(id);
    }

    public List<OrderDetail> findByProductId(long productId) {
        return orderDetailRepository.findByProductId(productId);
    }

    public List<OrderDetail> findByOrderId(long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    public boolean hasReview(OrderDetail orderDetail) {
        return orderDetail.getProductReview() != null;
    }

    public void updateProductReview(OrderDetail orderDetail, ProductReview review) {
        orderDetail.setProductReview(review);
        orderDetailRepository.save(orderDetail);
    }
}
