package violet.neon.authorization.service;

import violet.neon.authorization.model.User;

import java.util.Collection;

public interface UserService {
    User addUser(User user);

    User findUser(String id);

    User updateUser(User user);

    User deleteUser(String id);

    Collection<User> getAllUsers();
}
