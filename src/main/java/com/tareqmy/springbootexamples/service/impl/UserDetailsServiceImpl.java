package com.tareqmy.springbootexamples.service.impl;

import com.tareqmy.springbootexamples.data.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) {
        log.info("User sign in requested by " + email);
        return userRepository.findOneByUsername(email).map(user -> {
            log.info("Found user {}", user);
            return user;
        }).orElseThrow(() -> new UsernameNotFoundException("User " + email + " was not found in the " +
            "database"));
    }
}

