package com.ap.game.chess.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody UserRequestBody userRequestBody) throws Exception {
        User user = userService.create(userRequestBody);
        if(user==null) new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUsers(@PathVariable String email){
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable long userId){
        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
    }
}