package habsida.spring.boot_security.demo.model;

public enum UserRole {
    USER,
    ADMIN;

    public String toAuthority() {
        return "ROLE_" + this.name();
    }
}

