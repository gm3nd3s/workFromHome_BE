package spotify.project.exception;

public final class Messages {

	public static final String NULL_EXCEPTION = "Input cannot be null.";
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
	public static final String NOT_BLANK = " cannot be blank.";
	public static final String PASSWORD_SIZE = "password must be between 6 to 16 characters.";
	public static final String SCORE_SIZE = "score must be between 0 and 10.";

	public static final String CITY_NOT_FOUND = "City not found in our Database. Use instead localhost:8080/api/city/{cityName}.";
	public static final String CITY_ALREADY_EXISTS = "This city already exists on your visited list.";
	public static final String REVIEW_ALREADY_EXISTS = "You already made a review. You can update your review on localhost:8080/api/{city}/review";
    public static final String CITY_NOT_VISITED = "You have to visit the city first in order to place a review.";
	public static final String REVIEW_NOT_FOUND = "Review not found. You need make a review first, in order to be able to update it.";
	public static final String PASSWORDS_NOT_MATCH = "Passwords do not match.";
}
