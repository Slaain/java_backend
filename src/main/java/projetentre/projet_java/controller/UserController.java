package projetentre.projet_java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetentre.projet_java.entities.User;
import projetentre.projet_java.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService);
    this.userService= userService;
}

// CREATE
@PostMapping
public ResponseEntity<User> createUser(@RequestBody User user){
    User newUser = userService.createUser(user);
    return ResponseEntity.ok(newUser);
}

//GET ALL
@GetMapping ResponseEntity<List<User>> getAllUsers();
List<Users> users = userService.getAllUsers