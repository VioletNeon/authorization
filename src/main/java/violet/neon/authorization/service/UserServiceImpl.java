package violet.neon.authorization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import violet.neon.authorization.exception.UserNotFoundException;
import violet.neon.authorization.model.User;
import violet.neon.authorization.repository.UserRepository;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String addUser(User user) {
        userRepository.save(user);

        return user.getId();
    }

    @Override
    public User findUser(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User updateUser(User user) {
        this.findUser(user.getId());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        this.findUser(id);

        userRepository.deleteById(id);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }
}
