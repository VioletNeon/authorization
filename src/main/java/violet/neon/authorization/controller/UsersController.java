package violet.neon.authorization.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import violet.neon.authorization.model.User;
import violet.neon.authorization.service.UserService;

import java.util.Collection;

@RestController
@RequestMapping("users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        System.out.println("users/" + id);

        User user = userService.findUser(id);

        if (user == null) {
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);

        if (updatedUser == null) {
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("{id}")
    public User deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
