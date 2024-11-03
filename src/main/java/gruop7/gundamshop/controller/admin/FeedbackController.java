package gruop7.gundamshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.service.FeedbackService;
import gruop7.gundamshop.service.UploadService;
import gruop7.gundamshop.service.UserService;

@Controller
public class FeedbackController {
    // DI: dependency injection;
    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;
    private final FeedbackService feedbackService;

    public FeedbackController(UserService userService, UploadService uploadService,
            PasswordEncoder passwordEncoder, FeedbackService feedbackService) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
        this.feedbackService = feedbackService;
    }

    @GetMapping("/admin/customer/feedback/{id}")
    public String getFeedback(Model model, @PathVariable long id) {
        User newCustomer = this.userService.getUserById(id);
        model.addAttribute("newCustomer", newCustomer);
        System.out.println(newCustomer);
        model.addAttribute("id", id);
        return "admin/customer/detail";
    }

}
