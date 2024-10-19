package gruop7.gundamshop.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import gruop7.gundamshop.domain.Category;
import gruop7.gundamshop.domain.Product;
import gruop7.gundamshop.domain.Role;
import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.repository.CategoryRepository;
import gruop7.gundamshop.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public List<Product> fetchProducts() {
        return this.productRepository.findAll();
    }

    public Page<Product> fetchProducts(Pageable page) {
        // TODO Auto-generated method stub
        return this.productRepository.findAll(page);
    }

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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

    public Product getProductById(long id) {
        return this.productRepository.findById(id);
    }

}
