package com.snowcatsystems.nasascraper.User;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String test() {
        return "Works";
    }
}
