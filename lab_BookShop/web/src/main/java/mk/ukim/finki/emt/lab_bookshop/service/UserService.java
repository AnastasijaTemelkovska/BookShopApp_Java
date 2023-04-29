package mk.ukim.finki.emt.lab_bookshop.service;

import mk.ukim.finki.emt.lab_bookshop.model.User;
import mk.ukim.finki.emt.lab_bookshop.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, Role role);
}

