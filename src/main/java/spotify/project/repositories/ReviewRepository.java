package spotify.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spotify.project.models.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
