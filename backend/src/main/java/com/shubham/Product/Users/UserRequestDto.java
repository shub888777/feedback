package com.shubham.Product.Users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDto {

    @Email(message = "IT SHOULD BE IN PROPER EMAIL")
    private String email;
    @Size(max = 20 ,min = 6,message = "USER NAME SHOULD BE BETWEEN 6-20")
    private String username;
    private String password;

}
