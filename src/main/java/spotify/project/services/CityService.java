package spotify.project.services;

import org.springframework.http.ResponseEntity;
import spotify.project.command.CityDto;
import spotify.project.command.CityDtoWithCategory;
import spotify.project.command.CreateCityDto;
import spotify.project.models.City;

import java.util.List;

public interface CityService {
    public CreateCityDto getCityDto(String cityName);
    public void saveCity(CreateCityDto createCityDto);
    public List<CityDto> getAllCitiesInDB();
    public CityDto getCityDtoByName(String name);

    public City findCityByCityName(String cityName);

    public void saveCityOnRepository(City city);

	List<CityDto> getCitiesInDBOrdered();

    List<CityDtoWithCategory> getCitiesWithCategoryBiggerThan(String category, Integer score);
}
