package projetentre.projet_java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetentre.projet_java.entities.User;
import projetentre.projet_java.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("Creating a new user: {}", user.getEmail());
        User newUser = userService.createUser(user);
        logger.info("User created successfully with ID: {}", newUser.getId());
        return ResponseEntity.ok(newUser);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Fetching all users...");
        List<User> users = userService.getAllUsers();
        logger.info("Found {} users", users.size());
        return ResponseEntity.ok(users);
    }

    // GET ONE
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        logger.info("Fetching user with ID: {}", id);
        return userService.getUserById(id)
                .map(user -> {
                    logger.info("User found: {}", user.getEmail());
                    return ResponseEntity.ok(user);
                })
                .orElseGet(() -> {
                    logger.warn("User with ID: {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        logger.info("Updating user with ID: {}", id);
        Optional<User> existingUser = userService.getUserById(id);
        if (!existingUser.isPresent()) {
            logger.warn("User with ID: {} not found", id);
            return ResponseEntity.notFound().build();
        }
        user.setId(id);
        User updatedUser = userService.updateUser(user);
        logger.info("User with ID: {} updated successfully", id);
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.info("Request to delete user with ID: {}", id);
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            userService.deleteUser(id);
            logger.info("User with ID: {} deleted successfully", id);
            return ResponseEntity.ok().build();
        } else {
            logger.warn("User with ID: {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    // GET BY EMAIL
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        logger.info("Fetching user with email: {}", email);
        User user = userService.findByEmail(email);
        if (user != null) {
            logger.info("User found: {}", user.getEmail());
            return ResponseEntity.ok(user);
        } else {
            logger.warn("User with email: {} not found", email);
            return ResponseEntity.notFound().build();
        }
    }

    // ASSIGN ROLE TO USER
    @PatchMapping("/{userId}/assign-role/{roleId}")
    public ResponseEntity<User> assignRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
        try {
            User updatedUser = userService.assignRoleToUser(userId, roleId);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            logger.error("Error assigning role: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
