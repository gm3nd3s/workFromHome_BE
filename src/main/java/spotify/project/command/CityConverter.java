package spotify.project.command;

import spotify.project.models.Category;
import spotify.project.models.City;

import java.util.stream.Collectors;

public class CityConverter {

	public static CityDto convertToDto(City city) {
		return CityDto.builder()
				.name(city.getName())
				.categories(city
						.getCategoriesList()
						.stream()
						.map(category -> CategoryConverter.convertToDto(category))
						.toList())
				.summary(city.getSumary())
				.build();
	}

	public static City convertCreateCityDtoToCity(CreateCityDto createCityDto) {
		return City.builder()
				.name(createCityDto.getName())
				.categoriesList(createCityDto
						.getCategories()
						.stream()
						.map(categoryDto -> CategoryConverter.convertToCategory(categoryDto))
						.toList())
				.sumary(createCityDto.getSummary())
				.build();
	}


	public static City convertToCategory(CityDto cityDto) {
		return City.builder()
				.name(cityDto.getName())
				.categoriesList(cityDto
						.getCategories()
						.stream()
						.map(categoryDto -> CategoryConverter.convertToCategory(categoryDto))
						.toList())
				.build();
	}
}
