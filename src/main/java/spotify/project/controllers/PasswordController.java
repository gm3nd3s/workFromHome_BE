package spotify.project.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spotify.project.command.PasswordDto;
import spotify.project.services.PasswordService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static spotify.project.utils.PrintErrors.printErrors;

@RestController
@RequestMapping("/api/user")
public class PasswordController {

	private PasswordService passwordService;

	public PasswordController(PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	@PutMapping("/changepassword")
	public ResponseEntity<?> changePassword(@Valid
											@RequestBody PasswordDto passwordDto,
											BindingResult bindingResult,
											HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return printErrors(bindingResult);
		}
		passwordService.changePassword(passwordDto, request);
		return ResponseEntity.ok("Password changed successfully");
	}
}
