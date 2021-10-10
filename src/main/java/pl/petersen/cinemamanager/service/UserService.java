package pl.petersen.cinemamanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.petersen.cinemamanager.entity.User;
import pl.petersen.cinemamanager.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void save(User user) {
        user.setActive(true);
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public Boolean checkPasswordMatch(User user) {
        return user.getPassword().equals(user.getPasswordMatcher());
    }

    public boolean checkEmail(User user) {
        Optional<User> potentialUser = userRepository.findByEmail(user.getEmail());
        return potentialUser.isPresent();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public long countAllUsers() {
        return userRepository.count();
    }
}
