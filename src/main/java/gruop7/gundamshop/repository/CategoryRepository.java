package gruop7.gundamshop.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gruop7.gundamshop.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category save(Category category);

    Category findById(long id);

    List<Category> findAllByStatus(boolean status);

    Category findByName(String name);
}
