package gruop7.gundamshop.controller.admin;

import gruop7.gundamshop.domain.PasswordChangeForm;
import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ChangePassController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public ChangePassController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // Display password change form
    @GetMapping("/admin/changepass")
    public String showChangePassForm(Model model) {
        model.addAttribute("passwordChangeForm", new PasswordChangeForm());
        return "admin/changepass/pass"; // Path to pass.jsp
    }

    // Handle password change submission
    @PostMapping("/admin/changepass")
    public String changePassword(
            @Valid PasswordChangeForm passwordChangeForm,
            BindingResult bindingResult,
            Model model,
            HttpServletRequest request) {

        // Check for input errors
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "All fields are required.");
            return "admin/changepass/pass"; // Return the password change page if there are errors
        }

        // Get email from session
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        // Check if there is no email in the session
        if (email == null || email.isEmpty()) {
            model.addAttribute("errorMessage", "Session has expired or user is not logged in.");
            return "admin/changepass/pass"; // Return the page if email is not found
        }

        // Get the user by email
        User user = userService.getUserByEmail(email);
        if (user == null) {
            model.addAttribute("errorMessage", "User not found.");
            return "admin/changepass/pass";
        }

        // Check if the current password is correct
        if (!passwordEncoder.matches(passwordChangeForm.getCurrentPassword(), user.getPassword())) {
            model.addAttribute("errorMessage", "Current password is incorrect.");
            return "admin/changepass/pass";
        }

        // Check if the new password and confirm password match
        if (!passwordChangeForm.getNewPassword().equals(passwordChangeForm.getConfirmPassword())) {
            model.addAttribute("errorMessage", "New password and confirmation password do not match.");
            return "admin/changepass/pass";
        }

        // Update the user's password
        userService.updatePassword(user.getEmail(), passwordChangeForm.getNewPassword());

        // Add success message after password change
        model.addAttribute("successMessage", "Password has been successfully changed.");

        return "admin/changepass/pass"; // Stay on the same page to display success message
    }

    @GetMapping("/employee/changepass")
    public String showChangePassFormE(Model model) {
        model.addAttribute("passwordChangeForm", new PasswordChangeForm());
        return "employee/changepass/pass"; // Path to pass.jsp
    }

    @PostMapping("/employee/changepass")
    public String changePasswordE(
            @Valid PasswordChangeForm passwordChangeForm,
            BindingResult bindingResult,
            Model model,
            HttpServletRequest request) {

        // Check for input errors
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "All fields are required.");
            return "employee/changepass/pass"; // Return the password change page if there are errors
        }

        // Get email from session
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        // Check if there is no email in the session
        if (email == null || email.isEmpty()) {
            model.addAttribute("errorMessage", "Session has expired or user is not logged in.");
            return "employee/changepass/pass"; // Return the page if email is not found
        }

        // Get the user by email
        User user = userService.getUserByEmail(email);
        if (user == null) {
            model.addAttribute("errorMessage", "User not found.");
            return "employee/changepass/pass";
        }

        // Check if the current password is correct
        if (!passwordEncoder.matches(passwordChangeForm.getCurrentPassword(), user.getPassword())) {
            model.addAttribute("errorMessage", "Current password is incorrect.");
            return "employee/changepass/pass";
        }

        // Check if the new password and confirm password match
        if (!passwordChangeForm.getNewPassword().equals(passwordChangeForm.getConfirmPassword())) {
            model.addAttribute("errorMessage", "New password and confirmation password do not match.");
            return "employee/changepass/pass";
        }

        // Update the user's password
        userService.updatePassword(user.getEmail(), passwordChangeForm.getNewPassword());

        // Add success message after password change
        model.addAttribute("successMessage", "Password has been successfully changed.");

        return "employee/changepass/pass"; // Stay on the same page to display success message
    }

    @GetMapping("/customer/changepass")
    public String showChangePassFormEN(Model model) {
        model.addAttribute("passwordChangeForm", new PasswordChangeForm());
        return "customer/changepass/pass"; // Path to pass.jsp
    }

    // Handle password change submission
    @PostMapping("/customer/changepass")
    public String changePasswordEN(
            @Valid PasswordChangeForm passwordChangeForm,
            BindingResult bindingResult,
            Model model,
            HttpServletRequest request) {

        // Check for input errors
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "All fields are required.");
            return "customer/changepass/pass"; // Return the password change page if there are errors
        }

        // Get email from session
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        // Check if there is no email in the session
        if (email == null || email.isEmpty()) {
            model.addAttribute("errorMessage", "Session has expired or user is not logged in.");
            return "customer/changepass/pass"; // Return the page if email is not found
        }

        // Get the user by email
        User user = userService.getUserByEmail(email);
        if (user == null) {
            model.addAttribute("errorMessage", "User not found.");
            return "customer/changepass/pass";
        }

        // Check if the current password is correct
        if (!passwordEncoder.matches(passwordChangeForm.getCurrentPassword(), user.getPassword())) {
            model.addAttribute("errorMessage", "Current password is incorrect.");
            return "customer/changepass/pass";
        }

        // Check if the new password and confirm password match
        if (!passwordChangeForm.getNewPassword().equals(passwordChangeForm.getConfirmPassword())) {
            model.addAttribute("errorMessage", "New password and confirmation password do not match.");
            return "customer/changepass/pass";
        }

        // Update the user's password
        userService.updatePassword(user.getEmail(), passwordChangeForm.getNewPassword());

        // Add success message after password change
        model.addAttribute("successMessage", "Password has been successfully changed.");

        return "customer/changepass/pass"; // Stay on the same page to display success message
    }

}
