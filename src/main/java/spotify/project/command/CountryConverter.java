package spotify.project.command;

import spotify.project.models.Country;

public class CountryConverter {
 /*   public static CountryDto convertToDto(Country country) {
        return CountryDto.builder()
                .name(country.getName())
                .build();
    }*/

    public static Country convertToCountry(CountryDto countryDto) {
        return Country.builder()
                .href(countryDto.getHref())
                .name(countryDto.getName())
                .build();
    }
}
