package violet.neon.authorization.service;

import org.springframework.stereotype.Service;
import violet.neon.authorization.exception.UserNotFoundException;
import violet.neon.authorization.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final Map<String, User> users;

    public UserServiceImpl() {
        this.users = new HashMap<>();
    }

    @Override
    public User addUser(User user) {
        user.setId(String.valueOf(UUID.randomUUID()));
        users.put(user.getId(), user);

        return user;
    }

    @Override
    public User findUser(String id) {
        User user = users.get(id);

        if (user == null) {
            throw new UserNotFoundException(id);
        }

        return user;
    }

    @Override
    public User updateUser(User user) {
        if (!users.containsKey(user.getId())) {
            throw new UserNotFoundException(user.getId());
        }

        this.users.put(user.getId(), user);

        return user;
    }

    @Override
    public User deleteUser(String id) {
        if (!users.containsKey(id)) {
            throw new UserNotFoundException(id);
        }

        return users.remove(id);
    }

    @Override
    public Collection<User> getAllUsers() {
        return users.values();
    }
}
