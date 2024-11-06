package gruop7.gundamshop.service.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import gruop7.gundamshop.domain.Category_;
import gruop7.gundamshop.domain.Product;
import gruop7.gundamshop.domain.Product_;

public class ProductSpecs {
    public static Specification<Product> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Product_.NAME), "%" + name + "%");
    }

    // case 1
    public static Specification<Product> minPrice(double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.ge(root.get(Product_.PRICE), price);
    }

    // case 2
    public static Specification<Product> maxPrice(double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.le(root.get(Product_.PRICE), price);
    }

    // case3
    public static Specification<Product> matchFactory(String factory) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Product_.FACTORY), factory);
    }

    // case4
    public static Specification<Product> matchListFactory(List<String> factory) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(Product_.FACTORY)).value(factory);
    }

    // case4
    public static Specification<Product> matchListTarget(List<String> target) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(Product_.TARGET)).value(target);
    }

    // case5
    public static Specification<Product> matchPrice(double min, double max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.gt(root.get(Product_.PRICE), min),
                criteriaBuilder.le(root.get(Product_.PRICE), max));
    }

    // case6
    public static Specification<Product> matchMultiplePrice(double min, double max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(
                root.get(Product_.PRICE), min, max);
    }

    public static Specification<Product> matchStatus(boolean status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Product_.STATUS), status);
    }

    public static Specification<Product> matchNameOrCategory(String keyword) {
        return (root, query, criteriaBuilder) -> {
            // Tạo điều kiện để tìm kiếm tên sản phẩm hoặc tên danh mục
            query.distinct(true); // Đảm bảo rằng không có bản sao
            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get(Product_.NAME), "%" + keyword + "%"),
                    criteriaBuilder.like(root.join(Product_.CATEGORY).get(Category_.NAME), "%" + keyword + "%"));
        };
    }
}
