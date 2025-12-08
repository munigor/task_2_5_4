package habsida.spring.boot_security.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@UniqueUsername(groups = {OnCreate.class, OnUpdate.class})
public class UserDto {
    private Long id;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 2, max = 40, groups = {OnCreate.class, OnUpdate.class})
    private String firstname;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 2, max = 40, groups = {OnCreate.class, OnUpdate.class})
    private String lastname;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @Min(value = 1, groups = {OnCreate.class, OnUpdate.class})
    @Max(value = 150, groups = {OnCreate.class, OnUpdate.class})
    private Integer age;

    @Email(groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 5, max = 40, groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @Size(min = 5, max = 40, groups = {OnCreate.class})
    private String password;

    private List<Long> roles = new ArrayList<>();

    public boolean isPresentRole(Long id) {
        return roles.contains(id);
    }
}

