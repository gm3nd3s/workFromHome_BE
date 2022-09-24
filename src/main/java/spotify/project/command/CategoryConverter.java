package spotify.project.command;

import spotify.project.models.Category;

public class CategoryConverter {

/*	public static CategoryDto convertToDto(Category category) {
		return CategoryDto.builder()
				.name(category.getName())
				.score_out_of_10(category.getScore_out_of_ten())
				.build();
	}
*/
	public static Category convertCreateCategoryDtoToCategory(CreateCategoryDto creatCategoryDto) {
		return Category.builder()
				.name(creatCategoryDto.getName())
				.score_out_of_ten(creatCategoryDto.getScoreOutOfTen())
				.build();
	}

	public static Category convertToCategory(CategoryDto categoryDto) {
		return Category.builder()
				.name(categoryDto.getName())
				.score_out_of_ten(categoryDto.getScore_out_of_10())
				.build();
	}
}
