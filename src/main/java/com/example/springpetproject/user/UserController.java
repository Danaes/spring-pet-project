package com.example.springpetproject.user;

import com.example.springpetproject.user.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
