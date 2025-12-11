package habsida.spring.boot_security.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Objects;

@Entity
@Table(name="roles")
@Data
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 30, unique = true, nullable = false)
    private UserRole name;

    @Override
    public String getAuthority() {
        return name.toAuthority();
    }

    public String getNameAsString() {
        return name.toString();
    }
}
