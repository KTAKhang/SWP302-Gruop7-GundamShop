package gruop7.gundamshop.controller;

import java.util.Properties;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Controller
public class ForgotPasswordController {

    private final UserService userService;

    public ForgotPasswordController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/forgotpassword")
    public String getForgotPassword(Model model) {
        model.addAttribute("newUser", new User());
        return "authentication/forgotPassword";
    }

    @PostMapping("/authentication/forgotPassword")
    public String recoverPassword(Model model, @ModelAttribute("newUser") User user, HttpServletRequest request) {
        boolean checkEmail = this.userService.checkEmailExist(user.getEmail());
        if (checkEmail) {
            HttpSession mySession = request.getSession();

            // Generate OTP
            int otpvalue = new Random().nextInt(1255650);

            String email = user.getEmail();

            // Set properties for the mail session
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587"); // TLS port
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true"); // Enable TLS

            // Use an app-specific password generated in Google Account for security
            Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("khangkien.060603@gmail.com", "khangkien123"); // Replace
                    // with
                    // your
                    // app
                    // password
                }
            });

            // Compose the message
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress("khangkien.060603@gmail.com")); // Sender's email
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); // Recipient's email
                message.setSubject("Password Recovery OTP");
                message.setText("Your OTP is: " + otpvalue);

                // Send the message
                Transport.send(message);
                System.out.println("Message sent successfully");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

            // Store OTP and email in session
            mySession.setAttribute("otp", otpvalue);
            mySession.setAttribute("email", email);
            request.setAttribute("message", "OTP is sent to your email ID");
            return "authentication/enterOTP"; // Redirect to OTP entry page
        } else {
            request.setAttribute("message", "Invalid email address!");
            return "authentication/forgotPassword"; // Stay on the same page with an error
        }

    }

    @PostMapping("/validateOtp")
    public String validateOtp(HttpServletRequest request, @RequestParam("otp") int otp, Model model) {
        HttpSession session = request.getSession();
        int generatedOtp = (int) session.getAttribute("otp");

        if (otp == generatedOtp) {
            // OTP is correct, redirect to reset password page
            return "authentication/resetPassword";
        } else {
            // OTP is incorrect, show an error message
            request.setAttribute("message", "Invalid OTP. Please try again.");
            return "authentication/enterOTP";
        }
    }

    @PostMapping("/resetPassword")
    public String resetPassword(HttpServletRequest request, @RequestParam("password") String password,
            @RequestParam("confPassword") String confPassword, Model model) {
        if (password.equals(confPassword)) {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");

            // Update the password in the database
            this.userService.updatePassword(email, password);
            request.setAttribute("message", "Password successfully updated!");

            return "authentication/success"; // Redirect to success page
        } else {
            request.setAttribute("message", "Passwords do not match!");
            return "authentication/resetPassword";
        }
    }
}
