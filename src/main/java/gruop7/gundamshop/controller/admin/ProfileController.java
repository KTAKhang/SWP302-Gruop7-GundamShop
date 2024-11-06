package gruop7.gundamshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.service.UploadService;
import gruop7.gundamshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ProfileController {
    // DI: dependency injection;
    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public ProfileController(UserService userService, UploadService uploadService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin/profile/{id}")
    public String getDashboard(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("username");
        User currentUser = this.userService.getUserByEmail(email);

        // Ensure customer is not null before adding to model
        if (currentUser == null) {
            // Handle the case where the user is not found
            return "redirect:/admin/customer"; // Redirect or show an error page
        }
        // Ensure customer is not null before adding to model
        if (!currentUser.isStatus()) {
            // Handle the case where the user is not found
            return "redirect:/admin/customer"; // Redirect or show an error page
        }
        model.addAttribute("newUser", currentUser);

        return "admin/profile/show";
    }

    @PostMapping("/admin/profile/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") @Valid User user,
            BindingResult newProductBindingResult,
            @RequestParam("imagesFile") MultipartFile file) {
        // // validate
        // if (newProductBindingResult.hasErrors()) {
        // return "admin/customer/update";
        // }
        User currentUser = this.userService.getUserById(user.getId());
        if (currentUser != null) {
            // update new image
            if (!file.isEmpty()) {
                String img = this.uploadService.handleSaveUploadFile(file, "avatar");
                currentUser.setAvatar(img);
            }
            currentUser.setAddress(user.getAddress());
            currentUser.setFullName(user.getFullName());
            currentUser.setPhone(user.getPhone());

            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

}
