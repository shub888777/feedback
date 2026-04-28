package com.shubham.Product.Secuity;

import com.shubham.Product.Users.UserRepo;
import com.shubham.Product.Users.Users;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserDs implements UserDetailsService {
UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users=userRepo.findByUsername(username);
        return new AppUserDetails(users);
    }
}
