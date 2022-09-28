package spotify.project.unit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import spotify.project.models.Category;
import spotify.project.models.City;
import spotify.project.repositories.CategoryRepository;
import spotify.project.repositories.CityRepository;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static reactor.core.publisher.Mono.when;
import static spotify.project.mocks.MockedCitys.*;

@DataJpaTest
public class CityRepositoryTest {

    @Autowired
    CityRepository cityRepositoryTest;

    @AfterEach
    public void tearDown(){
        cityRepositoryTest.deleteAll();

    }


    @Test
    public void test_getCitiesFromRepository_Should_Succeed() {

        // Given

        List<City> cities = getMockedCities();

        cityRepositoryTest.save(cities.get(0));
        cityRepositoryTest.save(cities.get(1));

        // When

        List<City> response = cityRepositoryTest.findAll();
        List<City> expected = cities;

        // Then

        assertNotNull(response);
        assertEquals(expected.size(), response.size());
        assertEquals(expected, response);
    }

    @Test
    public void test_getCitiesFromRepositoryOrderedByAverageScore_Should_Succeed() {

        // Given

        List<City> cities = getMockedCities();

        cityRepositoryTest.save(cities.get(0));
        cityRepositoryTest.save(cities.get(1));

        // When

        List<City> response = cityRepositoryTest.getCitiesOrdered();
        List<City> expected = cities;

        // Then

        assertEquals(expected.size(), response.size());
        assertEquals(expected.get(1), response.get(0));
        assertEquals(expected.get(0), response.get(1));

    }

    @Test
    public void test_getCityByName_shouldSucceed(){

        // Given

        City city = getMockedCity1();
        City city1 = getMockedCity2();

        cityRepositoryTest.save(city);

        // When

        City response = cityRepositoryTest.findByName(city.getName()).get();

        final Executable action = () -> cityRepositoryTest.findByName(city1.getName()).get();

        City expected = city;

        // Then

        assertThrows(NoSuchElementException.class, action);
        assertNotNull(response);
        assertEquals(expected, response);

    }


}
