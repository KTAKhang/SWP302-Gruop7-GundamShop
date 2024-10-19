package gruop7.gundamshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gruop7.gundamshop.domain.Role;
import gruop7.gundamshop.domain.User;
import gruop7.gundamshop.repository.RoleRepository;
import gruop7.gundamshop.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public String handleHello() {
        return "Hello for UserService!";
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    // show user(employee, customer)
    public List<User> getUsersByRoleId(long roleId, boolean status) {
        return userRepository.findAllByRole_IdAndStatus(roleId, status);
    }

    // public Optional<User> fetchUserById(long id) {
    // return this.userRepository.findById(id);
    // }

    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public List<User> getAllUsersByEmail(String email) {
        return this.userRepository.findOneByEmail(email);
    }

    public User handleSaveUser(User user) {
        return this.userRepository.save(user);
    }

    public void deleteAUser(long id) {
        this.userRepository.deleteById(id);
    }

    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);
    }

    public boolean checkEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public void updatePassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email);
        user.setPassword(newPassword); // Hash password before saving
        userRepository.save(user);
    }

}
