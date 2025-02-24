package violet.neon.authorization.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
public class User {
    @Id
    private String id;
    private int department;
    private String fullName;
}