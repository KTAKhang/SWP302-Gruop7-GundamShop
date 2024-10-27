package gruop7.gundamshop.controller.client;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import gruop7.gundamshop.domain.Category;

import gruop7.gundamshop.domain.Product;

import gruop7.gundamshop.service.CategoryService;
import gruop7.gundamshop.service.ProductService;

@Controller
public class HomePageController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public HomePageController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
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
}
