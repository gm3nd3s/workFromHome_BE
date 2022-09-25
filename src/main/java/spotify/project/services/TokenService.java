package spotify.project.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface TokenService {

	public String getUsernameFromToken(HttpServletRequest request);

	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
