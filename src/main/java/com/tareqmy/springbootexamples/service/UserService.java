package com.tareqmy.springbootexamples.service;

import com.tareqmy.springbootexamples.data.entity.User;
import com.tareqmy.springbootexamples.web.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> addUser(UserDTO userDTO);

    Optional<User> updateUser(UserDTO userDTO);

    Optional<User> findOne(Long id);

    User getUser(String username);

    User getUser(Long id);

    List<User> findAll();

    void delete(Long id);

    boolean isUsernameTaken(String username);


}
