package com.kibzdev.globalFarm.repository;

import com.kibzdev.globalFarm.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Check if a user exist with this phone
    Optional<UserEntity> findDistinctByPhoneNumber(String phone);
    Optional<UserEntity> findDistinctByEmail(String email);
    //Login user
    UserEntity findByPhoneNumberAndPassword(String phone, String password);
}
