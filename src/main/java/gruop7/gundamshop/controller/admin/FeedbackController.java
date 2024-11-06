package gruop7.gundamshop.controller.admin;

import gruop7.gundamshop.domain.ProductReview;
import gruop7.gundamshop.service.FeedbackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/customer")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/{userId}/feedback")
    public String getFeedback(@PathVariable Long userId, Model model) {
        List<ProductReview> feedbackList = feedbackService.getFeedbackByUserId(userId);
        model.addAttribute("feedbackList", feedbackList);
        model.addAttribute("userId", userId);

        // Create a placeholder feedback object for the form binding
        model.addAttribute("feedback", new ProductReview());

        return "admin/customer/feedback"; // Adjust this path based on your JSP location
    }

    @PostMapping("/feedback/{feedbackId}")
    public String updateFeedbackVisibility(@PathVariable Long feedbackId, @RequestParam String visible) {
        feedbackService.updateFeedbackVisibility(feedbackId, visible);
        Long userId = feedbackService.getFeedbackById(feedbackId).getUser().getId(); // Lấy userId từ feedback vừa cập
                                                                                     // nhật
        return "redirect:/admin/customer/" + userId + "/feedback"; // Chuyển hướng về trang feedback của user đó
    }
}
