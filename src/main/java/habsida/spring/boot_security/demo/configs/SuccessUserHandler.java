package habsida.spring.boot_security.demo.configs;

import habsida.spring.boot_security.demo.model.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
    // Spring Security использует объект Authentication, пользователя авторизованной сессии.
    @Override
    public void onAuthenticationSuccess(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        Authentication authentication
    ) throws IOException {

        Set<? extends GrantedAuthority> roles = new HashSet<>(authentication.getAuthorities());
        if (roles.contains(UserRole.ADMIN.toAuthority())) {
            response.sendRedirect( "/admin");
        } else if (roles.contains(UserRole.USER.toAuthority())) {
            response.sendRedirect("/user");
        } else {
            response.sendRedirect("/");
        }
    }
}