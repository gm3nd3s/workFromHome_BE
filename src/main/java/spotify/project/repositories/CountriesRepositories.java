package spotify.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spotify.project.models.Country;

@Repository
public interface CountriesRepositories extends JpaRepository<Country, Long> {

}

