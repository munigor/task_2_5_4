package habsida.spring.boot_security.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.Objects;

@Entity
@Table(name="roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 30, unique = true, nullable = false)
    private UserRole name;

    public Role() {
    }

    public Role(UserRole name) {
        this.name = name;
    }
}
