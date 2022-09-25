package spotify.project.command;

import spotify.project.models.User;

import java.util.ArrayList;


public class UserConverter {

    public static UserDto convertEntityToUserDto(User user){
    return UserDto.builder()
            .id(user.getId())
            .name(user.getName())
            .username(user.getUsername())
            .password(user.getPassword())
            .roles(user.getRoles())
            .citiesVisited(user.getCitiesVisited())
            .livingCity(user.getLivingCity())
            .build();
}
    public static User convertUserDtoToEntity(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .roles(userDto.getRoles())
                .citiesVisited(userDto.getCitiesVisited())
                .livingCity(userDto.getLivingCity())
                .build();
    }
    public static User convertCreateUserDtoToEntity(CreateUserDto createUserDto){
        return User.builder()
                .name(createUserDto.getName())
                .username(createUserDto.getUsername())
                .password(createUserDto.getPassword())
                .roles(new ArrayList<>())
                .build();
    }

}
