package com.tareqmy.springbootexamples.web.rest;

import com.tareqmy.springbootexamples.service.UserService;
import com.tareqmy.springbootexamples.web.dto.UserDTO;
import com.tareqmy.springbootexamples.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by tareqmy at 6/3/22
 */
@RestController("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<UserDTO> getUsers() {
        return userService.findAll()
            .stream()
            .map(UserDTO::new)
            .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        UserDTO userDTO = userService.findOne(userId).map(UserDTO::new)
            .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO dto = userService.addUser(userDTO).map(UserDTO::new)
            .orElseThrow(() -> new ResourceNotFoundException("Could not create :: " + userDTO));
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO dto = userService.updateUser(userDTO).map(UserDTO::new)
            .orElseThrow(() -> new ResourceNotFoundException("Could not create :: " + userDTO));
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{userId}")
    public Map<String, Boolean> deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return Collections.singletonMap("deleted", Boolean.TRUE);
    }
}
