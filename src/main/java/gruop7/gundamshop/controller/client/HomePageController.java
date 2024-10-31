package gruop7.gundamshop.controller.client;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import gruop7.gundamshop.domain.Category;
import gruop7.gundamshop.domain.Order;
import gruop7.gundamshop.domain.Product;
import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.service.CategoryService;
import gruop7.gundamshop.service.OrderService;
import gruop7.gundamshop.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomePageController {
    private final OrderService orderService;
    private final ProductService productService;
    private final CategoryService categoryService;

    public HomePageController(ProductService productService, CategoryService categoryService,
            OrderService orderService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Product> products = this.productService.fetchProducts();
        List<Category> categories = this.categoryService.getCategoryByStatus(true);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "customer/homepage/show";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "authentication/login";
    }

    @GetMapping("/access-deny")
    public String getDeny(Model model) {
        return "authentication/deny";
    }

    @GetMapping("/order-history")
    public String getOrderHistoryPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            long userId = (long) session.getAttribute("id");
            User currentUser = new User();
            currentUser.setId(userId);

            List<Order> orders = orderService.getOrdersByUser(currentUser);
            model.addAttribute("orders", orders);
        }
        return "customer/order/history";
    }

    @GetMapping("/order-tracking")
    public String getOrderTracking(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            long userId = (long) session.getAttribute("id");
            User currentUser = new User();
            currentUser.setId(userId);

            List<Order> orders = orderService.getOrdersByUserAndStatusNot(currentUser, "COMPLETE");
            model.addAttribute("orders", orders);
        }
        return "customer/order/tracking";
    }

    @GetMapping("/customer/order-delete/{id}")
    public String getOrderDelete(Model model, @PathVariable long id) {
        Optional<Order> currentOrder = this.orderService.fetchOrderById(id);
        model.addAttribute("newOrder", currentOrder.get());
        return "customer/order/delete";
    }

    @PostMapping("/customer/order/delete")
    public String postOrderDelete(@ModelAttribute("newOrder") Order order) {
        this.orderService.updateOrder(order);
        return "redirect:/order-tracking";
    }

}
