package spotify.project.services;

import spotify.project.command.PasswordDto;

import javax.servlet.http.HttpServletRequest;

public interface PasswordService {
    void changePassword(PasswordDto passwordDto, HttpServletRequest request);
}
