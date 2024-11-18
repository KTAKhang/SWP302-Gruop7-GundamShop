package gruop7.gundamshop.repository;

import gruop7.gundamshop.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    // Các phương thức custom nếu cần

}
