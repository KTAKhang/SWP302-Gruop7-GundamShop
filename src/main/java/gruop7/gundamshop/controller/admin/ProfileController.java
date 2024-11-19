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

        model.addAttribute("newUser", currentUser);

        return "admin/profile/profile";
    }

    @PostMapping("/admin/profile/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") @Valid User user,
            BindingResult newProductBindingResult,
            @RequestParam("imagesFile") MultipartFile file) {
        // validate
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
        // Redirect to the profile page of the updated user
        return "redirect:/admin/profile/" + user.getId();

    }

    @GetMapping("/employee/profile/{id}")
    public String getDashboardE(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("username");
        User currentUser = this.userService.getUserByEmail(email);

        model.addAttribute("newUser", currentUser);

        return "employee/profile/profile";
    }

    @PostMapping("/employee/profile/update")
    public String postUpdateUserE(Model model, @ModelAttribute("newUser") @Valid User user,
            BindingResult newProductBindingResult,
            @RequestParam("imagesFile") MultipartFile file) {
        // validate
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
        // Redirect to the profile page of the updated user
        return "redirect:/employee/profile/" + user.getId();
    }

    @GetMapping("/customer/profile/{id}")
    public String getDashboardEN(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("username");
        User currentUser = this.userService.getUserByEmail(email);

        model.addAttribute("newUser", currentUser);

        return "customer/profile/profile";
    }

    @PostMapping("/customer/profile/update")
    public String postUpdateUserEN(Model model, @ModelAttribute("newUser") @Valid User user,
            BindingResult newProductBindingResult,
            @RequestParam("imagesFile") MultipartFile file) {
        // validate

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

        // Redirect to the profile page of the updated user
        return "redirect:/customer/profile/" + user.getId();
    }

}