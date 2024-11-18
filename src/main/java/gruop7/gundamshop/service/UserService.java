package gruop7.gundamshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import gruop7.gundamshop.domain.Role;
import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.domain.dto.RegisterDTO;
import gruop7.gundamshop.repository.RoleRepository;
import gruop7.gundamshop.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String handleHello() {
        return "Hello from UserService!";
    }

    /**
     * Retrieve all users.
     */
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    /**
     * Get users by role ID and status.
     */
    public List<User> getUsersByRoleId(long roleId, boolean status) {
        return userRepository.findAllByRole_IdAndStatus(roleId, status);
    }

    /**
     * Retrieve user by ID.
     */
    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    /**
     * Convert RegisterDTO to User, encoding the password for secure storage.
     */
    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }

    /**
     * Find a user by email.
     */
    public List<User> getAllUsersByEmail(String email) {
        return this.userRepository.findOneByEmail(email);
    }

    /**
     * Save a user to the database.
     */
    public User handleSaveUser(User user) {
        return this.userRepository.save(user);
    }

    /**
     * Delete a user by ID.
     */
    public void deleteAUser(long id) {
        this.userRepository.deleteById(id);
    }

    /**
     * Get role by role name.
     */
    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }

    /**
     * Check if an email already exists in the database.
     */
    public boolean checkEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }

    /**
     * Retrieve a user by email.
     */
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    /**
     * Update the user's password with a new, encoded password.
     */
    public void updatePassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword)); // Encode the new password
            userRepository.save(user);
        }
    }
}
