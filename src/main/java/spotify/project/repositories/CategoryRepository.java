package spotify.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spotify.project.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
