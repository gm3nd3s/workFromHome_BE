package spotify.project.apiHandler;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import spotify.project.command.CreateCategoryDto;
import spotify.project.command.CreateCityDto;
import spotify.project.command.UrbanAreaDto;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class ApiHandlerImpl implements ApiHandler {

	private static final String GET_CITY_THROUGH_NAME = "https://api.teleport.org/api/urban_areas/slug:{city}/scores/";
	private static final String GET_CITIES = "https://api.teleport.org/api/urban_areas/";
	private final RestTemplate restTemplate = new RestTemplate();

	@Override
	public CreateCityDto cityDto(String city) {

		String city2 = city.replaceAll(" ", "-").toLowerCase(Locale.ROOT).replaceAll("[,.]", "");
		if (city2.equalsIgnoreCase("galway")){
			city2 = "gaillimh";
		}
		Map<String, String> params = new HashMap<>();
		params.put("city", city2);
		CreateCityDto createCityDto = restTemplate.getForObject(GET_CITY_THROUGH_NAME, CreateCityDto.class, params);
		createCityDto.setName(city);
		createCityDto.setAverage_score(
				createCityDto
						.getCategories()
						.stream()
						.map(CreateCategoryDto::getScore)
						.reduce(0, Integer::sum) / createCityDto.getCategories().size()
		);
		return createCityDto;
	}

	@Override
	public UrbanAreaDto getCities() {
		return restTemplate.getForObject(GET_CITIES, UrbanAreaDto.class);
	}
}
