package gruop7.gundamshop.controller.client;

import gruop7.gundamshop.domain.OrderDetail;
import gruop7.gundamshop.domain.Product;
import gruop7.gundamshop.domain.ProductReview;
import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.service.OrderDetailService;
import gruop7.gundamshop.service.ProductReviewService;
import gruop7.gundamshop.service.ProductService;
import gruop7.gundamshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

// Import thêm các thư viện cần thiết nếu chưa có
import java.util.Optional;

@Controller
public class ProductReviewController {

    private final ProductService productService;
    private final UserService userService;
    private final ProductReviewService productReviewService;
    private final OrderDetailService orderDetailService;

    public ProductReviewController(ProductService productService, UserService userService,
            ProductReviewService productReviewService, OrderDetailService orderDetailService) {
        this.productService = productService;
        this.userService = userService;
        this.productReviewService = productReviewService;
        this.orderDetailService = orderDetailService;
    }

    // Endpoint GET cho trang đánh giá sản phẩm
    @GetMapping("/customer/product-review/{orderDetailId}")
    public String showReviewPage(@PathVariable long orderDetailId, Model model, HttpSession session) {
        OrderDetail orderDetail = orderDetailService.findById(orderDetailId).orElse(null);
        String email = (String) session.getAttribute("email");
        User user = userService.getUserByEmail(email);

        if (orderDetail == null || user == null) {
            return "error/404";
        }

        // Kiểm tra xem đã có đánh giá cho OrderDetail này hay chưa
        ProductReview existingReview = productReviewService.findByOrderDetail(orderDetail);

        model.addAttribute("product", orderDetail.getProduct());
        model.addAttribute("user", user);
        model.addAttribute("orderDetail", orderDetail); // Đảm bảo rằng orderDetail được thêm vào model
        model.addAttribute("existingReview", existingReview); // Truyền thông tin đánh giá hiện tại (nếu có)
        return "customer/product/review";
    }

    @PostMapping("/customer/submit-review/{orderDetailId}")
    public String submitReview(@PathVariable long orderDetailId,
            @RequestParam("rating") int rating,
            @RequestParam("reviewContent") String reviewContent,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        String email = (String) session.getAttribute("email");
        User user = userService.getUserByEmail(email);
        OrderDetail orderDetail = orderDetailService.findById(orderDetailId).orElse(null);

        if (orderDetail == null) {
            return "error/404"; // Nếu không tìm thấy OrderDetail
        }

        ProductReview review = productReviewService.findByOrderDetail(orderDetail);

        if (review == null) {
            review = new ProductReview();
            review.setOrderDetail(orderDetail);
            review.setUser(user);
            review.setProduct(orderDetail.getProduct());
        }

        review.setRating(rating);
        review.setReviewContent(reviewContent);

        productReviewService.saveProductReview(review);

        redirectAttributes.addFlashAttribute("message", "Cảm ơn bạn đã đánh giá sản phẩm!");

        return "customer/product/review-success";
    }

}
