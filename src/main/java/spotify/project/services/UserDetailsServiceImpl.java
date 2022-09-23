package spotify.project.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spotify.project.models.User;
import spotify.project.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    private UserRepository userRepository;
    private UserService userService;

    public UserDetailsServiceImpl(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override //é apartir deste método que o spring vai buscar/reconhecer os users aonde eles foram guardados
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if(user==null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found int the database");
        }else {
            log.info("User found in the database: {}", username);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();//adicionar/criar as autorizações que do user para depois adicionar ao User da UserDetails
        user.getRoles()
                .forEach(role -> authorities
                        .add((new SimpleGrantedAuthority(role.getRoleType()))));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
