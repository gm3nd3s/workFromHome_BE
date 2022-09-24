package spotify.project.apiHandler;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import spotify.project.command.CategoryConverter;
import spotify.project.command.CategoryDto;
import spotify.project.command.CityDto;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApiHandlerImpl implements ApiHandler{

	private static final String GET_CITY_THREW_NAME = "https://api.teleport.org/api/urban_areas/slug:{city}/scores/";

	private final RestTemplate restTemplate = new RestTemplate();

	@Override
	public CityDto cityDto(String city){

		Map<String, String> params = new HashMap<>();
		params.put("city", city);

		CityDto cityDto = restTemplate.getForObject(GET_CITY_THREW_NAME, CityDto.class, params);
		cityDto.setName(city);
		cityDto.getCategories().stream().map(categoryDto -> CategoryConverter.convertToCategory(categoryDto));
		return cityDto;
	}

}
