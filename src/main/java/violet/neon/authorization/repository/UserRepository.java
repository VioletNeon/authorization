package violet.neon.authorization.repository;

import violet.neon.authorization.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    Collection<User> findAll();

    User save(User user);

    Optional<User> findById(String id);

    void deleteById(String id);
}
