package com.henrique.repository;

import com.henrique.model.CartEntity;
import com.henrique.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    Optional<CartEntity> findByUser(UserEntity user);

}
