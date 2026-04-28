package com.shubham.Product.Users;

import com.shubham.Product.Secuity.JwtUtil;
import lombok.AllArgsConstructor;
import org.apache.catalina.Authenticator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    JwtUtil jwtUtil;

    public Users createUser(UserRequestDto userdto){
        Users users=new Users();
        users.setEmail(userdto.getEmail());
        users.setPassword(passwordEncoder.encode(userdto.getPassword()));
        users.setRole(List.of("USER"));

        users.setUsername(userdto.getUsername());
        return userRepo.save(users);
    }
    public Users createAdmin(UserRequestDto userdto){
        Users users=new Users();
        users.setUsername(userdto.getUsername());
        users.setEmail(userdto.getEmail());
        users.setPassword(passwordEncoder.encode(userdto.getPassword()));
        users.setRole(List.of("ADMIN"));


        return userRepo.save(users);
    }
    public List<Users> getAllUser(){
        return userRepo.findAll();
    }


    public String loginUser(LoginDtoRequest loginDtoRequest){

        Authentication auth=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken
                        (loginDtoRequest.getUsername(),loginDtoRequest.getPassword()));
        UserDetails userDetails= (UserDetails) auth.getPrincipal();


        String token= jwtUtil.generateToken(userDetails);
        return token;
    }


    public Users getUserByUsername(String username){
        return userRepo.findByUsername(username);
    }

}

