package violet.neon.authorization.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import violet.neon.authorization.model.User;

@Repository
@Profile("jpa")
public interface JpaUserRepository extends UserRepository, ListCrudRepository<User, String> {
}
