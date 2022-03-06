package com.tareqmy.springbootexamples.service.impl;

import com.tareqmy.springbootexamples.data.entity.Role;
import com.tareqmy.springbootexamples.data.entity.User;
import com.tareqmy.springbootexamples.data.repository.UserRepository;
import com.tareqmy.springbootexamples.service.AccountService;
import com.tareqmy.springbootexamples.service.UserService;
import com.tareqmy.springbootexamples.web.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by tareqmy at 6/3/22
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AccountService accountService;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AccountService accountService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
    }

    @Transactional
    @Override
    public Optional<User> addUser(UserDTO userDTO) {
        if (isUsernameTaken(userDTO.getUsername())) {
            throw new RuntimeException("This email is already taken!");
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.valueOf(userDTO.getRole()));
        if (user.isSystemAdmin()) {
            throw new RuntimeException("System admin cannot be created!");
        }
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> updateUser(UserDTO userDTO) {
        User user = getUser(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findOneByUsername(username).orElseThrow(() -> new RuntimeException("Current Logged user may not be active or found!"));
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid user id " + id));
    }

    @Override
    public List<User> findAll() {
        User user = accountService.getUser();
        if (user.isSystemAdmin()) {
            return userRepository.findAll();
        }
        return userRepository.findAllByRoleNot(Role.ROLE_SYSTEM_ADMIN);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        findOne(id).ifPresent(this::delete);
    }

    private void delete(User user) {
        if (user.isSystemAdmin()) {
            throw new RuntimeException("System admin cannot be deleted!");
        }
        if (accountService.getUser().equals(user)) {
            throw new RuntimeException("You cant delete yourself!");
        }
        userRepository.delete(user);
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.findOneByUsername(username).isPresent();
    }
}
