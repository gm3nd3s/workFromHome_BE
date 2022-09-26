package spotify.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spotify.project.models.City;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {
    City findByName(String name);

    @Query(value = "SELECT c FROM City c order by avg_score_0_to_10 desc")
    public List<City> getCitiesOrdered();

    @Query(value = "SELECT ci, c FROM City ci JOIN ci.categoriesList c WHERE c.name = :name AND c.score_out_of_ten > :score_out_of_ten order by score_out_of_ten desc")
    public  List<City> getCitiesWithCategoryBiggerThan(@Param("name") String name, @Param("score_out_of_ten") Integer score_out_of_10);


}
