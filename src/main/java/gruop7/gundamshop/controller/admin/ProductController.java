package gruop7.gundamshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import gruop7.gundamshop.domain.Category;
import gruop7.gundamshop.domain.Product;
import gruop7.gundamshop.service.CategoryService;
import gruop7.gundamshop.service.ProductService;
import gruop7.gundamshop.service.UploadService;
import jakarta.validation.Valid;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UploadService uploadService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, UploadService uploadService,
            CategoryService categoryService) {
        this.productService = productService;
        this.uploadService = uploadService;
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/product")
    public String getProduct(Model model) {
        List<Product> products = this.productService.getProductByStatus(true);
        model.addAttribute("products", products);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create") // GET
    public String getCreateUserPage(Model model) {
        model.addAttribute("newProduct", new Product());
        List<Category> categories = this.categoryService.getCategoryByStatus(true);
        model.addAttribute("categories", categories);

        return "admin/product/create";
    }

    @PostMapping(value = "/admin/product/create")
    public String createProductPage(Model model, @ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestParam("productFile") MultipartFile file) {
        // validation
        List<FieldError> errors = newProductBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }
        if (newProductBindingResult.hasErrors()) {
            List<Category> categories = this.categoryService.getCategoryByStatus(true);
            model.addAttribute("categories", categories);
            return "admin/product/create";
        }
        //

        product.setCategory(this.productService.getCategoryByName(product.getCategory().getName()));
        String image = this.uploadService.handleSaveUploadFile(file, "product");
        System.out.println(image);
        product.setImage(image);
        product.setStatus(true);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        // save
        this.productService.handleSaveProduct(product);

        return "redirect:/admin/product";
    }

    @RequestMapping("/admin/product/update/{id}") // GET
    public String getUpdateProductPage(Model model, @PathVariable long id) {
        // chú ý category sử lý chỗ này

        Optional<Product> Product = this.productService.getProductById(id);

        Product currentProduct = Product.get();
        // Ensure customer is not null before adding to model
        if (currentProduct == null) {
            // Handle the case where the user is not found

            return "redirect:/admin/product"; // Redirect or show an error page
        }
        // Ensure customer is not null before adding to model
        if (!currentProduct.isStatus()) {
            // Handle the case where the user is not found
            return "redirect:/admin/product"; // Redirect or show an error page
        }
        model.addAttribute("newProduct", currentProduct);
        List<Category> categories = this.categoryService.getCategoryByStatus(true);
        model.addAttribute("categories", categories);
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String postUpdateProduct(Model model, @ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestParam("productFile") MultipartFile file) {
        // validate
        if (newProductBindingResult.hasErrors()) {

            return "admin/product/update";
        }

        Optional<Product> currentProductOpt = this.productService.getProductById(product.getId());

        if (currentProductOpt.isPresent()) { // Correct handling of Optional
            Product currentProduct = currentProductOpt.get(); // Unwrap the Optional
            if (currentProduct != null) {
                // update new image
                if (!file.isEmpty()) {
                    String img = this.uploadService.handleSaveUploadFile(file, "product");
                    currentProduct.setImage(img);
                }
                currentProduct.setCategory(this.productService.getCategoryByName(product.getCategory().getName()));
                currentProduct.setUpdatedAt(LocalDateTime.now());
                currentProduct.setName(product.getName());
                currentProduct.setPrice(product.getPrice());
                currentProduct.setQuantity(product.getQuantity());
                currentProduct.setDetailDesc(product.getDetailDesc());
                currentProduct.setShortDesc(product.getShortDesc());
                currentProduct.setFactory(product.getFactory());
                currentProduct.setTarget(product.getTarget());

                this.productService.handleSaveProduct(currentProduct);
            }
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProductPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);

        model.addAttribute("Product", new Product());
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String postDeleteProduct(Model model, @ModelAttribute("Product") Product product) {
        Product currentProduct = this.productService.getProductById(product.getId()).get();

        if (currentProduct != null) {

            currentProduct.setStatus(false);

            this.productService.handleSaveProduct(currentProduct);
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/{id}")
    public String getProductDetailPage(Model model, @PathVariable long id) {
        Product newProduct = this.productService.getProductById(id).get();
        model.addAttribute("newProduct", newProduct);
        model.addAttribute("id", id);
        return "admin/product/detail";
    }
}
