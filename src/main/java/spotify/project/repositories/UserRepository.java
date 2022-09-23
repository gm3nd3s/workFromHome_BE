package spotify.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spotify.project.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
