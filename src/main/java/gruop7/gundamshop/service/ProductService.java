package gruop7.gundamshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import gruop7.gundamshop.domain.Cart;
import gruop7.gundamshop.domain.CartDetail;
import gruop7.gundamshop.domain.Category;
import gruop7.gundamshop.domain.Order;
import gruop7.gundamshop.domain.OrderDetail;
import gruop7.gundamshop.domain.Product;
import gruop7.gundamshop.domain.Role;
import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.domain.dto.ProductCriteriaDTO;
import gruop7.gundamshop.repository.CartDetailRepository;
import gruop7.gundamshop.repository.CartRepository;
import gruop7.gundamshop.repository.CategoryRepository;
import gruop7.gundamshop.repository.OrderDetailRepository;
import gruop7.gundamshop.repository.OrderRepository;
import gruop7.gundamshop.repository.ProductRepository;
import gruop7.gundamshop.service.specification.ProductSpecs;
import jakarta.servlet.http.HttpSession;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
            CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService,
            OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository) {
        this.userService = userService;
        this.cartDetailRepository = cartDetailRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public Optional<OrderDetail> getOrderDetailById(long id) {
        return orderDetailRepository.findById(id);
    }

    public Page<Product> fetchProductsWithSpec(Pageable page, ProductCriteriaDTO productCriteriaDTO) {
        Specification<Product> combinedSpec = Specification.where(ProductSpecs.matchStatus(true)); // Chỉ lấy sản phẩm
                                                                                                   // có status = 1

        if (productCriteriaDTO.getTarget() != null && productCriteriaDTO.getTarget().isPresent()) {
            Specification<Product> currentSpecs = ProductSpecs.matchListTarget(productCriteriaDTO.getTarget().get());
            combinedSpec = combinedSpec.and(currentSpecs);
        }

        if (productCriteriaDTO.getFactory() != null && productCriteriaDTO.getFactory().isPresent()) {
            Specification<Product> currentSpecs = ProductSpecs.matchListFactory(productCriteriaDTO.getFactory().get());
            combinedSpec = combinedSpec.and(currentSpecs);
        }

        if (productCriteriaDTO.getPrice() != null && productCriteriaDTO.getPrice().isPresent()) {
            Specification<Product> currentSpecs = this.buildPriceSpecification(productCriteriaDTO.getPrice().get());
            combinedSpec = combinedSpec.and(currentSpecs);
        }

        return this.productRepository.findAll(combinedSpec, page);
    }

    public List<String> getAllFactories() {
        return productRepository.findAll()
                .stream()
                .map(Product::getFactory)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getAllTargets() {
        return productRepository.findAll()
                .stream()
                .map(Product::getTarget)
                .distinct()
                .collect(Collectors.toList());
    }

    // case 6
    public Specification<Product> buildPriceSpecification(List<String> price) {
        Specification<Product> combinedSpec = Specification.where(null); // disconjunction
        for (String p : price) {
            double min = 0;
            double max = 0;

            // Set the appropriate min and max based on the price range string
            switch (p) {
                case "duoi-10-trieu":
                    min = 1;
                    max = 10000000;
                    break;
                case "10-15-trieu":
                    min = 10000000;
                    max = 15000000;
                    break;
                case "15-20-trieu":
                    min = 15000000;
                    max = 20000000;
                    break;
                case "tren-20-trieu":
                    min = 20000000;
                    max = 200000000;
                    break;
            }

            if (min != 0 && max != 0) {
                Specification<Product> rangeSpec = ProductSpecs.matchMultiplePrice(min, max);
                combinedSpec = combinedSpec.or(rangeSpec);
            }
        }

        return combinedSpec;
    }

    public List<Product> fetchProducts() {
        return this.productRepository.findAllByStatus(true);
    }

    public Product createProduct(Product pr) {
        return this.productRepository.save(pr);
    }

    public Product getProductByIdAndStatus(long id, boolean status) {
        return this.productRepository.findByIdAndStatus(id, status);
    }

    public List<Product> getProductByStatus(boolean status) {
        return productRepository.findAllByStatus(status);
    }

    public Product handleSaveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public Page<Product> fetchProducts(Pageable page) {
        return this.productRepository.findAll(page);
    }

    public Product getByIdAndStatus(Long id, boolean status) {
        return this.productRepository.findByIdAndStatus(id, status);
    }

    public Category getCategoryByName(String name) {
        return this.categoryRepository.findByName(name);
    }

    public List<CartDetail> getCartDetailsByProduct(Product product) {
        return this.cartDetailRepository.findByProduct(product);
    }

    public Optional<Product> getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void handleAddProductToCart(String email, long productId, HttpSession session, long quantity) {

        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            // check user đã có Cart chưa ? nếu chưa -> tạo mới
            Cart cart = this.cartRepository.findByUser(user);

            if (cart == null) {
                // tạo mới cart
                Cart otherCart = new Cart();
                otherCart.setUser(user);
                otherCart.setSum(0);

                cart = this.cartRepository.save(otherCart);
            }

            // save cart_detail
            // tìm product by id

            Optional<Product> productOptional = this.productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product realProduct = productOptional.get();

                // check sản phẩm đã từng được thêm vào giỏ hàng trước đây chưa ?
                CartDetail oldDetail = this.cartDetailRepository.findByCartAndProduct(cart, realProduct);
                //
                if (oldDetail == null) {
                    CartDetail cd = new CartDetail();
                    cd.setCart(cart);
                    cd.setProduct(realProduct);
                    cd.setPrice(realProduct.getPrice());
                    cd.setQuantity(quantity);
                    this.cartDetailRepository.save(cd);

                    // update cart (sum);
                    int s = cart.getSum() + 1;
                    cart.setSum(s);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", s);
                } else {
                    oldDetail.setQuantity(oldDetail.getQuantity() + quantity);
                    this.cartDetailRepository.save(oldDetail);
                }

            }

        }
    }

    public Cart fetchByUser(User user) {
        return this.cartRepository.findByUser(user);
    }

    public void handleRemoveCartDetail(long cartDetailId, HttpSession session) {
        Optional<CartDetail> cartDetailOptional = this.cartDetailRepository.findById(cartDetailId);
        if (cartDetailOptional.isPresent()) {
            CartDetail cartDetail = cartDetailOptional.get();

            Cart currentCart = cartDetail.getCart();
            this.cartDetailRepository.deleteById(cartDetailId);

            if (currentCart.getSum() > 1) {
                int s = currentCart.getSum() - 1;
                currentCart.setSum(s);
                session.setAttribute("sum", s);
                this.cartRepository.save(currentCart);
            } else {
                this.cartRepository.deleteById(currentCart.getId());
                session.setAttribute("sum", 0);
            }
        }
    }

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails) {
        for (CartDetail cartDetail : cartDetails) {
            Optional<CartDetail> cdOptional = this.cartDetailRepository.findById(cartDetail.getId());
            if (cdOptional.isPresent()) {
                CartDetail currentCartDetail = cdOptional.get();
                currentCartDetail.setQuantity(cartDetail.getQuantity());
                this.cartDetailRepository.save(currentCartDetail);
            }
        }
    }

    public void handlePlaceOrder(User user, HttpSession session, String receiverName, String receiverAddress,
            String receiverPhone, String Note) {

        Cart cart = this.cartRepository.findByUser(user);
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();

            if (cartDetails != null) {
                Order order = new Order();
                order.setUser(user);
                order.setReceiverName(receiverName);
                order.setReceiverAddress(receiverAddress);
                order.setReceiverPhone(receiverPhone);
                order.setNote(Note);
                order.setOrderDate(LocalDateTime.now());
                order.setStatus("PENDING");

                double sum = 0;
                for (CartDetail cd : cartDetails) {
                    sum += cd.getPrice() * cd.getQuantity();
                }
                order.setTotalPrice(sum);
                order = this.orderRepository.save(order);

                for (CartDetail cd : cartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cd.getProduct());

                    // Chuyển đổi BigDecimal và ép kiểu int
                    orderDetail.setPrice(BigDecimal.valueOf(cd.getPrice()));
                    orderDetail.setQuantity((int) cd.getQuantity());

                    this.orderDetailRepository.save(orderDetail);

                    Product product = cd.getProduct();
                    product.setQuantity(product.getQuantity() - cd.getQuantity());
                    product.setSold(product.getSold() + cd.getQuantity());

                    this.productRepository.save(product);
                }

                for (CartDetail cd : cartDetails) {
                    this.cartDetailRepository.deleteById(cd.getId());
                }

                this.cartRepository.deleteById(cart.getId());
                session.setAttribute("sum", 0);
            }
        }
    }

    public List<Product> findAll(Specification<Product> spec) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    public List<Product> getProductByNameOrCategory(String keyword, boolean status) {
        return productRepository.findByNameOrCategoryNameAndStatus(keyword, status);
    }

    public Page<Product> searchProducts(String keyword, Pageable pageable,
            ProductCriteriaDTO productCriteriaDTO) {
        // Bắt đầu với điều kiện cơ bản để chỉ bao gồm sản phẩm có trạng thái = true
        Specification<Product> combinedSpec = Specification.where(ProductSpecs.matchStatus(true));

        // Nếu có keyword được cung cấp, sử dụng keyword để tìm kiếm
        if (keyword != null && !keyword.isEmpty()) {
            Specification<Product> keywordSpec = ProductSpecs.matchNameOrCategory(keyword);
            combinedSpec = combinedSpec.and(keywordSpec);
        }

        // Thêm lọc theo target
        if (productCriteriaDTO.getTarget() != null && productCriteriaDTO.getTarget().isPresent()) {
            Specification<Product> targetSpec = ProductSpecs.matchListTarget(productCriteriaDTO.getTarget().get());
            combinedSpec = combinedSpec.and(targetSpec);
        }

        // Thêm lọc theo factory
        if (productCriteriaDTO.getFactory() != null && productCriteriaDTO.getFactory().isPresent()) {
            Specification<Product> factorySpec = ProductSpecs.matchListFactory(productCriteriaDTO.getFactory().get());
            combinedSpec = combinedSpec.and(factorySpec);
        }

        // Thêm lọc theo khoảng giá
        if (productCriteriaDTO.getPrice() != null && productCriteriaDTO.getPrice().isPresent()) {
            Specification<Product> priceSpec = this.buildPriceSpecification(productCriteriaDTO.getPrice().get());
            combinedSpec = combinedSpec.and(priceSpec);
        }

        // Trả về kết quả phân trang sử dụng đặc tả kết hợp
        return productRepository.findAll(combinedSpec, pageable);
    }

}
