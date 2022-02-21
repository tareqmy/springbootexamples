package com.tareqmy.springbootexamples.service;

import com.tareqmy.springbootexamples.data.entity.User;

public interface AccountService {

    User getUser();

    boolean isSystemAdmin();

    boolean isAdmin();

    boolean isUser();
}
