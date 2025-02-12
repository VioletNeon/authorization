package violet.neon.authorization.controller;

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

    @GetMapping
    public Collection<User> getAllUsers() {
        System.out.println("GET " + "users/");

        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable String id) {
        System.out.println("GET " + "users/" + id);

        return userService.findUser(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        System.out.println("POST " + "users/");

        return userService.addUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        System.out.println("PUT " + "users/");

        return userService.updateUser(user);
    }

    @DeleteMapping("{id}")
    public User deleteUser(@PathVariable String id) {
        System.out.println("DELETE " + "users/" + id);

        return userService.deleteUser(id);
    }
}
