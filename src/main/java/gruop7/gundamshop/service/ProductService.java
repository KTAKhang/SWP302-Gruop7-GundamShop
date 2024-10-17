package gruop7.gundamshop.service;

import java.util.List;
import org.springframework.stereotype.Service;

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

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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
