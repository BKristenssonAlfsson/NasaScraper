package com.snowcatsystems.nasascraper.User;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test() {
        return "Works";
    }
}
