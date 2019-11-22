package com.example.company.device_library.configuration;

import com.example.company.device_library.model.Role;
import com.example.company.device_library.model.UserApp;
import com.example.company.device_library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String emailAsUsername) throws UsernameNotFoundException {
        UserApp userApp = userRepository.findUserByUsernameAsEmail(emailAsUsername)
                .orElseThrow(() -> new UsernameNotFoundException("Nie ma takiego użytkownika"));
        return new org.springframework.security.core.userdetails.User(
                userApp.getEmail(), userApp.getPassword(), getAuthorities(userApp.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return authorities;
    }


}
