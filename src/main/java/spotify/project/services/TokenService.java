package spotify.project.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface TokenService {

	String getUsernameFromToken(HttpServletRequest request);

	void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
