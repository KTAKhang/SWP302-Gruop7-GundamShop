package gruop7.gundamshop.service;

import gruop7.gundamshop.domain.Contact;
import gruop7.gundamshop.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public void saveContact(Contact contact) {
        // Set status or other properties if needed
        contact.setStatus(true);  // Set the status to true or whatever is appropriate
        contactRepository.save(contact);  // Save contact to the database
    }
}
