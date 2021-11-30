package com.cs316.money_mind.serviceImpl;

import com.cs316.money_mind.entity.User;
import com.cs316.money_mind.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UserDetailsServiceImpl
 *
 * @author Sainjargal Ishdorj
 **/

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        final User user;

        if (!optionalUser.isPresent()) throw new UsernameNotFoundException("User email:'" + email + "' not found");
        else user = optionalUser.get();

        if (!user.isActive()) throw new UsernameNotFoundException(email + " is deactivated user");
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    public UserDetails loadUserByRoleTemp(String subject) {
        return org.springframework.security.core.userdetails.User
                .withUsername(subject)
                .password("")
                .authorities("ROLE_TEMP")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
