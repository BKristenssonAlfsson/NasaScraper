package com.snowcatsystems.nasascraper.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean registerNewUserAccount(final UserModel userModel) {

        final User user = new User();

        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setUsername(userModel.getUsername());
        user.setActive(user.getActive());
        user.setRoles(user.getRoles());

        User exist = userRepository.findByUsername(user.getUsername());

        if ( exist == null ) {
            userRepository.save(user);
            return false;
        } else {
            return true;
        }
    }
}
