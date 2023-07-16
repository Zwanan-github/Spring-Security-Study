package com.example.springsecuritystudy.repository;

import com.example.springsecuritystudy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.username = ?1 and u.isDelete = 0")
    User findByUsername(@NonNull String username);


    @Query("select u from User u inner join u.roles roles where roles.rid = 1 and u.isDelete = 0")
    List<User> findAllAdmin();

    @Transactional
    @Modifying
    @Query("update User u set u.nickname = ?1, u.password = ?2 where u.username = ?3 and u.isDelete = 0")
    int updateUserInfo(@NonNull String nickname, @NonNull String password, @NonNull String username);

    @Override
    User save(User entity);

    @Transactional
    @Modifying
    @Query("update User u set u.isDelete = 1 where u.username = ?1")
    int deleteByUsername(@NonNull String username);




}
