package spotify.project.services;

import spotify.project.command.CityDto;
import spotify.project.models.City;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface TokenService {

    public String getUsernameFromToken(HttpServletRequest request);
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
    public List<CityDto> getAllCitiesInDB();
    public CityDto getCityDtoByName(String name);

    public City findCityByCityName(String cityName);

    public void saveCityOnRepository(City city);
}
