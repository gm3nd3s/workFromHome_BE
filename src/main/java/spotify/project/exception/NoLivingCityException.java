package spotify.project.exception;

import org.springframework.data.jpa.repository.JpaRepository;

public class NoLivingCityException extends RuntimeException{
    public NoLivingCityException() {
        super(Messages.NO_LIVING_CITY);
    }
}
