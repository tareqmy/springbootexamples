package com.tareqmy.springbootexamples.data.repository;

import com.tareqmy.springbootexamples.data.entity.Role;
import com.tareqmy.springbootexamples.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByUsername(String email);

    List<User> findAllByRoleNot(Role role);
}
