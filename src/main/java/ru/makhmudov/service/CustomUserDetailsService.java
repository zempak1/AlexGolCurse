package ru.makhmudov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.makhmudov.entity.UserReg;
import ru.makhmudov.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserReg user = userRepository.findUserByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("Username not found with username: " + username));

        return build(user);
    }

    public UserReg loadUserById(Long id) {
        return userRepository.findUserById(id).orElse(null);
    }

    public static UserReg build(UserReg user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role->new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());

        return new UserReg(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }
}
