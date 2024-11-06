package gruop7.gundamshop.controller.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import gruop7.gundamshop.domain.Cart;
import gruop7.gundamshop.domain.CartDetail;
import gruop7.gundamshop.domain.Product;
import gruop7.gundamshop.domain.ProductReview;
import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.domain.dto.ProductCriteriaDTO;
import gruop7.gundamshop.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import gruop7.gundamshop.service.CategoryService;
import gruop7.gundamshop.service.ProductReviewService;

@Controller
public class ItemController {

    private final ProductService productService;
    private final ProductReviewService productReviewService; // Thêm ProductReviewService
    @Autowired
    private CategoryService categoryService; // Dịch vụ để lấy danh sách category

    public ItemController(ProductService productService, ProductReviewService productReviewService) {
        this.productService = productService;
        this.productReviewService = productReviewService;
    }

    @GetMapping("/product/{id}")
    public String getProductPage(@PathVariable long id, Model model, HttpServletRequest request) {
        // Retrieve the product using its ID
        Optional<Product> productOptional = productService.getProductById(id);

        // Retrieve the current user from the session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("id") == null) {
            return "error/404"; // Handle case where user is not logged in
        }

        long idUser = (long) session.getAttribute("id");
        User currentUser = new User();
        currentUser.setId(idUser);

        // Fetch the user's cart
        Cart cart = productService.fetchByUser(currentUser);
        List<CartDetail> cartDetails = (cart != null) ? cart.getCartDetails() : new ArrayList<>();

        // Check if the product exists; if not, return a 404 error
        if (productOptional.isEmpty()) {
            return "error/404"; // Product not found
        }

        Product product = productOptional.get();
        model.addAttribute("product", product);

        // Fetch CartDetail for the product in the user's cart
        List<CartDetail> productCartDetails = productService.getCartDetailsByProduct(product);
        CartDetail cartDetail = null;

        // Logic to determine which CartDetail to use (if any)
        if (!productCartDetails.isEmpty()) {
            // Example: Get the first cartDetail (or apply your selection logic)
            cartDetail = productCartDetails.get(0);
        }

        model.addAttribute("cartDetail", cartDetail);

        // Fetch reviews for the product
        List<ProductReview> reviews = productReviewService.findReviewsByProductId(id);
        double averageRating = reviews.stream().mapToInt(ProductReview::getRating).average().orElse(0);

        // Add reviews and average rating to the model
        model.addAttribute("reviews", reviews);
        model.addAttribute("averageRating", averageRating);

        // Return the product detail view
        return "customer/product/detail";
    }

    @GetMapping("/products")
    public String getProductPage(Model model,
            ProductCriteriaDTO productCriteriaDTO,
            HttpServletRequest request) {
        int page = 1;
        try {
            if (productCriteriaDTO.getPage().isPresent()) {
                page = Integer.parseInt(productCriteriaDTO.getPage().get());
            }
        } catch (Exception e) {
            // page = 1
        }

        Pageable pageable;

        // Kiểm tra xem sort có phải là null hoặc Optional trống không
        if (productCriteriaDTO.getSort() != null && productCriteriaDTO.getSort().isPresent()) {
            String sort = productCriteriaDTO.getSort().get();
            if ("gia-tang-dan".equals(sort)) {
                pageable = PageRequest.of(page - 1, 10, org.springframework.data.domain.Sort.by("price").ascending());
            } else if ("gia-giam-dan".equals(sort)) {
                pageable = PageRequest.of(page - 1, 10, org.springframework.data.domain.Sort.by("price").descending());
            } else {
                pageable = PageRequest.of(page - 1, 10); // không sắp xếp
            }
        } else {
            pageable = PageRequest.of(page - 1, 10); // không sắp xếp nếu không có sort
        }

        Page<Product> prs = this.productService.fetchProductsWithSpec(pageable, productCriteriaDTO);
        List<Product> products = prs.getContent().size() > 0 ? prs.getContent()
                : new ArrayList<Product>();

        // Lấy danh sách factory và target từ ProductService
        List<String> factories = productService.getAllFactories();
        List<String> targets = productService.getAllTargets();

        String qs = request.getQueryString();
        if (qs != null && !qs.isBlank()) {
            qs = qs.replace("page=" + page, "");
        }

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", prs.getTotalPages());
        model.addAttribute("queryString", qs);

        // Thêm dữ liệu cho bộ lọc
        model.addAttribute("factories", factories);
        model.addAttribute("targets", targets);

        return "customer/product/show";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(@PathVariable long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        long productId = id;
        String email = (String) session.getAttribute("email");

        this.productService.handleAddProductToCart(email, productId, session, 1);

        return "redirect:/";
    }

    @PostMapping("/add-product-from-view-detail")
    public String handleAddProductFromViewDetail(
            @RequestParam("id") long id,
            @RequestParam("quantity") long quantity,
            HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        String email = (String) session.getAttribute("email");
        this.productService.handleAddProductToCart(email, id, session, quantity);
        return "redirect:/product/" + id;
    }

    @GetMapping("/cart")
    public String getCartPage(Model model, HttpServletRequest request) {
        User currentUser = new User();// null
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);

        Cart cart = this.productService.fetchByUser(currentUser);

        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();

        double totalPrice = 0;
        for (CartDetail cd : cartDetails) {
            totalPrice += cd.getPrice() * cd.getQuantity();
        }

        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice);

        model.addAttribute("cart", cart);

        return "customer/cart/show";
    }

    @PostMapping("/delete-cart-product/{id}")
    public String deleteCartDetail(@PathVariable long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        long cartDetailId = id;
        this.productService.handleRemoveCartDetail(cartDetailId, session);
        return "redirect:/cart";
    }

    @GetMapping("/out-of-stock")
    public String getOutOfStock(Model model) {

        return "customer/cart/out-of-stock";
    }

    @GetMapping("/checkout")
    public String getCheckOutPage(Model model, HttpServletRequest request) {
        User currentUser = new User();// null
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);

        Cart cart = this.productService.fetchByUser(currentUser);

        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();
        // Kiểm tra sản phẩm có số lượng bằng 0
        for (CartDetail cd : cartDetails) {
            if (cd.getProduct().getQuantity() == 0) {
                return "redirect:/out-of-stock"; // Redirect to an out-of-stock page
            }
            if (cd.getProduct().getQuantity() < cd.getQuantity()) {
                return "redirect:/cart?errorInventory"; // Redirect to an out-of-stock page
            }
        }

        double totalPrice = 0;
        for (CartDetail cd : cartDetails) {
            totalPrice += cd.getPrice() * cd.getQuantity();
        }

        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice);

        return "customer/cart/checkout";
    }

    @PostMapping("/confirm-checkout")
    public String getCheckOutPage(@ModelAttribute("cart") Cart cart) {
        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();
        this.productService.handleUpdateCartBeforeCheckout(cartDetails);
        return "redirect:/checkout";
    }

    @PostMapping("/place-order")
    public String handlePlaceOrder(
            HttpServletRequest request,
            @RequestParam("receiverName") String receiverName,
            @RequestParam("receiverAddress") String receiverAddress,
            @RequestParam("receiverPhone") String receiverPhone,
            @RequestParam("Note") String Note) {

        User currentUser = new User();// null
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);

        this.productService.handlePlaceOrder(currentUser, session, receiverName,
                receiverAddress, receiverPhone, Note);

        return "redirect:/thanks";

    }

    @GetMapping("/thanks")
    public String getThankYouPage(Model model) {

        return "customer/cart/thank";
    }

    @GetMapping("/search")
    public String getSearchPage(Model model,
            ProductCriteriaDTO productCriteriaDTO,
            HttpServletRequest request) {
        int page = productCriteriaDTO.getPage()
                .map(Integer::parseInt)
                .orElse(1);

        // Khởi tạo Pageable mặc định không có sắp xếp
        Pageable pageable = PageRequest.of(page - 1, 10);

        // Kiểm tra xem có sort hay không
        if (productCriteriaDTO.getSort().isPresent()) {
            String sort = productCriteriaDTO.getSort().get();
            if ("gia-tang-dan".equals(sort)) {
                pageable = PageRequest.of(page - 1, 10, org.springframework.data.domain.Sort.by("price").ascending());
            } else if ("gia-giam-dan".equals(sort)) {
                pageable = PageRequest.of(page - 1, 10, org.springframework.data.domain.Sort.by("price").descending());
            }
        }

        // Lấy từ khóa tìm kiếm từ người dùng
        String keyword = productCriteriaDTO.getSearchKeyword()
                .orElse(request.getParameter("query"));

        // Tìm kiếm sản phẩm theo tên sản phẩm hoặc tên danh mục với các tiêu chí lọc
        Page<Product> prs = this.productService.searchProducts(keyword, pageable, productCriteriaDTO);
        List<Product> products = prs.getContent();

        List<String> factories = productService.getAllFactories();
        List<String> targets = productService.getAllTargets();

        // Xử lý query string để phân trang
        String qs = request.getQueryString();
        if (qs != null && !qs.isBlank()) {
            qs = qs.replace("page=" + page, "");
        }

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", prs.getTotalPages());
        model.addAttribute("queryString", qs);
        model.addAttribute("factories", factories);
        model.addAttribute("targets", targets);
        model.addAttribute("searchKeyword", keyword);

        return "customer/product/show";
    }

}
