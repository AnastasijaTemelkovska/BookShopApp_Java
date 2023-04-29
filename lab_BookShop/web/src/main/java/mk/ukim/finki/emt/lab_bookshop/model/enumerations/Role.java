package mk.ukim.finki.emt.lab_bookshop.model.enumerations;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_LIBRARIAN,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}

