package spotify.project.services;

import spotify.project.command.CreateCityDto;

public interface CityService {
    public CreateCityDto getCityDto(String cityName);
    public void saveCity(CreateCityDto createCityDto);
}
