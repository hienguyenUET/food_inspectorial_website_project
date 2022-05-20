package com.example.food_inspectorate_website_project.service.implementation;

import com.example.food_inspectorate_website_project.dto.AccountDTO;
import com.example.food_inspectorate_website_project.entity.Account;
import com.example.food_inspectorate_website_project.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
public class UserDetailsImpl implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private GrantedAuthority authorities;

    public UserDetailsImpl(int id, String username, String email, String password, String lastName, String firstName, GrantedAuthority authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        GrantedAuthority authorities = new SimpleGrantedAuthority(user.getRole().getName().name());
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getLastName(),
                user.getFirstName(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(authorities);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
