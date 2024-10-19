package gruop7.gundamshop.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import gruop7.gundamshop.domain.Product;

import gruop7.gundamshop.domain.dto.ProductCriteriaDTO;
import gruop7.gundamshop.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ItemController {

    private final ProductService productService;

    public ItemController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String getProductPage(Model model, @PathVariable long id) {
        Product pr = this.productService.getProductById(id);
        model.addAttribute("product", pr);
        model.addAttribute("id", id);
        return "customer/product/detail";
    }

    @GetMapping("/products")
    public String getProductPage(Model model,
            ProductCriteriaDTO productCriteriaDTO,
            HttpServletRequest request) {
        int page = 1;
        try {
            if (productCriteriaDTO.getPage().isPresent()) {
                // convert from String to int
                page = Integer.parseInt(productCriteriaDTO.getPage().get());
            } else {
                // page = 1
            }
        } catch (Exception e) {
            // page = 1
            // TODO: handle exception
        }

        Pageable pageable = PageRequest.of(page - 1, 6);
        Page<Product> prs = this.productService.fetchProducts(pageable);
        List<Product> products = prs.getContent();

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", prs.getTotalPages());

        return "customer/product/show";
    }
}
