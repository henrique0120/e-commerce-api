package com.henrique.repository;

import com.henrique.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT e FROM UserEntity e JOIN FETCH e.roles WHERE e.email= (:email)")
    Optional<UserEntity> findByEmail(@Param("email") String email);

    boolean existsByEmail(String email);



}
