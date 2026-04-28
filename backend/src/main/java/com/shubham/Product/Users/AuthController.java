package com.shubham.Product.Users;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping()
    public Users createUser(@Valid @RequestBody UserRequestDto userRequestDto){
        return userService.createUser(userRequestDto);
    }


    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@Valid @RequestBody LoginDtoRequest loginDtoRequest){
        System.out.println(loginDtoRequest);
        return ResponseEntity.ok(userService.loginUser(loginDtoRequest));
    }
}
