package gruop7.gundamshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gruop7.gundamshop.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    void deleteById(long id);

    List<User> findOneByEmail(String email);

    User findById(long id);

    boolean existsByEmail(String email);

    User findByEmail(String email);

}
