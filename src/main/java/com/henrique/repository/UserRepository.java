package com.henrique.repository;

import com.henrique.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT e FROM UserEntity e JOIN FETCH e.roles WHERE e.username= (:username)")
    UserEntity findByUsername(@Param("username") String username);

    boolean existsByUsername(String username);
}
