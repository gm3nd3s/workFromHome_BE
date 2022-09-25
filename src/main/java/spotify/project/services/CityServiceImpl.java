package spotify.project.services;

import org.springframework.stereotype.Service;
import spotify.project.apiHandler.ApiHandler;
import spotify.project.command.CityConverter;
import spotify.project.command.CityDto;
import spotify.project.command.CityDtoWithCategory;
import spotify.project.command.CreateCityDto;
import spotify.project.models.City;
import spotify.project.repositories.CategoryRepository;
import spotify.project.repositories.CityRepository;

import java.util.List;
import java.util.stream.Collectors;

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
		saveCity(createCityDto);
		return createCityDto;}

	public void saveCity(CreateCityDto createCityDto) {
		cityRepository
			.save(CityConverter
				.convertCreateCityDtoToCity(createCityDto));
	}

	public List<CityDto> getAllCitiesInDB() {
		return cityRepository
			.findAll()
			.stream()
			.map(CityConverter::convertToDto)
			.collect(Collectors.toList());
	}

	public CityDto getCityDtoByName(String name) {
		return CityConverter.convertToDto(cityRepository.findByName(name));
	}

	public City findCityByCityName(String cityName) {
		return cityRepository.findByName(cityName);
	}

	public void saveCityOnRepository(City city) {
		cityRepository.save(city);
	}

	@Override
	public List<CityDto> getCitiesInDBOrdered() {
		return cityRepository
				.getCitiesOrdered()
				.stream()
				.map(CityConverter::convertToDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<CityDtoWithCategory> getCitiesWithCategoryBiggerThan(String category, Integer score) {
		return cityRepository
				.getCitiesWithCategoryBiggerThan(category, score)
				.stream()
				.map(city -> CityConverter.convertToDtoWithCategory(city, category))
				.collect(Collectors.toList());
	}
}
