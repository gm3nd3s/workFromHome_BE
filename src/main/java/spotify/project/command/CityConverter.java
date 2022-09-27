package spotify.project.command;

import spotify.project.models.City;
import spotify.project.services.CityService;

import java.util.ArrayList;

public class CityConverter {

	public static CityDto convertToDto(City city) {
		return CityDto.builder()
				.id(city.getId())
				.name(city.getName())
				.averageScore(city.getAverageScore())
				.build();
	}

	public static CityDtoWithCategory convertToDtoWithCategory(City city, String categoryName) {
		CityDtoWithCategory cityDtoWithCategory = CityDtoWithCategory.builder()
				.name(city.getName())
				.avg_score_0_to_10(city.getAverageScore())
				.categoryDto(CategoryConverter
						.convertToDto(city
								.getCategoriesList()
								.stream()
								.filter(category1 -> category1.getName().equals(categoryName))
								.findFirst()
								.get()))
				.build();

		return cityDtoWithCategory;
	}


	public static City convertCreateCityDtoToCity(CreateCityDto createCityDto) {
		City city = City.builder()
				.name(createCityDto.getName())
				.averageScore(createCityDto.getAverage_score())
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

	public static City convertCityDtoToCity(CityDto cityDto) {
		return City.builder()
				.name(cityDto.getName())
				.averageScore(cityDto.getAverageScore())
				.build();
	}
}
