package gruop7.gundamshop.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255, message = "Note should not exceed 255 characters")
    private String note;

    @Size(max = 15, message = "Phone number should not exceed 15 characters")
    private String phoneNumber;

    @NotNull(message = "Status cannot be null")
    private Boolean status;

    @NotNull(message = "Subject name cannot be null")
    @Size(max = 100, message = "Subject name should not exceed 100 characters")
    private String subjectName;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    // Constructors
    public Contact() {
    }

    public Contact(String note, String phoneNumber, Boolean status, String subjectName, User user) {
        this.note = note;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.subjectName = subjectName;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Optional: Override toString() for better debugging
    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", subjectName='" + subjectName + '\'' +
                ", user=" + (user != null ? user.getId() : "null") +
                '}';
    }
}
