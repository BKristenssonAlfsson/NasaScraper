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
        Boolean user = userService.registerNewUserAccount(newUser);
        if ( user == true ) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestBody UserModel userModel) {
        userService.deleteUser();
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
