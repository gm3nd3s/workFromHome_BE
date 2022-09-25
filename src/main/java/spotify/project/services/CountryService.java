package spotify.project.services;

import org.springframework.stereotype.Service;
import spotify.project.apiHandler.ApiHandler;

@Service
public class CountryService {

    private final ApiHandler apiHandler;

    public CountryService(ApiHandler apiHandler) {
        this.apiHandler = apiHandler;
    }

    /*public CountriesDto getCountryDto() {
        return apiHandler.getCountryList();
    }*/
}
