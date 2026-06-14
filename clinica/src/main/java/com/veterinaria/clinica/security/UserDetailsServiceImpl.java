package com.veterinaria.clinica.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // Usuarios hardcodeados por ahora (después se pueden mover a BD)
    private static final Map<String, String[]> USERS = Map.of(
            "admin", new String[]{new BCryptPasswordEncoder().encode("password123"), "ROLE_ADMIN"},
            "user",  new String[]{new BCryptPasswordEncoder().encode("password123"), "ROLE_USER"}
    );

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] data = USERS.get(username);

        if (data == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        return new User(
                username,
                data[0],
                List.of(new SimpleGrantedAuthority(data[1]))
        );
    }
}