package spotify.project.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spotify.project.command.CreateUserDto;
import spotify.project.command.UserDto;
import spotify.project.models.Role;
import spotify.project.services.TokenService;
import spotify.project.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static spotify.project.utils.PrintErrors.printErrors;

@RestController
@RequestMapping("/api")
public class UserController {
    TokenService tokenService;
    UserService userService;

    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/findByUsername/{username}")//melhorar nomes, mais diretos, tipo logo {username}
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok().body(userService.findByUserName(username));
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody CreateUserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
          return printErrors(bindingResult);
        }
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return new ResponseEntity<>(userService.saveRole(role), HttpStatus.CREATED);
    }

    @PutMapping("/role={name}/user={username}")
    public ResponseEntity<?> addRoleToUser(@PathVariable String name, @PathVariable String username){
        return new ResponseEntity<>(userService.addRoleToUser(username,name), HttpStatus.OK);
    }

    @DeleteMapping("/delete/user={username}")
    public void deleteUser(@PathVariable String username){
        if(username == null){
            throw new RuntimeException("You need to give a proper username");
        }

        userService.deleteUser(username);
    }


    @DeleteMapping("/delete/role={roleType}")
    public void deleteRole(@PathVariable String roleType){
        if(roleType == null){
            throw new RuntimeException("You need to give a proper username");
        }

        userService.deleteRole(roleType);
    }

    @GetMapping("/role/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        tokenService.refreshToken(request, response);
    }
}
