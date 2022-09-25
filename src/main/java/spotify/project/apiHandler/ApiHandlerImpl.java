package spotify.project.apiHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import spotify.project.command.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ApiHandlerImpl implements ApiHandler{

	private static final String GET_CITY_THREW_NAME = "https://api.teleport.org/api/urban_areas/slug:{city}/scores/";
	private static final String GET_COUNTRIES = "https://api.teleport.org/api/countries/";
	private final RestTemplate restTemplate = new RestTemplate();


	@Override
	public CreateCityDto cityDto(String city){

		Map<String, String> params = new HashMap<>();
		params.put("city", city);

		CreateCityDto createCityDto = restTemplate.getForObject(GET_CITY_THREW_NAME, CreateCityDto.class, params);
		createCityDto.setName(city);
		createCityDto.setAvg_score_0_to_10(createCityDto
				.getCategories()
				.stream()
				.map(CreateCategoryDto::getScore_out_of_10)
				.reduce(0,(integer, integer2) -> integer + integer2)/17);

		return createCityDto;


	}



}
