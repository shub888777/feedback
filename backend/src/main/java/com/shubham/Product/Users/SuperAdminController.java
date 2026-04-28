package com.shubham.Product.Users;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/superadmin")
public class SuperAdminController {
    private final UserService userService;
    @PostMapping("/admin")
    public Users createAdmin(@RequestBody UserRequestDto userRequestDto){
        return userService.createAdmin(userRequestDto);
    }
    @GetMapping
    public List<Users> getAllUser(){
        return userService.getAllUser();
    }
}
