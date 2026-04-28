package com.shubham.feedback;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Data
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserAuthService us;
    @PostMapping("/register")
    public Users register(@RequestBody Users users){
        return us.registerUser(users);
    }
}
