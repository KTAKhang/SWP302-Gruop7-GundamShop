package gruop7.gundamshop.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import gruop7.gundamshop.domain.Cart;
import gruop7.gundamshop.domain.CartDetail;
import gruop7.gundamshop.domain.Category;
import gruop7.gundamshop.domain.Order;
import gruop7.gundamshop.domain.OrderDetail;
import gruop7.gundamshop.domain.Product;
import gruop7.gundamshop.domain.Role;
import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.repository.CartDetailRepository;
import gruop7.gundamshop.repository.CartRepository;
import gruop7.gundamshop.repository.CategoryRepository;
import gruop7.gundamshop.repository.OrderDetailRepository;
import gruop7.gundamshop.repository.OrderRepository;
import gruop7.gundamshop.repository.ProductRepository;
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

    public List<Product> fetchProducts() {
        return this.productRepository.findAllByStatus(true);
    }

    public Page<Product> fetchProducts(Pageable page) {
        // TODO Auto-generated method stub
        return this.productRepository.findAll(page);
    }

    public Product createProduct(Product pr) {
        return this.productRepository.save(pr);
    }

    public List<Product> getProductByStatus(boolean status) {
        return productRepository.findAllByStatus(status);
    }

    public Product handleSaveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public Category getCategoryByName(String name) {
        return this.categoryRepository.findByName(name);
    }

    public Optional<Product> getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void handleAddProductToCart(String email, long productId, HttpSession session) {

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
                    cd.setQuantity(1);
                    this.cartDetailRepository.save(cd);

                    // update cart (sum);
                    int s = cart.getSum() + 1;
                    cart.setSum(s);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", s);
                } else {
                    oldDetail.setQuantity(oldDetail.getQuantity() + 1);
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
            // delete cart-detail
            this.cartDetailRepository.deleteById(cartDetailId);

            // update cart
            if (currentCart.getSum() > 1) {
                // update current cart
                int s = currentCart.getSum() - 1;
                currentCart.setSum(s);
                session.setAttribute("sum", s);
                this.cartRepository.save(currentCart);
            } else {
                // delete cart (sum = 1)
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

    // public void handlePlaceOrder(
    // User user, HttpSession session,
    // String receiverName, String receiverAddress, String receiverPhone) {

    // // create order
    // Order order = new Order();
    // order.setUser(user);
    // order.setReceiverName(receiverName);
    // order.setReceiverAddress(receiverAddress);
    // order.setReceiverPhone(receiverPhone);
    // order = this.orderRepository.save(order);

    // // create orderDetail

    // // step 1: get cart by user
    // Cart cart = this.cartRepository.findByUser(user);
    // if (cart != null) {
    // List<CartDetail> cartDetails = cart.getCartDetails();

    // if (cartDetails != null) {
    // for (CartDetail cd : cartDetails) {
    // OrderDetail orderDetail = new OrderDetail();
    // orderDetail.setOrder(order);
    // orderDetail.setProduct(cd.getProduct());
    // orderDetail.setPrice(cd.getPrice());
    // orderDetail.setQuantity(cd.getQuantity());

    // this.orderDetailRepository.save(orderDetail);
    // }

    // // step 2: delete cart_detail and cart
    // for (CartDetail cd : cartDetails) {
    // this.cartDetailRepository.deleteById(cd.getId());
    // }

    // this.cartRepository.deleteById(cart.getId());

    // // step 3 : update session
    // session.setAttribute("sum", 0);
    // }
    // }

    // }

}
