package gruop7.gundamshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    @GetMapping("/")
    public String getHomePage(Model model) {

        return "customer/homepage";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "authentication/login";
    }

    @GetMapping("/access-deny")
    public String getDeny(Model model) {
        return "authentication/deny";
    }
}
