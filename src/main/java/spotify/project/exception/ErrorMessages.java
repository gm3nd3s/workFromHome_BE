package spotify.project.exception;

public final class ErrorMessages {

	private ErrorMessages() {
	}

	public static final String USER_NOT_FOUND = "User not found";
	public static final String ROLE_NOT_FOUND = "Role not found";
	public static final String INVALID_USER = "Invalid user input";
	public static final String USER_ALREADY_EXISTS = "Username already exists. Try another one, please.";
	public static final String ROLE_ALREADY_EXISTS = "Role type already exists. Try another one, please.";
	public static final String USER_ALREADY_HAS_THAT_ROLE = "User already has that role.";
	public static final String LOGIN_FAILED = "Login failed.";
	public static final String NOT_ENOUGH_PERMISSIONS = "Not enough permissions";
	public static final String INVALID_PASSWORD = "Invalid Password";
	public static final String ALREADY_LOGGED_IN = "Already logged in.";
	public static final String INVALID_PASSWORD_CHANGE_REQUEST = "Password change failed, check if the new password and confirm new password are equal.";
	public static final String INVALID_ASSERT_AUTHORITIES = "User has already this authority";

}
