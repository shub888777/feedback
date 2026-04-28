package com.shubham.Product.Secuity;

import com.shubham.Product.Users.Users;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class AppUserDetails implements UserDetails {
    final private Users users;
    List<String> roles = new ArrayList<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return users.getRole().stream().map(r-> new SimpleGrantedAuthority("ROLE_"+r)).toList();
    }

    @Override
    public  String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }
}
