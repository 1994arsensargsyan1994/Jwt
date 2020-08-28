package net.prosetly.jwtappaemo.rest;


import net.prosetly.jwtappaemo.dto.UserDto;
import net.prosetly.jwtappaemo.model.User;
import net.prosetly.jwtappaemo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody User user, BindingResult bindingResult) {

        if(userService.findByUsername(user.getUsername()) != null){
            bindingResult
                    .rejectValue("email", "error.userEntity",
                            "There is already a user registered with the email provided");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        User userForDb = userService.addUser(user);

        return ResponseEntity.ok(UserDto.fromUser(userForDb));
    }


}
