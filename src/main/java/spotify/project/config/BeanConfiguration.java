package spotify.project.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import spotify.project.services.UserService;


@Configuration
public class BeanConfiguration {


    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.createRoles();
            userService.createOwner();
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
