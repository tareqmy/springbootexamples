package com.tareqmy.springbootexamples.service.impl;

import com.tareqmy.springbootexamples.data.entity.User;
import com.tareqmy.springbootexamples.data.repository.UserRepository;
import com.tareqmy.springbootexamples.service.AccountService;
import com.tareqmy.springbootexamples.web.exceptions.ApiFailedException;
import com.tareqmy.springbootexamples.web.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser() {
        return userRepository.findOneByUsername(SecurityUtils.getCurrentUserName()).orElseThrow(
            () -> new RuntimeException("Current Logged user may not be active or found!")
        );
    }

    @Override
    public boolean isSystemAdmin() {
        return getUser().isSystemAdmin();
    }

    @Override
    public boolean isAdmin() {
        return getUser().isAdmin();
    }

    @Override
    public boolean isUser() {
        return getUser().isUser();
    }

    @Transactional(readOnly = false)
    @Override
    public String generateAPIKey(User user) {
        try {
            UUID uuid = UUID.randomUUID();
            user.setApiKey(uuid.toString());
            userRepository.save(user);
            return uuid.toString();
        } catch (Exception e) {
            throw new ApiFailedException(e);
        }
    }
}
