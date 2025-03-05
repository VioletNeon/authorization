package violet.neon.authorization.repository;

import org.springframework.data.repository.ListCrudRepository;
import violet.neon.authorization.model.User;

public interface UserRepository extends ListCrudRepository<User, String> {
}
