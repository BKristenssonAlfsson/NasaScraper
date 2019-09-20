package com.snowcatsystems.nasascraper.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @PostMapping("/adduser")
    public ResponseEntity addUser(@RequestBody UserModel newUser) {
        User temp = user.toEntity(newUser);
        System.out.println(temp.toString());
        //System.out.println(temp.toString());
        //userRepository.save(temp);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
