package violet.neon.authorization.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import violet.neon.authorization.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@ConditionalOnProperty(name = "repository.type", havingValue = "jdbc")
public class JdbcUserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Collection<User> findAll() {
        System.out.println("findAll - jdbc");
        return jdbcTemplate.query(
                "select id, department, full_name from users",
                this::mapRowToUser
        );
    }

    @Override
    public User save(User user) {
        System.out.println("save - jdbc");

        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId(UUID.randomUUID().toString());

            jdbcTemplate.update(
                    "insert into users (id, department, full_name) values (?, ?, ?)",
                    user.getId(),
                    user.getDepartment(),
                    user.getFullName()
            );
        } else {
            jdbcTemplate.update(
                    "update users set full_name = ?, department = ? where id = ?",
                    user.getFullName(),
                    user.getDepartment(),
                    user.getId()
            );
        }

        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        System.out.println("findById - jdbc");
        List<User> results = jdbcTemplate.query(
            "select id, department, full_name from users where id=?",
            this::mapRowToUser,
            id
        );

        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public void deleteById(String id) {
        System.out.println("deleteById - jdbc");
        jdbcTemplate.update("delete from users where id=?", id);
    }

    private User mapRowToUser(ResultSet row, int rowNum) throws SQLException {
        User user =  new User();

        user.setId(row.getString("id"));
        user.setDepartment(row.getInt("department"));
        user.setFullName(row.getString("full_name"));

        return user;
    }
}
