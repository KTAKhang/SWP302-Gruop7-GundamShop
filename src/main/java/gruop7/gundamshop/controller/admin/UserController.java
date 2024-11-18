package gruop7.gundamshop.controller.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.service.UploadService;
import gruop7.gundamshop.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.persistence.EntityNotFoundException;

import jakarta.validation.Valid;

@Controller
public class UserController {

    // DI: dependency injection;
    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    // -------------------------------- User ---------------------------------
    @GetMapping("/admin/user")
    public String getDashboard(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/show";
    }

    @GetMapping("/admin/user/create") // GET
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        System.out.println("newUser");
        return "admin/user/create";
    }

    @PostMapping(value = "/admin/user/create")
    public String createUserPage(Model model, @ModelAttribute("newUser") @Valid User user,
            BindingResult newUserBindingResult,
            @RequestParam("hoidanitFile") MultipartFile file) {
        // validation
        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }
        if (newUserBindingResult.hasErrors()) {
            return "admin/user/create";
        }
        //

        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setAvatar(avatar);
        user.setPassword(hashPassword);
        user.setStatus(true);
        user.setRole(this.userService.getRoleByName(user.getRole().getName()));
        // save
        this.userService.handleSaveUser(user);

        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        // User user = new User();
        // user.setId(id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model, @ModelAttribute("newUser") User eric) {
        this.userService.deleteAUser(eric.getId());
        return "redirect:/admin/user";
    }

    // -------------------------------- User ---------------------------------
    @PostMapping("/admin/customer/ban/{userId}")
    public String banOrUnbanCustomer(@PathVariable Long userId, @RequestParam boolean status,
            RedirectAttributes redirectAttributes) {
        try {
            if (!status) {
                userService.banCustomerAccount(userId);
                redirectAttributes.addFlashAttribute("message", "Tài khoản đã bị cấm thành công!");
            } else {
                userService.unbanCustomerAccount(userId);
                redirectAttributes.addFlashAttribute("message", "Tài khoản đã được gỡ cấm thành công!");
            }
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "Tài khoản không tìm thấy!");
        }
        return "redirect:/admin/customer"; // Điều hướng lại trang danh sách khách hàng
    }

    // -------------------------------- Customer ---------------------------------
    @GetMapping("/admin/customer")
    public String getAllCustomer(Model model) {
        List<User> customers = this.userService.getUsersRoleId(3);
        model.addAttribute("customers", customers);
        return "admin/customer/show";
    }

    // -------------------------------- create customer

    @GetMapping("/admin/customer/create") // GET
    public String getCreateCustomerPage(Model model) {
        model.addAttribute("newCustomer", new User());
        System.out.println("newCustomer");
        return "admin/customer/create";
    }

    @PostMapping(value = "admin/customer/create")
    public String createCustomerPage(Model model, @ModelAttribute("newCustomer") @Valid User customer,
            BindingResult newUserBindingResult,
            @RequestParam("imagesFile") MultipartFile file) {
        // validation
        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }
        if (newUserBindingResult.hasErrors()) {
            return "admin/customer/create";
        }
        //

        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(customer.getPassword());
        customer.setAvatar(avatar);
        customer.setPassword(hashPassword);
        customer.setStatus(true);
        customer.setRole(this.userService.getRoleByName(customer.getRole().getName()));
        // save
        this.userService.handleSaveUser(customer);

        return "redirect:/admin/customer";
    }

    // -------------------------------- create customer

    // -------------------------------- delete customer

    @GetMapping("/admin/customer/delete/{id}")
    public String getDeleteCustomerPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        // User user = new User();
        // user.setId(id);
        model.addAttribute("newCustomer", new User());
        return "admin/customer/delete";
    }

    @PostMapping("/admin/customer/delete")
    public String postDeleteCustomer(Model model, @ModelAttribute("newCustomer") User customer) {
        User currentCustomer = this.userService.getUserById(customer.getId());

        if (currentCustomer != null) {

            currentCustomer.setStatus(false);
            ;

            this.userService.handleSaveUser(currentCustomer);
        }
        return "redirect:/admin/customer";
    }

    // -------------------------------- delete customer

    // -------------------------------- update customer
    @RequestMapping("/admin/customer/update/{id}") // GET
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        // chú ý category sử lý chỗ này

        User currentCustomer = this.userService.getUserById(id);

        // Ensure customer is not null before adding to model
        if (currentCustomer == null) {
            // Handle the case where the user is not found
            return "redirect:/admin/customer"; // Redirect or show an error page
        }
        // Ensure customer is not null before adding to model
        if (!currentCustomer.isStatus()) {
            // Handle the case where the user is not found
            return "redirect:/admin/customer"; // Redirect or show an error page
        }
        model.addAttribute("newCustomer", currentCustomer);
        return "admin/customer/update";
    }

    @PostMapping("/admin/customer/update")
    public String postUpdateUser(Model model, @ModelAttribute("newCustomer") @Valid User customer,
            BindingResult newProductBindingResult,
            @RequestParam("imagesFile") MultipartFile file) {
        // // validate
        // if (newProductBindingResult.hasErrors()) {
        // return "admin/customer/update";
        // }
        User currentCustomer = this.userService.getUserById(customer.getId());
        if (currentCustomer != null) {
            // update new image
            if (!file.isEmpty()) {
                String img = this.uploadService.handleSaveUploadFile(file, "avatar");
                currentCustomer.setAvatar(img);
            }
            currentCustomer.setAddress(customer.getAddress());
            currentCustomer.setFullName(customer.getFullName());
            currentCustomer.setPhone(customer.getPhone());

            this.userService.handleSaveUser(currentCustomer);
        }
        return "redirect:/admin/customer";
    }

    // -------------------------------- update customer

    // -------------------------------- customer detail
    @GetMapping("/admin/customer/{id}")
    public String getCustomerDetailPage(Model model, @PathVariable long id) {
        User newCustomer = this.userService.getUserById(id);
        model.addAttribute("newCustomer", newCustomer);
        System.out.println(newCustomer);
        model.addAttribute("id", id);
        return "admin/customer/detail";
    }
    // -------------------------------- customer detail

    // -------------------------------- Customer ---------------------------------

    // -------------------------------- Employee ---------------------------------
    // -------------------------------- Show Employee

    @GetMapping("/admin/employee")
    public String getAllEmployee(Model model) {
        List<User> employees = this.userService.getUsersByRoleId(2, true);
        model.addAttribute("employees", employees);
        return "admin/employee/show";
    }
    // -------------------------------- Show Employee

    // -------------------------------- Create Employee

    @GetMapping("/admin/employee/create") // GET
    public String getCreateEmployeePage(Model model) {
        model.addAttribute("newEmployee", new User());
        System.out.println("newEmployee");
        return "admin/employee/create";
    }

    @PostMapping(value = "admin/employee/create")

    public String createEmployeePage(Model model, @ModelAttribute("newEmployee") @Valid User employee,
            BindingResult newUserBindingResult, HttpServletRequest request,
            @RequestParam("imagesFile") MultipartFile file) {
        // validation
        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }
        if (newUserBindingResult.hasErrors()) {
            return "admin/employee/create";
        }
        if (userService.checkEmailExist(employee.getEmail())) {
            request.setAttribute("message", "Email is already registered. Try logging in.");
            return "redirect:/admin/employee/create?exit";
        }
        //

        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(employee.getPassword());
        employee.setAvatar(avatar);
        employee.setPassword(hashPassword);
        employee.setStatus(true);
        employee.setRole(this.userService.getRoleByName(employee.getRole().getName()));
        // save
        this.userService.handleSaveUser(employee);

        return "redirect:/admin/employee";
    }

    // -------------------------------- Create Employee

    // -------------------------------- Update Employee
    @RequestMapping("/admin/employee/update/{id}") // GET
    public String getUpdateEmployeePage(Model model, @PathVariable long id) {
        // chú ý category sử lý chỗ này

        User currentEmployee = this.userService.getUserById(id);

        // Ensure customer is not null before adding to model
        if (currentEmployee == null) {
            // Handle the case where the user is not found
            return "redirect:/admin/employee"; // Redirect or show an error page
        }

        // Ensure customer is not null before adding to model
        if (!currentEmployee.isStatus()) {
            // Handle the case where the user is not found
            return "redirect:/admin/employee"; // Redirect or show an error page
        }
        model.addAttribute("newEmployee", currentEmployee);
        return "admin/employee/update";
    }

    @PostMapping("/admin/employee/update")
    public String postUpdateEmployee(Model model, @ModelAttribute("newEmployee") @Valid User employee,
            BindingResult newProductBindingResult,
            @RequestParam("imagesFile") MultipartFile file) {
        // // validate
        // if (newProductBindingResult.hasErrors()) {
        // return "admin/customer/update";
        // }
        User currentEmployee = this.userService.getUserById(employee.getId());
        if (currentEmployee != null) {
            // update new image
            if (!file.isEmpty()) {
                String img = this.uploadService.handleSaveUploadFile(file, "avatar");
                currentEmployee.setAvatar(img);
            }
            currentEmployee.setAddress(employee.getAddress());
            currentEmployee.setFullName(employee.getFullName());
            currentEmployee.setPhone(employee.getPhone());

            this.userService.handleSaveUser(currentEmployee);
        }
        return "redirect:/admin/employee";
    }

    // -------------------------------- Update Employee

    // -------------------------------- Delete Employee
    @GetMapping("/admin/employee/delete/{id}")
    public String getDeleteEmployeePage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        // User user = new User();
        // user.setId(id);
        model.addAttribute("newEmployee", new User());
        return "admin/employee/delete";
    }

    @PostMapping("/admin/employee/delete")
    public String postDeleteEmployee(Model model, @ModelAttribute("newEmployee") User employee) {
        User currentEmployee = this.userService.getUserById(employee.getId());

        if (currentEmployee != null) {

            currentEmployee.setStatus(false);
            ;

            this.userService.handleSaveUser(currentEmployee);
        }
        return "redirect:/admin/employee";
    }
    // -------------------------------- Delete Employee

    // -------------------------------- Employee Detail
    @GetMapping("/admin/employee/{id}")
    public String getEmployeeDetailPage(Model model, @PathVariable long id) {
        User newEmployee = this.userService.getUserById(id);
        model.addAttribute("newEmployee", newEmployee);
        System.out.println(newEmployee);
        model.addAttribute("id", id);
        return "admin/employee/detail";
    }

    // -------------------------------- Employee Detail

    // -------------------------------- Employee ---------------------------------

}
