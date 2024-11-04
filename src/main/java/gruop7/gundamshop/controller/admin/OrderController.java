package gruop7.gundamshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import gruop7.gundamshop.service.UserService;
import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.domain.Order;
import gruop7.gundamshop.service.OrderService;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/admin/order")
    public String getDashboard(Model model) {
        List<Order> orders = this.orderService.fetchAllOrders();
        model.addAttribute("orders", orders);
        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getOrderDetailPage(Model model, @PathVariable long id) {
        Order order = this.orderService.fetchOrderById(id).get();
        model.addAttribute("order", order);
        model.addAttribute("id", id);
        model.addAttribute("orderDetails", order.getOrderDetails());
        return "admin/order/detail";
    }

    @GetMapping("/admin/order/delete/{id}")
    public String getDeleteOrderPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("newOrder", new Order());
        return "admin/order/delete";
    }

    @PostMapping("/admin/order/delete")
    public String postDeleteOrder(@ModelAttribute("newOrder") Order order) {
        this.orderService.deleteOrderById(order.getId());
        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getUpdateOrderPage(Model model, @PathVariable long id) {
        Optional<Order> currentOrder = this.orderService.fetchOrderById(id);
        model.addAttribute("newOrder", currentOrder.get());
        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String handleUpdateOrder(@ModelAttribute("newOrder") Order order) {
        this.orderService.updateOrder(order);
        return "redirect:/admin/order";
    }

    @GetMapping("/admin/customer/{customerId}/purchase-history")
    public String getPurchaseHistory(@PathVariable long customerId, Model model) {
        User customer = userService.findUserById(customerId); // Gọi phương thức tìm kiếm người dùng
        if (customer != null) {
            List<Order> orders = orderService.fetchOrdersByCustomerId(customerId);
            model.addAttribute("customer", customer);
            model.addAttribute("orders", orders);
        } else {
            model.addAttribute("error", "Customer not found");
        }
        return "admin/customer/purchaseHistory"; // Đảm bảo đường dẫn tới trang JSP chính xác
    }

    @GetMapping("/employee/order")
    public String getDashboarde(Model model) {
        List<Order> orders = this.orderService.fetchAllOrders();
        model.addAttribute("orders", orders);
        return "employee/order/show";
    }

    @GetMapping("/employee/order/{id}")
    public String getOrderDetailPagee(Model model, @PathVariable long id) {
        Order order = this.orderService.fetchOrderById(id).get();
        model.addAttribute("order", order);
        model.addAttribute("id", id);
        model.addAttribute("orderDetails", order.getOrderDetails());
        return "employee/order/detail";
    }

    @GetMapping("/employee/order/delete/{id}")
    public String getDeleteOrderPagee(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("newOrder", new Order());
        return "employee/order/delete";
    }

    @PostMapping("/employee/order/delete")
    public String postDeleteOrdere(@ModelAttribute("newOrder") Order order) {
        this.orderService.deleteOrderById(order.getId());
        return "redirect:/employee/order";
    }

    @GetMapping("/employee/order/update/{id}")
    public String getUpdateOrderPagee(Model model, @PathVariable long id) {
        Optional<Order> currentOrder = this.orderService.fetchOrderById(id);
        model.addAttribute("newOrder", currentOrder.get());
        return "employee/order/update";
    }

    @PostMapping("/employee/order/update")
    public String handleUpdateOrdere(@ModelAttribute("newOrder") Order order) {
        this.orderService.updateOrder(order);
        return "redirect:/employee/order";
    }
}
