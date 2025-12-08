package habsida.spring.boot_security.demo.dto;

import habsida.spring.boot_security.demo.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, UserDto> {

    private final UserRepository userRepository;

    private String message;

    public UniqueUsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(UserDto dto, ConstraintValidatorContext context) {
        if (dto.getUsername() == null) return true;

        boolean exists = userRepository.existsByUsernameAndIdNot(
            dto.getUsername(),
            dto.getId() == null ? -1 : dto.getId()
        );

        if (exists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode("username")
                .addConstraintViolation();
            return false;
        }
        return true;
    }
}

