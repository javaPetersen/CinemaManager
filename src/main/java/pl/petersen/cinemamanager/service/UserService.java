package pl.petersen.cinemamanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.petersen.cinemamanager.entity.User;
import pl.petersen.cinemamanager.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void save(User user) {
        user.setActive(true);
        user.setRole("ROLE_USER");
        String encode = passwordEncoder.encode(user.getPassword());
        System.out.println(encode);
        userRepository.save(user);
    }

    public boolean checkEmail(User user) {
        Optional<User> potentialUser = userRepository.findByEmail(user.getEmail());
        return potentialUser.isPresent();
    }

}
