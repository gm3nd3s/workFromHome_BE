package spotify.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spotify.project.models.City;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
	Optional<City> findByName(String name);

	@Query(value = "SELECT c FROM City c order by average_score desc")
	List<City> getCitiesOrdered();

	@Query(value = "SELECT ci, c FROM City ci JOIN ci.categoriesList c WHERE c.name = :name AND c.score_out_of_ten > :score_out_of_ten order by score_out_of_ten desc")
	List<City> getCitiesWithCategoryBiggerThan(@Param("name") String name, @Param("score_out_of_ten") Integer score_out_of_10);


}
