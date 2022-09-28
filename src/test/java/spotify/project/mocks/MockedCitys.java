package spotify.project.mocks;

import spotify.project.models.City;

import java.util.List;

import static spotify.project.mocks.MockedCategories.*;
public class MockedCitys {

    public static City getMockedCity1(){
        return City.builder()
                .id(1)
                .name("porto")
                .averageScore(5)
                .categoriesList(getCategoryList())

                .build();
    }

    public static City getMockedCity2(){
        return City.builder()
                .id(2)
                .name("lisboa")
                .averageScore(5)
                .categoriesList(getCategoryList())
                .build();
    }

    public static List<City> getMockedCities(){
            return List.of(City.builder()
                .id(1)
                .name("lisboa")
                .averageScore(5)
                .categoriesList(getCategoryList())
            .build(),
                    City.builder()
                            .id(2)
                            .name("porto")
                            .averageScore(6)
                            .categoriesList(getCategoryList())
                            .build());
    }






}
