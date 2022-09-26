package spotify.project.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

//onceperequestfilter interseta todos os requests do server
@Slf4j
public class CustomAuthorizationFilter extends OncePerRequestFilter {


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (request.getServletPath().equals("/login") || request.getServletPath().equals("/api/user/register")) {
			filterChain.doFilter(request, response);
		} else {

			String authorizationHeader = request.getHeader(AUTHORIZATION);

			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				try {
					String token = authorizationHeader.substring("Bearer ".length());
					Algorithm algorithm = Algorithm.HMAC256("secret".getBytes()); //secret é tipo a assinatura do nosso token, deveria ser guardada e encriptada num local seguro
					JWTVerifier verifier = JWT.require(algorithm).build();
					DecodedJWT decodedJWT = verifier.verify(token);
					String username = decodedJWT.getSubject(); //dá me username
					String[] roles = decodedJWT.getClaim("roles").asArray(String.class); //definimos no C.A.Filter que o claim era os roles
					Collection<SimpleGrantedAuthority> authorities = stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
					// stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
					UsernamePasswordAuthenticationToken authenticationToken =
							new UsernamePasswordAuthenticationToken(username, null, authorities);//credentials é a password, que nao temos so temos o token
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);//basicament está a dizer ao sistema que este user que estou a
					// enviar está autenticado e tem os roles x,y,z
					filterChain.doFilter(request, response);
				} catch (Exception exception) {
					log.error("Error logging in {}", exception);
					response.setHeader("error", exception.getMessage());
					response.setStatus(FORBIDDEN.value());
					//response.sendError(FORBIDDEN.value());
					Map<String, String> error = new HashMap<>();
					error.put("error_message", exception.getMessage());
					response.setContentType(APPLICATION_JSON_VALUE);
					new ObjectMapper().writeValue(response.getOutputStream(), error);// devolve os tokens na resposta
				}
			} else {
				filterChain.doFilter(request, response);
			}

		}
	}
}
