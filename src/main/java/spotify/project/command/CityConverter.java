package spotify.project.command;

import spotify.project.models.Category;
import spotify.project.models.City;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CityConverter {

	public static CityDto convertToDto(City city) {
		return CityDto.builder()
				.id(city.getId())
				.name(city.getName())
				.avg_score_0_to_10(city.getAvg_score_0_to_10())
				.build();
	}


	public static City convertCreateCityDtoToCity(CreateCityDto createCityDto) {
		City city = City.builder()
				.name(createCityDto.getName())
				.avg_score_0_to_10(createCityDto.getAvg_score_0_to_10())
				.categoriesList(new ArrayList<>())
				.build();

		createCityDto.getCategories()
				.forEach(createCategoryDto -> city
						.getCategoriesList()
						.add(CategoryConverter.convertCreateCategoryDtoToCategory(createCategoryDto)));

		city.getCategoriesList()
				.forEach(category -> category.setCity(city));

		return city;
	}


	/*public static City convertCityDtoToCity(CityDto cityDto) {
		return City.builder()
				.name(cityDto.getName())
				.categoriesList(cityDto
						.getCategories()
						.stream()
						.map(categoryDto -> CategoryConverter.convertToCategory(categoryDto))
						.toList())
				.build();
	}*/
}
