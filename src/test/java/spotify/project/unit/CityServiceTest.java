package spotify.project.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spotify.project.apiHandler.ApiHandler;
import spotify.project.command.UserConverter;
import spotify.project.models.City;
import spotify.project.models.User;
import spotify.project.repositories.CategoryRepository;
import spotify.project.repositories.CityRepository;
import spotify.project.services.CityService;
import spotify.project.services.CityServiceImpl;
import spotify.project.services.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static spotify.project.mocks.MockedCitys.getMockedCity1;
import static spotify.project.mocks.MockedUsers.getMockedUser0;

@ExtendWith(SpringExtension.class)
public class CityServiceTest {
    CityService cityService;

    @Mock
    ApiHandler apiHandler;
    @Mock
    CityRepository cityRepository;
    @Mock
    CategoryRepository categoryRepository;


    @BeforeEach
    public void setUp() {
        cityService = new CityServiceImpl(apiHandler, cityRepository, categoryRepository);

    }

    @Test
    public void saveCity_should_return_success() {

        // Given

        City expected = getMockedCity1();

        // When

        cityRepository.save(expected);
        ArgumentCaptor<City> userArgumentCaptor = ArgumentCaptor.forClass(City.class);
        verify(cityRepository).save(userArgumentCaptor.capture());
        City response = userArgumentCaptor.getValue();

        // Then

        assertNotNull(response);
        assertEquals(expected, response);

    }
}
