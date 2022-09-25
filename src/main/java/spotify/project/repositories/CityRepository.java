package spotify.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spotify.project.models.City;

public interface CityRepository extends JpaRepository<City, Integer> {
    City findByName(String name);
}
