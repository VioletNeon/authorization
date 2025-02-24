package violet.neon.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeConvertCallback;
import violet.neon.authorization.model.User;

import java.util.UUID;

@Configuration
public class JdbcConfig {
    @Bean
    BeforeConvertCallback<User> beforeConvertCallback() {
        return (user) -> {
            if (user.getId() == null) {
                user.setId(UUID.randomUUID().toString());
            }

            return user;
        };
    }
}