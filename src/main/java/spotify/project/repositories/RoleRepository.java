package spotify.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spotify.project.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByRoleType(String roleType);
}

