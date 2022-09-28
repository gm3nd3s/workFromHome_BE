package spotify.project.services;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import spotify.project.apiHandler.ApiHandler;
import spotify.project.command.*;
import spotify.project.exception.CityNotFoundEXception;
import spotify.project.models.City;
import spotify.project.repositories.CategoryRepository;
import spotify.project.repositories.CityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EnableCaching
@Service
public class CityServiceImpl implements CityService {

	private final ApiHandler apiHandler;
	private final CityRepository cityRepository;
	private final CategoryRepository categoryRepository;

	public CityServiceImpl(ApiHandler apiHandler, CityRepository cityRepository, CategoryRepository categoryRepository) {
		this.apiHandler = apiHandler;
		this.cityRepository = cityRepository;
		this.categoryRepository = categoryRepository;
	}

	public CreateCityDto getCityDto(String cityName) {
		CreateCityDto createCityDto = apiHandler.cityDto(cityName);
		if (checkCityOnDatabase(cityName)) {
			return createCityDto;
		}
		saveCity(createCityDto);
		return createCityDto;
	}

	public void saveCity(CreateCityDto createCityDto) {
		saveCityOnRepository(CityConverter
						.convertCreateCityDtoToCity(createCityDto));
	}

	public boolean checkCityOnDatabase(String cityName) {
		return cityRepository.findByName(cityName).isPresent();
	}

	public List<CityDto> getAllCitiesInDB() {
		return cityRepository
				.findAll()
				.parallelStream()
				.map(CityConverter::convertToDto)
				.collect(Collectors.toList());
	}

	public CityDto getCityDtoByName(String name) {
		return CityConverter.convertToDto(cityRepository.findByName(name).orElseThrow(CityNotFoundEXception::new));
	}

	public City findCityByCityName(String cityName) {
		return cityRepository.findByName(cityName).orElseThrow(CityNotFoundEXception::new);
	}

	public void saveCityOnRepository(City city) {
		cityRepository.save(city);
	}

	@Override
	public List<CityDto> getCitiesInDBOrdered() {
		return cityRepository
				.getCitiesOrdered()
				.parallelStream()
				.map(CityConverter::convertToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<CityDtoWithCategory> getCitiesWithCategoryBiggerThan(String category, Integer score) {
		return cityRepository
				.getCitiesWithCategoryBiggerThan(category, score)
				.parallelStream()
				.map(city -> CityConverter.convertToDtoWithCategory(city, category))
				.collect(Collectors.toList());
	}

	@Override
	public List<CityUrbanAreaDto> getCities() {
		return apiHandler.getCities().getLinks().getCities();
	}

	@Override
	public List<CityDtoWithCategory> getCitiesWithMinimumScoreForCategory(String category, Integer minimumScore) {
		ArrayList<CityDtoWithCategory> cities = new ArrayList<>();
		apiHandler.getCities().getLinks().getCities().forEach(city -> {
			String cityName = city.getName();//getHref().substring(city.getHref().lastIndexOf(":")+1,city.getHref().lastIndexOf("/"));
			CreateCityDto cityDto = apiHandler.cityDto(cityName);
			CreateCategoryDto categoryDto = cityDto
					.getCategories()
					.parallelStream()
					.filter(cat -> category.equalsIgnoreCase(cat.getName()))
					.findFirst()
					.orElseThrow();
			Integer score = categoryDto.getScore();
			if (score >= minimumScore) {
				cities.add(CityConverter.convertToDtoWithCategory(CityConverter.convertCreateCityDtoToCity(cityDto), category));
			}
		});
		return cities;
	}
}
