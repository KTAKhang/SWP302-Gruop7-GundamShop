package gruop7.gundamshop.service;

import org.springframework.stereotype.Service;
import gruop7.gundamshop.domain.Order;
import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.repository.OrderRepository;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Cập nhật trạng thái đơn hàng từ PENDING sang CONFIRMED
    public void updateOrderStatusToConfirmed(long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null && order.getStatus().equals(Order.STATUS_PENDING)) {
            order.setStatus(Order.STATUS_CONFIRMED);
            orderRepository.save(order);
        }
    }

    // Cập nhật trạng thái đơn hàng từ CONFIRMED sang SHIPPING
    public void updateOrderStatusToShipping(long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null && order.getStatus().equals(Order.STATUS_CONFIRMED)) {
            order.setStatus(Order.STATUS_SHIPPING);
            orderRepository.save(order);
        }
    }

    // Cập nhật trạng thái đơn hàng từ SHIPPING sang COMPLETED
    public void updateOrderStatusToCompleted(long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null && order.getStatus().equals(Order.STATUS_SHIPPING)) {
            order.setStatus(Order.STATUS_COMPLETED);
            orderRepository.save(order);
        }
    }

    // Lấy tất cả đơn hàng của người dùng, không phân biệt trạng thái
    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user); // Lấy tất cả đơn hàng bất kể trạng thái
    }

    // Lấy đơn hàng của người dùng theo trạng thái (nếu cần)
    public List<Order> getOrdersByUserAndStatus(User user, String status) {
        return orderRepository.findByUserAndStatus(user, status); // Lấy đơn hàng theo trạng thái
    }
}
