package gruop7.gundamshop.repository;

import gruop7.gundamshop.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
