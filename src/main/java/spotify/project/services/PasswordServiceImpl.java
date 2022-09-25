package spotify.project.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spotify.project.command.PasswordDto;
import spotify.project.exception.InvalidPasswordChangeRequestException;
import spotify.project.exception.InvalidPasswordException;
import spotify.project.models.User;
import spotify.project.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Service
public class PasswordServiceImpl implements PasswordService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private TokenServiceImpl tokenService;

    public PasswordServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenServiceImpl tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public void changePassword(PasswordDto passwordDto, HttpServletRequest request) {
        String username = tokenService.getUsernameFromToken(request);
        User user = userRepository.findByUsername(username).get();
        String newPassword = passwordDto.getNewPassword();
        String confirmPassword = passwordDto.getNewPasswordConfirm();
        String oldPassword = passwordDto.getOldPassword();
        if (newPassword.length() < 7) {
            throw new InvalidPasswordException();
        }
        if (!newPassword.equals(confirmPassword)) {
            throw new InvalidPasswordChangeRequestException();
        }
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new InvalidPasswordChangeRequestException();
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
