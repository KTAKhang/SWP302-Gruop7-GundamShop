package gruop7.gundamshop.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {
    @GetMapping("/search")
    public String getDashboard() {
        return "customer/search/show";
    }
}
