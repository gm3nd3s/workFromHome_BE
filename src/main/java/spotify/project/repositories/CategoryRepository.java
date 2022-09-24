package spotify.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spotify.project.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
