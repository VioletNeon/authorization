package violet.neon.authorization.service;

import violet.neon.authorization.model.User;

import java.util.Collection;

public interface UserService {
    String addUser(User user);

    User findUser(String id);

    User updateUser(User user);

    void deleteUser(String id);

    Collection<User> getAllUsers();
}
