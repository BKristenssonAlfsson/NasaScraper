package com.snowcatsystems.nasascraper.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserService userService = new UserService();

    @PostMapping("/adduser")
    public ResponseEntity addUser(@RequestBody UserModel newUser) {
        User user = userService.registerNewUserAccount(newUser);
        System.out.println(user);
        //System.out.println(temp.toString());
        //userRepository.save(temp);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
