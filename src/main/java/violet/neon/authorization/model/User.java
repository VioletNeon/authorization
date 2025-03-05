package violet.neon.authorization.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @UuidGenerator
    private String id;
    private int department;
    private String fullName;
}