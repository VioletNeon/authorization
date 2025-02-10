package violet.neon.authorization.service;

import violet.neon.authorization.model.User;

import java.util.Collection;

public interface UserService {
    User addUser(User user);

    User findUser(int id);

    User updateUser(User user);

    User deleteUser(int id);

    Collection<User> getAllUsers();
}
