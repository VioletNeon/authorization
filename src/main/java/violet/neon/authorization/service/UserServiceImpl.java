package violet.neon.authorization.service;

import org.springframework.stereotype.Service;
import violet.neon.authorization.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final Map<Integer, User> users;
    private int lastId = 0;

    public UserServiceImpl() {
        this.users = new HashMap<>();
    }

    @Override
    public User addUser(User user) {
        user.setId(++lastId);
        users.put(lastId, user);

        return user;
    }

    @Override
    public User findUser(int id) {
        return users.get(id);
    }

    @Override
    public User updateUser(User user) {
        if (!users.containsKey(user.getId())) {
            return null;
        }

        this.users.put(user.getId(), user);

        return user;
    }

    @Override
    public User deleteUser(int id) {
        return users.remove(id);
    }

    @Override
    public Collection<User> getAllUsers() {
        return users.values();
    }
}
