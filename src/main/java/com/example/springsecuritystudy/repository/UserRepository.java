package com.example.springsecuritystudy.repository;

import com.example.springsecuritystudy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.username like ?1")
    User findByUsername(@NonNull String username);

}
