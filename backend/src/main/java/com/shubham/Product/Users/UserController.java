package com.shubham.Product.Users;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    @GetMapping("/me")
    public Users getMyInfo(Principal principal)
    {
        return userService.getUserByUsername(principal.getName());
    }

}
