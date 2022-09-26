package spotify.project.services;

import spotify.project.command.*;
import spotify.project.models.City;

import java.util.List;

public interface CityService {
	CreateCityDto getCityDto(String cityName);

	void saveCity(CreateCityDto createCityDto);

	List<CityDto> getAllCitiesInDB();

	CityDto getCityDtoByName(String name);

	City findCityByCityName(String cityName);

	void saveCityOnRepository(City city);

	List<CityDto> getCitiesInDBOrdered();

	List<CityDtoWithCategory> getCitiesWithCategoryBiggerThan(String category, Integer score);

	List<CityUrbanAreaDto> getCities();

	List<String> getCitiesWithMinimumScoreForCategory(String category, Integer minimumScore);
}
