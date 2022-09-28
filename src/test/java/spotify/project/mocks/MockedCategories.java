package spotify.project.mocks;

import spotify.project.command.CategoryDto;
import spotify.project.command.CreateCategoryDto;
import spotify.project.models.Category;

import java.util.List;

public class MockedCategories {

    public static Category getCategory1(){
        return Category.builder()
                .id(1)
                .name("Housing")
                .score_out_of_ten(10)
                .build();
    }

    public static Category getCategory2(){
        return Category.builder()
                .id(2)
                .name("Safety")
                .score_out_of_ten(9)
                .build();
    }

    public static List<Category> getCategoryList(){
        return List.of(Category.builder()
                .id(1)
                .name("Housing")
                .score_out_of_ten(10)
                .build(),
                Category.builder()
                        .id(2)
                        .name("Safety")
                        .score_out_of_ten(9)
                        .build());
    }

    public static CategoryDto convertToDto(Category category) {
        return CategoryDto.builder()
                .name(category.getName())
                .score_out_of_10(category.getScore_out_of_ten())
                .build();
    }

    public static Category convertCreateCategoryDtoToCategory(CreateCategoryDto creatCategoryDto) {
        return Category.builder()
                .name(creatCategoryDto.getName())
                .score_out_of_ten(creatCategoryDto.getScore())
                .build();
    }
}
