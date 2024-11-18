package gruop7.gundamshop.controller.admin;

import gruop7.gundamshop.domain.Contact;
import gruop7.gundamshop.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/admin/contact")
    public String showContactList(Model model) {
        model.addAttribute("contactList", contactService.getAllContacts());
        return "admin/contact/show"; // Giao diện JSP hiển thị danh sách
    }

    // View contact detail
    @GetMapping("/admin/contact/view/{id}")
    public String viewContactDetail(@PathVariable("id") Long id, Model model) {
        Contact contact = contactService.getContactById(id);

        if (contact == null) {
            model.addAttribute("errorMessage", "Contact not found.");
            return "admin/contact/show"; // Redirect to contact list page
        }

        model.addAttribute("contact", contact);
        return "admin/contact/view"; // Renders the detail view
    }

    // Confirm delete contact (for confirmation page)
    @GetMapping("/admin/contact/confirm-delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String confirmDeleteContact(@PathVariable("id") Long id, Model model) {
        Contact contact = contactService.getContactById(id);

        if (contact == null) {
            model.addAttribute("errorMessage", "Contact not found.");
            return "redirect:/admin/contact"; // Redirect back to the contact list if not found
        }

        model.addAttribute("contact", contact);
        return "admin/contact/delete"; // Render the confirmation page
    }

    // Handle deletion of contact
    @PostMapping("/admin/contact/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteContact(@RequestParam("id") Long id, Model model) {
        boolean isDeleted = contactService.deleteContact(id);

        if (!isDeleted) {
            model.addAttribute("errorMessage", "Failed to delete contact.");
            return "redirect:/admin/contact"; // Redirect back to the contact list if deletion fails
        }

        return "redirect:/admin/contact"; // Redirect to the contact list page after successful deletion
    }

}
