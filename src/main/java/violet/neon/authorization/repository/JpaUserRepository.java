package violet.neon.authorization.repository;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.ListCrudRepository;
import violet.neon.authorization.model.User;

@ConditionalOnProperty(name = "repository.type", havingValue = "jpa", matchIfMissing = true)
public interface JpaUserRepository extends UserRepository, ListCrudRepository<User, String> {
}
