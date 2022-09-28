package spotify.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spotify.project.services.CityServiceImpl;

import javax.annotation.PostConstruct;
import java.security.Security;

@SpringBootApplication
public class GroupProjectWithExternalApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupProjectWithExternalApiApplication.class, args);
	}
}
