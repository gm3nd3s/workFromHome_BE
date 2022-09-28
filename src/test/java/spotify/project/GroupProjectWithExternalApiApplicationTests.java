package spotify.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import spotify.project.command.CreateCityDto;
import spotify.project.services.CityServiceImpl;

@SpringBootTest
@ContextConfiguration(classes = GroupProjectWithExternalApiApplication.class)
class GroupProjectWithExternalApiApplicationTests {

	CityServiceImpl cityService;

	@Test
	void testGetCityByNameShouldReturnCreateCityDto() {
		//Arrange
		String cityName = "Sofia";
		String expectedResltCityName = "Sofia";
		//Act
		CreateCityDto cityResult = cityService.getCityDto(cityName);
		//Assert
		Assertions.assertEquals(cityName, cityResult.getName());
	}
}
