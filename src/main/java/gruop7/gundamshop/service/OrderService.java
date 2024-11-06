package gruop7.gundamshop.service;

import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import gruop7.gundamshop.domain.Order;
import gruop7.gundamshop.domain.OrderDetail;
import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.repository.OrderDetailRepository;
import gruop7.gundamshop.repository.OrderRepository;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.HashMap;

import java.util.Map;

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

    // Lấy tất cả đơn hàng của người dùng, không phân biệt trạng thái
    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user); // Lấy tất cả đơn hàng bất kể trạng thái
    }

    // Lấy đơn hàng của người dùng theo trạng thái (nếu cần)
    public List<Order> getOrdersByUserAndStatus(User user, String status) {
        return orderRepository.findByUserAndStatus(user, status); // Lấy đơn hàng theo trạng thái
    }

    public Map<Integer, Double> getMonthlyRevenueForYear(int year) {
        List<Order> orders = orderRepository.findByStatus("COMPLETE");
        Map<Integer, Double> monthlyRevenue = new HashMap<>();

        for (Order order : orders) {
            if (order.getOrderDate().getYear() == year) {
                int month = order.getOrderDate().getMonthValue();
                monthlyRevenue.put(month, monthlyRevenue.getOrDefault(month, 0.0) + order.getTotalPrice());
            }
        }
        return monthlyRevenue;
    }

    // Phương thức để lấy danh sách các năm có dữ liệu
    public Set<Integer> getYearsWithData() {
        List<Order> orders = orderRepository.findByStatus("COMPLETE");
        return orders.stream()
                .map(order -> order.getOrderDate().getYear())
                .collect(Collectors.toCollection(TreeSet::new)); // TreeSet để sắp xếp các năm tăng dần
        // Lấy tất cả đơn hàng của người dùng với trạng thái khác "COMPLETE"

    }

    public List<Order> getOrdersByUserAndStatusNot(User user, String excludedStatus) {
        return orderRepository.findByUserAndStatusNot(user, excludedStatus);

    }
}