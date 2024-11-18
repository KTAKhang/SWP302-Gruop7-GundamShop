package gruop7.gundamshop.controller.admin;

import java.util.List;

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

import gruop7.gundamshop.domain.Category;
import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.service.CategoryService;
import gruop7.gundamshop.service.UploadService;
import jakarta.validation.constraints.Size;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Controller
public class CategoryController {

    private final CategoryService categoryService;
    private final UploadService uploadService;

    public CategoryController(CategoryService categoryService, UploadService uploadService) {
        this.categoryService = categoryService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/category")
    public String getCategory(Model model) {
        List<Category> categories = this.categoryService.getCategoryByStatus(true);
        model.addAttribute("categories", categories);
        return "admin/category/show";
    }

    @GetMapping("/admin/category/create") // GET
    public String getCreateCategoryPage(Model model) {
        model.addAttribute("newCategory", new Category());
        return "admin/category/create";
    }

    @PostMapping(value = "/admin/category/create")
    public String createCategoryPage(Model model,
            @ModelAttribute("newCategory") @Valid Category category,
            BindingResult newCategoryBindingResult,
            @RequestParam("imageFile") MultipartFile file) {
        // Kiểm tra lỗi
        if (newCategoryBindingResult.hasErrors()) {
            model.addAttribute("newCategory", category);
            return "admin/category/create";
        }

        // Kiểm tra hình ảnh
        if (file.isEmpty()) {
            newCategoryBindingResult.rejectValue("image", "error.image", "Hình ảnh không được bỏ trống");
            model.addAttribute("newCategory", category);
            return "admin/category/create";
        }

        // Xử lý upload file
        String image = this.uploadService.handleSaveUploadFile(file, "category");
        category.setImage(image);
        category.setStatus(true);

        // Lưu vào database
        this.categoryService.handleSaveCategory(category);

        return "redirect:/admin/category";
    }

    @RequestMapping("/admin/category/update/{id}") // GET
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        // chú ý category sử lý chỗ này

        Category currentCategory = this.categoryService.getCategoryById(id);

        // Ensure customer is not null before adding to model
        if (currentCategory == null) {
            // Handle the case where the user is not found
            return "redirect:/admin/category"; // Redirect or show an error page
        }
        // Ensure customer is not null before adding to model
        if (!currentCategory.isStatus()) {
            // Handle the case where the user is not found
            return "redirect:/admin/category"; // Redirect or show an error page
        }
        model.addAttribute("newCategory", currentCategory);
        return "admin/category/update";
    }

    @PostMapping("/admin/category/update")
    public String postUpdateCategory(Model model,
            @ModelAttribute("newCategory") @Valid Category category,
            BindingResult newCategoryBindingResult,
            @RequestParam("imageFile") MultipartFile file) {
        Category currentCategory = this.categoryService.getCategoryById(category.getId());

        // Kiểm tra lỗi cho name
        if (newCategoryBindingResult.hasErrors()) {
            model.addAttribute("newCategory", category);
            return "admin/category/update";
        }

        if (file.isEmpty() && (currentCategory.getImage() == null || currentCategory.getImage().isEmpty())) {
            newCategoryBindingResult.rejectValue("image", "error.image", "Hình ảnh không được bỏ trống");
            model.addAttribute("newCategory", category);
            return "admin/category/update";
        }

        // Nếu có hình ảnh mới, xử lý lưu hình ảnh
        if (!file.isEmpty()) {
            String img = this.uploadService.handleSaveUploadFile(file, "category");
            currentCategory.setImage(img);
        }

        // Cập nhật thông tin
        currentCategory.setName(category.getName());

        this.categoryService.handleSaveCategory(currentCategory);

        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/delete/{id}")
    public String getDeleteCategoryPage(Model model, @PathVariable long id) {
        Category category = this.categoryService.getCategoryById(id);
        model.addAttribute("id", id);
        model.addAttribute("name", category.getName()); // Thêm name
        model.addAttribute("newCategory", new Category());
        return "admin/category/delete";
    }

    @PostMapping("/admin/category/delete")
    public String postDeleteCategory(Model model, @ModelAttribute("newCategory") Category category) {
        Category currentCategory = this.categoryService.getCategoryById(category.getId());

        if (currentCategory != null) {

            currentCategory.setStatus(false);
            ;

            this.categoryService.handleSaveCategory(currentCategory);
        }
        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/{id}")
    public String getCategoryDetailPage(Model model, @PathVariable long id) {
        Category newCategory = this.categoryService.getCategoryById(id);
        model.addAttribute("newCategory", newCategory);
        model.addAttribute("id", id);
        return "admin/category/detail";
    }
}
