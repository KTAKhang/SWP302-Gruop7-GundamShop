package gruop7.gundamshop.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import gruop7.gundamshop.domain.Order;
import gruop7.gundamshop.domain.OrderDetail;
import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.repository.OrderDetailRepository;
import gruop7.gundamshop.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderService(
            OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
    }

    public List<Order> fetchAllOrders() {
        return this.orderRepository.findAll();
    }

    public Optional<Order> fetchOrderById(long id) {
        return this.orderRepository.findById(id);
    }

    public void deleteOrderById(long id) {
        // delete order detail
        Optional<Order> orderOptional = this.fetchOrderById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            List<OrderDetail> orderDetails = order.getOrderDetails();
            for (OrderDetail orderDetail : orderDetails) {
                this.orderDetailRepository.deleteById(orderDetail.getId());
            }
        }

        this.orderRepository.deleteById(id);
    }

    public void updateOrder(Order order) {
        Optional<Order> orderOptional = this.fetchOrderById(order.getId());
        if (orderOptional.isPresent()) {
            Order currentOrder = orderOptional.get();
            currentOrder.setStatus(order.getStatus());
            this.orderRepository.save(currentOrder);
        }
    }

    public List<Order> fetchOrderByUser(User user) {
        return this.orderRepository.findByUser(user);
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
